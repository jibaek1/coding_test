package com.tenco.codingtest.board;

import com.tenco.codingtest.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class BoardRequest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SaveDTO {
        private String title;
        private String content;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(this.title)
                    .content(this.content)
                    .user(user)
                    .build();
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateDTO {
        private String title;
        private String content;
    }

}
