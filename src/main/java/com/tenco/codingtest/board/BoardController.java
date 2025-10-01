package com.tenco.codingtest.board;

import com.tenco.codingtest.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @PostMapping("/save")
    public String save(BoardRequest.SaveDTO saveDTO, HttpSession session) {
        User sessionUser = (User)session.getAttribute("sessionUser");
        boardService.save(saveDTO,sessionUser);

        return "redirect:/";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable(name = "id") Long boardId,
                             HttpServletRequest request,
                             HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.checkBoardOwner(boardId,sessionUser.getId());
        request.setAttribute("boardId",sessionUser.getBoard());

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/board/{id}/update")
    public String update(@PathVariable(name = "id") Long boardId,
                         BoardRequest.UpdateDTO updateDTO,
                         HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.updateById(boardId,updateDTO,sessionUser);

        return "redirect:/board/" + boardId;
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable(name = "id") Long boardId, HttpSession session) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardService.deleteById(boardId,sessionUser);

        return "redirect:/";
    }

    @GetMapping("/")
    public String index(Model model) {
        List<Board> boardList = boardService.findAll();
        model.addAttribute("boardList",boardList);

        return "index";
    }

    @GetMapping("/board/{id}")
    public String detail(@PathVariable(name = "id") Long boardId,Model model) {
        model.addAttribute("board",boardService.findById(boardId));

        return "board/detail";
    }

}
