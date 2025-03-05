package com.example.blog.board_back.dto.response.user;

import com.example.blog.board_back.common.ResponseCode;
import com.example.blog.board_back.common.ResponseMessage;
import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.auth.SignUpResponseDto;
import com.example.blog.board_back.dto.response.board.PostBoardResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchNicknameResponseDto extends ResponseDto {
    private PatchNicknameResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchNicknameResponseDto> success() {
        PatchNicknameResponseDto result = new PatchNicknameResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return PostBoardResponseDto.notExistUser();
    }

    public static ResponseEntity<ResponseDto> duplicateNickname() {
        return SignUpResponseDto.duplicateNickname();
    }

}
