package com.example.blog.board_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board_list_view")
@Table(name="board_list_view")
public class BoardListViewEntity {
    @Id
    private int boardIdx;
    private String title;
    private String content;
    private String titleImage;
    private int favoriteCount;
    private int viewCount;
    private int commentCount;
    private String regDatetime;
    private String writerNickname;
    private String email;
    private String writer_profile_image;
}
