package com.example.blog.board_back.repository;

import com.example.blog.board_back.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    List<ImageEntity> findByBoardIdx(Integer boardIdx);

    @Transactional
    void deleteByBoardIdx(Integer boardIdx);

}
