package com.example.blog.board_back.service.implement;

import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.board.GetBoardResponseDto;
import com.example.blog.board_back.dto.response.board.PostBoardResponseDto;
import com.example.blog.board_back.dto.response.board.PutFavoriteResponseDto;
import com.example.blog.board_back.entity.BoardEntity;
import com.example.blog.board_back.entity.FavoriteEntity;
import com.example.blog.board_back.entity.ImageEntity;
import com.example.blog.board_back.repository.BoardRepository;
import com.example.blog.board_back.repository.FavoriteRepository;
import com.example.blog.board_back.repository.ImageRepository;
import com.example.blog.board_back.repository.UserRepository;
import com.example.blog.board_back.repository.resultSet.GetBoardResultSet;
import com.example.blog.board_back.service.BoardService;
import lombok.RequiredArgsConstructor;
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
}
