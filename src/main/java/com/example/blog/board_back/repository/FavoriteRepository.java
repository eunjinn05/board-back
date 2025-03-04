package com.example.blog.board_back.repository;

import com.example.blog.board_back.entity.FavoriteEntity;
import com.example.blog.board_back.entity.primaryKey.FavoritePk;
import com.example.blog.board_back.repository.resultSet.GetFavoriteListResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, FavoritePk> {

    FavoriteEntity findByBoardIdxAndUserEmail(Integer boardIdx, String email);

    @Query(value = "SELECT " +
                        "U.email AS email, U.nickname AS nickname, U.profile_image AS profileImage " +
                    "FROM favorite AS f " +
                    "INNER JOIN user AS U " +
                    "ON f.user_email = U.email " +
                    "WHERE f.board_idx=?1", nativeQuery = true)
    List<GetFavoriteListResultSet> getFavoriteList(Integer boardIdx);

    @Transactional
    void deleteByBoardIdx(Integer boardIdx);
}
