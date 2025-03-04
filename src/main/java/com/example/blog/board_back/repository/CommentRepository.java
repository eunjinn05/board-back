package com.example.blog.board_back.repository;


import com.example.blog.board_back.entity.CommentEntity;
import com.example.blog.board_back.repository.resultSet.GetCommentListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {

    @Query(value = "SELECT U.nickname AS nickname, u.profile_image AS profileImage, c.reg_datetime AS regDate, c.content AS content " +
                        "FROM comment AS c " +
                        "INNER JOIN user AS u " +
                        "ON c.user_email = u.email " +
                        "WHERE c.board_idx = ?1 " +
                        "ORDER BY c.reg_datetime DESC", nativeQuery = true)
    List<GetCommentListResultSet> getCommentList(Integer boardIdx);

    @Transactional
    void deleteByBoardIdx(Integer boardIdx);
}
