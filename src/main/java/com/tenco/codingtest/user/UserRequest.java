package com.tenco.codingtest.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class UserRequest {

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class JoinDTO {
        private String username;
        private Long age;
        private String loginId;
        private String password;
        private String email;

        public User toEntity() {
            return User.builder()
                    .username(this.username)
                    .age(this.age)
                    .loginId(this.loginId)
                    .password(this.password)
                    .email(this.email)
                    .build();
        }
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginDTO {
        private String loginId;
        private String password;
    }

    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class UpdateDTO {
        private String password;
        private String email;
        private String username;
    }
}
