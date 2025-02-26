package com.example.blog.board_back.service.implement;

import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.board.*;
import com.example.blog.board_back.entity.BoardEntity;
import com.example.blog.board_back.entity.CommentEntity;
import com.example.blog.board_back.entity.FavoriteEntity;
import com.example.blog.board_back.entity.ImageEntity;
import com.example.blog.board_back.repository.*;
import com.example.blog.board_back.repository.resultSet.GetBoardResultSet;
import com.example.blog.board_back.repository.resultSet.GetCommentListResultSet;
import com.example.blog.board_back.repository.resultSet.GetFavoriteListResultSet;
import com.example.blog.board_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;

    @Override
    public ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email) {
        try {
            boolean existedEmail = userRepository.existsByEmail(email);
            if (!existedEmail) return PostBoardResponseDto.notExistUser();

            BoardEntity boardEntity = new BoardEntity(dto, email);
            boardRepository.save(boardEntity);

            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            int boardIdx = boardEntity.getBoardIdx();

            for (String image: boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardIdx, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardIdx) {
        GetBoardResultSet resultSet = null;
        List<ImageEntity> imageEntities = new ArrayList<>();
        try {
            resultSet = boardRepository.getBoard(boardIdx);
            if(resultSet == null) {
                return GetBoardResponseDto.notExistBoard();
            }
            imageEntities = imageRepository.findByBoardIdx(boardIdx);

            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBoardResponseDto.success(resultSet, imageEntities);
    }

    @Override
    public ResponseEntity<? super PutFavoriteResponseDto> putBoardFavorite(Integer boardIdx, String email) {
        try {
            boolean existUser = userRepository.existsByEmail(email);
            if(!existUser) return PutFavoriteResponseDto.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(boardEntity == null) return PutFavoriteResponseDto.noExistBoard();

            FavoriteEntity favoriteEntity = favoriteRepository.findByBoardIdxAndUserEmail(boardIdx, email);
            if(favoriteEntity == null) {
                favoriteEntity = new FavoriteEntity(email, boardIdx);
                favoriteRepository.save(favoriteEntity);
                boardEntity.increaseFavoriteCount();
            } else {
                favoriteRepository.delete(favoriteEntity);
                boardEntity.decreaseFavoriteCount();
            }
            boardRepository.save(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PutFavoriteResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardIdx) {
        List<GetFavoriteListResultSet> resultSets = new ArrayList<>();
        try {
            boolean existBoard = boardRepository.existsByBoardIdx(boardIdx);
            if(!existBoard) return GetFavoriteListResponseDto.noExistBoard();

            resultSets = favoriteRepository.getFavoriteList(boardIdx);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetFavoriteListResponseDto.success(resultSets);
    }

    @Override
    public ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardIdx, String email) {
        try {
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(boardEntity == null) return PostCommentResponseDto.noExistBoard();

            boolean existUser = userRepository.existsByEmail(email);
            if(!existUser) return PostCommentResponseDto.noExistUser();

            CommentEntity commentEntity = new CommentEntity(dto, boardIdx, email);
            commentRepository.save(commentEntity);

            boardEntity.increaseCommentCount();
            boardRepository.save(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PostCommentResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardIdx) {
        List<GetCommentListResultSet> resultSets = new ArrayList<>();
        try {
            boolean existBoard = boardRepository.existsByBoardIdx(boardIdx);
            if(!existBoard) return GetCommentListResponseDto.noExistBoard();

            resultSets = commentRepository.getCommentList(boardIdx);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetCommentListResponseDto.success(resultSets);
    }
}
