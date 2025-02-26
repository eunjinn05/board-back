package com.example.blog.board_back.controller;

import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import com.example.blog.board_back.dto.response.board.GetBoardResponseDto;
import com.example.blog.board_back.dto.response.board.PostBoardResponseDto;
import com.example.blog.board_back.dto.response.board.PutFavoriteResponseDto;
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
}
