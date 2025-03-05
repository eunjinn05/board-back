package com.example.blog.board_back.service;

import com.example.blog.board_back.dto.response.user.GetSignInUserResponseDto;
import com.example.blog.board_back.dto.response.user.GetUserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
}
