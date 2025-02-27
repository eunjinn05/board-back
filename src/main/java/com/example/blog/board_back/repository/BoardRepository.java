package com.example.blog.board_back.repository;

import com.example.blog.board_back.entity.BoardEntity;
import com.example.blog.board_back.repository.resultSet.GetBoardResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {

    BoardEntity findByBoardIdx(Integer boardIdx);

    @Query(value= "SELECT B.board_idx as board_idx, B.title as title, B.content as content," +
                        "B.reg_datetime as regDatetime, B.writer_email as writerEmail, U.nickname as writerNickname, " +
                        " U.profile_image as writerProfileImage" +
                    " FROM board AS B INNER JOIN user AS U " +
                    "ON B.writer_email = U.email WHERE board_idx = ?1", nativeQuery = true)
    GetBoardResultSet getBoard(Integer boardIdx);

    boolean existsByBoardIdx(Integer boardIdx);

}
