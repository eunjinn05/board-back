package com.example.blog.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="image")
@Table(name="image")
public class ImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence;
    private String image;
    private int boardIdx;


    public ImageEntity(Integer boardIdx, String image) {
        this.boardIdx = boardIdx;
        this.image = image;
    }
}
