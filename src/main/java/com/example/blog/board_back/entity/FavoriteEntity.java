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
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favorite_idx;
    @Id
    private int user_idx;
    @Id
    private int board_idx;
}
