package com.example.blog.board_back.service;

import com.example.blog.board_back.dto.request.board.PatchBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
import com.example.blog.board_back.dto.response.GetSearchBoardListResponseDto;
import com.example.blog.board_back.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardIdx);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putBoardFavorite(Integer boardIdx, String email);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardIdx);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardIdx, String email);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardIdx);
    ResponseEntity<? super IncreaseViewCountResponseDto> getIncreaseViewCount(Integer boardIdx);
    ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(Integer boardIdx, String email);
    ResponseEntity<? super PatchBoardResponseDto> patchBoard(PatchBoardRequestDto dto, Integer boardIdx, String email);
    ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList();
    ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList();
    ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(String searchWord, String preSearchWord);
    ResponseEntity<? super GetUserBoardListResponseDto> getUserBoardList(String email);
}
