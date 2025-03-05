package com.example.blog.board_back.repository;

import com.example.blog.board_back.entity.BoardListViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer> {
    List<BoardListViewEntity> findByOrderByRegDatetimeDesc();
    List<BoardListViewEntity> findTop3ByRegDatetimeGreaterThanOrderByFavoriteCountDescCommentCountDescViewCountDesc(String regdate);
}
