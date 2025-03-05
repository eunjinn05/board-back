package com.example.blog.board_back.dto.response.user;

import com.example.blog.board_back.common.ResponseCode;
import com.example.blog.board_back.common.ResponseMessage;
import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.board.PostBoardResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PatchProfileImageResponseDto extends ResponseDto {
    public PatchProfileImageResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PatchProfileImageResponseDto> success() {
        PatchProfileImageResponseDto result = new PatchProfileImageResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return PostBoardResponseDto.notExistUser();
    }

}
