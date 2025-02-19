package com.example.blog.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment
    private int board_idx;
    private String title;
    private String content;
    private int user_idx;
    private int favorite_count;
    private int view_count;
    private String reg_datetime;

}
