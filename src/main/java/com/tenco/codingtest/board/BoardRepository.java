package com.tenco.codingtest.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board,Long> {
    @Query("select b from Board b join fetch b.user u order by b.id desc")
    List<Board> findAllJoinUser();
    @Query("select b from Board b join fetch b.user order by b.id desc")
    Optional<Board> findByIdJoinUser(@Param("id") Long id);
}
