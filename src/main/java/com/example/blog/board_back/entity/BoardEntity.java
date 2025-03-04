package com.example.blog.board_back.entity;

import com.example.blog.board_back.dto.request.board.PatchBoardRequestDto;
import com.example.blog.board_back.dto.request.board.PostBoardRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="board")
@Table(name="board")
public class BoardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  // auto increment
    private int boardIdx;
    private String title;
    private String content;
    private String writerEmail;
    private int favoriteCount;
    private int viewCount;
    private int commentCount;
    private String regDatetime;

    public BoardEntity(PostBoardRequestDto dto, String email) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
        this.favoriteCount = 0;
        this.commentCount = 0;
        this.viewCount = 0;
        this.writerEmail = email;

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.regDatetime = simpleDateFormat.format(now);
    }

    public void increaseViewCount() {
        this.viewCount++;
    }

    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }

    public void decreaseFavoriteCount() {
        this.favoriteCount--;
    }

    public void increaseCommentCount() {
        this.commentCount++;
    }

    public void patchBoard(PatchBoardRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}
