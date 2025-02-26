package com.example.blog.board_back.service;

import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
import com.example.blog.board_back.dto.response.board.*;
import org.springframework.http.ResponseEntity;

public interface BoardService {
    ResponseEntity<? super GetBoardResponseDto> getBoard(Integer boardIdx);
    ResponseEntity<? super PostBoardResponseDto> postBoard(PostBoardRequestDto dto, String email);
    ResponseEntity<? super PutFavoriteResponseDto> putBoardFavorite(Integer boardIdx, String email);
    ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(Integer boardIdx);
    ResponseEntity<? super PostCommentResponseDto> postComment(PostCommentRequestDto dto, Integer boardIdx, String email);
    ResponseEntity<? super GetCommentListResponseDto> getCommentList(Integer boardIdx);
}
