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
    private int board_idx;
    private String title;
    private String content;
    private String title_image;
    private int favorite_count;
    private int view_count;
    private int comment_count;
    private String reg_datetime;
    private String writer_nickname;
    private String email;
    private int user_idx;
    private String writer_profile_image;
}
