package com.example.blog.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="favorite")
@Table(name="favorite")
@IdClass(FavoriteEntity.class)
public class FavoriteEntity {
    @Id
    private String userEmail;
    @Id
    private int boardIdx;
}
