package com.tenco.codingtest.board;

import com.tenco.codingtest.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Board save(BoardRequest.SaveDTO saveDTO, User sessionUser) {

        Board board = saveDTO.toEntity(sessionUser);

        Board saved = boardRepository.save(board);
        return saved;
    }

    @Transactional(readOnly = true)
    public List<Board> findAll() {
        List<Board> boardList = boardRepository.findAllJoinUser();
        return boardList;
    }

    @Transactional(readOnly = true)
    public Board findById(Long id) {
        Board board = boardRepository.findByIdJoinUser(id).orElseThrow(() -> {
            return new RuntimeException("게시글을 찾을 수 없습니다.");
        });
        return board;
    }

    @Transactional
    public Board updateById(Long id, BoardRequest.UpdateDTO updateDTO,
                            User sessionUser) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> {
                    return new RuntimeException("해당 게시글을 찾을 수 없습니다");
                });

        board.setTitle(updateDTO.getTitle());
        board.setContent(updateDTO.getContent());

        return board;
    }

    @Transactional
    public void deleteById(Long id, User sessionUser) {

        Board board = boardRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("삭제하려는 게시글이 없습니다");
        });
        boardRepository.deleteById(id);
    }

    public void checkBoardOwner(Long boardId, Long userId) {
        Board board = findById(boardId);
        if (!board.isOwner(userId)) {
            throw new RuntimeException("본인 게시글만 수정할 수 있습니다.");
        }
    }
}
