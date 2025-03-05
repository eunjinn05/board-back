package com.example.blog.board_back.service.implement;

import com.example.blog.board_back.dto.request.board.PatchBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
import com.example.blog.board_back.dto.response.GetSearchBoardListResponseDto;
import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.board.*;
import com.example.blog.board_back.entity.*;
import com.example.blog.board_back.repository.*;
import com.example.blog.board_back.repository.resultSet.GetBoardResultSet;
import com.example.blog.board_back.repository.resultSet.GetCommentListResultSet;
import com.example.blog.board_back.repository.resultSet.GetFavoriteListResultSet;
import com.example.blog.board_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImplement implements BoardService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;
    private final ImageRepository imageRepository;
    private final FavoriteRepository favoriteRepository;
    private final CommentRepository commentRepository;
    private final BoardListViewRepository boardListViewRepository;
    private final SearchLogRepository searchLogRepository;

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

    @Override
    public ResponseEntity<? super IncreaseViewCountResponseDto> getIncreaseViewCount(Integer boardIdx) {
        try {
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(boardEntity == null) return IncreaseViewCountResponseDto.noExistBoard();

            boardEntity.increaseViewCount();
            boardRepository.save(boardEntity);

        } catch(Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return IncreaseViewCountResponseDto.success();
    }

    @Override
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardIdx, String email) {
        try {
            boolean existUser = userRepository.existsByEmail(email);
            if (!existUser) return DeleteBoardResponseDto.noExistUser();

            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(boardEntity == null) return DeleteBoardResponseDto.noExistBoard();

            String writerEmail = boardEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return DeleteBoardResponseDto.noPermission();

            imageRepository.deleteByBoardIdx(boardIdx);
            commentRepository.deleteByBoardIdx(boardIdx);
            favoriteRepository.deleteByBoardIdx(boardIdx);

            boardRepository.delete(boardEntity);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return DeleteBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardIdx, String email) {
        try {
            BoardEntity boardEntity = boardRepository.findByBoardIdx(boardIdx);
            if(boardEntity == null) return PatchBoardResponseDto.notExistBoard();

            boolean existedUser = userRepository.existsByEmail(email);
            if(!existedUser) return PatchBoardResponseDto.notExistUser();

            String writerEmail = boardEntity.getWriterEmail();
            boolean isWriter = writerEmail.equals(email);
            if(!isWriter) return PatchBoardResponseDto.noPermission();

            boardEntity.patchBoard(dto);
            boardRepository.save(boardEntity);

            imageRepository.deleteByBoardIdx(boardIdx);
            List<String> boardImageList = dto.getBoardImageList();
            List<ImageEntity> imageEntities = new ArrayList<>();

            for (String image: boardImageList) {
                ImageEntity imageEntity = new ImageEntity(boardIdx, image);
                imageEntities.add(imageEntity);
            }
            imageRepository.saveAll(imageEntities);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return PatchBoardResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {

        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();

        try {
            boardListViewEntities = boardListViewRepository.findByOrderByRegDatetimeDesc();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetLatestBoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
        try {
            Date beforeWeek = Date.from(Instant.now().minus(7, ChronoUnit.DAYS));
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String sevenDaysAgo = simpleDateFormat.format(beforeWeek);
            boardListViewEntities = boardListViewRepository.findTop3ByRegDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDesc(sevenDaysAgo);


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetTop3BoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord) {
        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
        try {
            boardListViewEntities = boardListViewRepository.findByTitleContainsOrContentContainsOrderByRegDatetimeDesc(searchWord, searchWord);

            SearchLogEntity searchLogEntity = new SearchLogEntity(searchWord, preSearchWord, false);
            searchLogRepository.save(searchLogEntity);

            boolean relation = preSearchWord != null;
            if(relation) {
                searchLogEntity = new SearchLogEntity(preSearchWord, searchWord, relation);
                searchLogRepository.save(searchLogEntity);
            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSearchBoardListResponseDto.success(boardListViewEntities);
    }

    @Override
    public ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email) {
        List<BoardListViewEntity> boardListViewEntities = new ArrayList<>();
        try {
            boolean existUser = userRepository.existsByEmail(email);
            if(!existUser) return GetUserBoardListResponseDto.noExistUser();

            boardListViewEntities = boardListViewRepository.findByWriterEmailOrderByRegDatetimeDesc(email);

        } catch (Exception e) {
            e.printStackTrace();
            ResponseDto.databaseError();
        }
        return GetUserBoardListResponseDto.success(boardListViewEntities);
    }
}
