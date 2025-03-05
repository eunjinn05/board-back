package com.example.blog.board_back.controller;

import com.example.blog.board_back.dto.request.board.PatchBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
import com.example.blog.board_back.dto.response.GetSearchBoardListResponseDto;
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

    @DeleteMapping("/{boardIdx}")
    public ResponseEntity<? super DeleteBoardResponseDto> deleteBoard(@PathVariable("boardIdx") Integer boardIdx, @AuthenticationPrincipal String email) {
        ResponseEntity<? super DeleteBoardResponseDto> response = boardService.deleteBoard(boardIdx, email);
        return response;
    }

    @PatchMapping("/{boardIdx}")
    public ResponseEntity<? super PatchBoardResponseDto> patchBoard(@RequestBody @Valid PatchBoardRequestDto requestBody, @PathVariable("boardIdx") Integer boardIdx, @AuthenticationPrincipal String email) {
        ResponseEntity<? super PatchBoardResponseDto> response = boardService.patchBoard(requestBody, boardIdx, email);
        return response;
    }

    @GetMapping("/latest-list")
    public ResponseEntity<? super GetLatestBoardListResponseDto> getLatestBoardList() {
        ResponseEntity<? super GetLatestBoardListResponseDto> response = boardService.getLatestBoardList();
        return response;
    }

    @GetMapping("/top-3")
    public ResponseEntity<? super GetTop3BoardListResponseDto> getTop3BoardList() {
        ResponseEntity<? super GetTop3BoardListResponseDto> response = boardService.getTop3BoardList();
        return response;
    }

    @GetMapping(value = {"/search-list/{searchWord}", "/search-list/{searchWord}/{preSearchWord}"})
    public ResponseEntity<? super GetSearchBoardListResponseDto> getSearchBoardList(@PathVariable("searchWord") String searchWord, @PathVariable(value="preSearchWord", required = false) String preSearchWord) {
        ResponseEntity<? super GetSearchBoardListResponseDto> response= boardService.getSearchBoardList(searchWord, preSearchWord);
        return response;
    }
}
