package com.example.blog.board_back.dto.response.board;

import com.example.blog.board_back.common.ResponseCode;
import com.example.blog.board_back.common.ResponseMessage;
import com.example.blog.board_back.dto.object.BoardListItem;
import com.example.blog.board_back.dto.response.ResponseDto;
import com.example.blog.board_back.dto.response.user.GetUserResponseDto;
import com.example.blog.board_back.entity.BoardListViewEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GetUserBoardListResponseDto extends ResponseDto {
    private List<BoardListItem> userBoardList;

    public GetUserBoardListResponseDto(List<BoardListViewEntity> boardListViewEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        List<BoardListItem> userBoardList = new ArrayList<>();
        this.userBoardList = BoardListItem.getList(boardListViewEntities);
    }

    public static ResponseEntity<GetUserBoardListResponseDto> success(List<BoardListViewEntity> boardListViewEntities) {
        GetUserBoardListResponseDto result = new GetUserBoardListResponseDto(boardListViewEntities);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistUser() {
        return PostBoardResponseDto.notExistUser();
    }

}
