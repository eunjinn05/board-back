package com.example.blog.board_back.service.implement;

import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.user.GetSignInUserResponseDto;
import com.example.blog.board_back.entity.UserEntity;
import com.example.blog.board_back.repository.UserRepository;
import com.example.blog.board_back.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImplement implements UserService {
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super GetSignInUserResponseDto> getSignInUser(String email) {
        UserEntity userEntity = null;
        try {
            userEntity = userRepository.findByEmail(email);
            if (userEntity == null) return GetSignInUserResponseDto.notExistUser();

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetSignInUserResponseDto.success(userEntity);
    }
}
