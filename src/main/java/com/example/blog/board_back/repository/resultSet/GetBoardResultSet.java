package com.example.blog.board_back.repository.resultSet;

public interface GetBoardResultSet {
    Integer getBoardIdx();
    String getTitle();
    String getContent();
    String getRegdatetime();
    String getWriterEmail();
    String getWriterNickname();
    String getWriterProfileImage();
}
