package com.example.blog.board_back.dto.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FavoritListItem {
    private String email;
    private String nickname;
    private String profileImage;
}
