package com.tenco.codingtest.user;

import com.tenco.codingtest.board.Board;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "user_tb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private Long age;

    @ManyToOne(fetch = FetchType.LAZY,optional = true)
    @JoinColumn(name = "board_id")
    private Board board;

    private String loginId;

    private String password;

    private String email;

    @CreationTimestamp
    private Timestamp createAt;
}
