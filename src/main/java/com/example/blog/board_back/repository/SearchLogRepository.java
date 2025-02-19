package com.example.blog.board_back.repository;

import com.example.blog.board_back.entity.SearchLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer> {
}
