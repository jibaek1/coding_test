package com.tenco.codingtest.board;

import com.tenco.codingtest.user.User;
import com.tenco.codingtest.utils.MyDateUtil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@Table(name = "board_tb")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String title;

    private String content;

    @CreationTimestamp
    private Timestamp createAt;

    @Transient
    private boolean isBoardOwner;

    public boolean isOwner(Long checkUserId) {
        return this.user.getId().equals(checkUserId);
    }

    public String getTime() {
        return MyDateUtil.timestampFormat(createAt);
    }
}
