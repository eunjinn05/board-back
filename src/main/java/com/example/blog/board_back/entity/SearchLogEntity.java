package com.example.blog.board_back.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="search_log")
@Table(name="search_log")
public class SearchLogEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int searchlog_idx;
    private String search_word;
    private String relation_word;
    private Boolean relation;
    private int sequence;

}
