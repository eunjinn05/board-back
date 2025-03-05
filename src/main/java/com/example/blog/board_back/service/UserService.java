package com.example.blog.board_back.service;

import com.example.blog.board_back.dto.request.user.PatchNicknameRequestDto;
import com.example.blog.board_back.dto.request.user.PatchProfileImageRequestDto;
import com.example.blog.board_back.dto.response.user.GetSignInUserResponseDto;
import com.example.blog.board_back.dto.response.user.GetUserResponseDto;
import com.example.blog.board_back.dto.response.user.PatchNicknameResponseDto;
import com.example.blog.board_back.dto.response.user.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email);
    ResponseEntity<? super GetUserResponseDto> getUser(String email);
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);
}
