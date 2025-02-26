package com.example.blog.board_back.entity;

import com.example.blog.board_back.dto.request.board.PostCommentRequestDto;
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
@Entity(name="comment")
@Table(name="comment")
public class CommentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentIdx;
    private String userEmail;
    private int boardIdx;
    private String content;
    private String regDatetime;

    public CommentEntity (PostCommentRequestDto dto, Integer boardIdx, String email) {

        Date now = Date.from(Instant.now());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String writeDatetime = simpleDateFormat.format(now);

        this.content = dto.getContent();
        this.regDatetime = writeDatetime;
        this.userEmail = email;
        this.boardIdx = boardIdx;
    }
}
