package com.example.blog.board_back.service;

import com.example.blog.board_back.dto.response.search.GetPopularListResponseDto;
import com.example.blog.board_back.dto.response.search.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
}
