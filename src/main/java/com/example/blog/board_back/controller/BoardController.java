package com.example.blog.board_back.controller;

import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
import com.example.blog.board_back.dto.response.board.*;
import com.example.blog.board_back.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("{boardIdx}")
    public ResponseEntity<? super GetBoardResponseDto> getBoard(@PathVariable("boardIdx") Integer boardIdx) {
        ResponseEntity<? super GetBoardResponseDto> response = boardService.getBoard(boardIdx);
        return response;
    }

    @PostMapping("")
    public ResponseEntity<? super PostBoardResponseDto> postBoard(@RequestBody @Valid PostBoardRequestDto requestBody, @AuthenticationPrincipal String email) {
        ResponseEntity<? super PostBoardResponseDto> response = boardService.postBoard(requestBody, email);
        return response;
    }

    @PutMapping("/{boardIdx}/favorite")
    public ResponseEntity<? super PutFavoriteResponseDto> putFavorite(@PathVariable("boardIdx") Integer boardIdx, @AuthenticationPrincipal String email) {
        ResponseEntity<? super PutFavoriteResponseDto> response = boardService.putBoardFavorite(boardIdx, email);
        return response;
    }

    @GetMapping("/{boardIdx}/favorite-list")
    public ResponseEntity<? super GetFavoriteListResponseDto> getFavoriteList(@PathVariable("boardIdx") Integer boardIdx) {
        ResponseEntity<? super GetFavoriteListResponseDto> response = boardService.getFavoriteList(boardIdx);
        return response;
    }

    @PostMapping("/{boardIdx}/comment")
    public ResponseEntity<? super PostCommentResponseDto> postComment(@RequestBody @Valid PostCommentRequestDto requestBody, @PathVariable("boardIdx") Integer boardIdx, @AuthenticationPrincipal String email) {
        ResponseEntity<? super PostCommentResponseDto> response = boardService.postComment(requestBody, boardIdx, email);
        return response;
    }

    @GetMapping("/{boardIdx}/comment-list")
    public ResponseEntity<? super GetCommentListResponseDto> getCommentList(@PathVariable("boardIdx") Integer boardIdx) {
        ResponseEntity<? super GetCommentListResponseDto> response = boardService.getCommentList(boardIdx);
        return response;
    }

    @GetMapping("/{boardIdx}/increase-view-count")
    public ResponseEntity<? super IncreaseViewCountResponseDto> increaseViewCount(@PathVariable("boardIdx") Integer boardIdx) {
        ResponseEntity<? super IncreaseViewCountResponseDto> response = boardService.getIncreaseViewCount(boardIdx);
        return response;
    }


}
