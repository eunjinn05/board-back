package com.example.blog.board_back.dto.response.board;

import com.example.blog.board_back.common.ResponseCode;
import com.example.blog.board_back.common.ResponseMessage;
import com.example.blog.board_back.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class PutFavoriteResponseDto extends ResponseDto {
    private PutFavoriteResponseDto() {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
    }

    public static ResponseEntity<PutFavoriteResponseDto> success () {
        PutFavoriteResponseDto result = new PutFavoriteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistBoard() {
        return GetBoardResponseDto.notExistBoard();
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return PostBoardResponseDto.notExistUser();
    }


}
