package com.tenco.codingtest.user;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.join(joinDTO);

        return "redirect:/login-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO loginDTO, HttpSession session) {
        User user = userService.login(loginDTO);
        session.setAttribute("sessionUser", user);

        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/update-form")
    public String updateForm(Model model,HttpSession session) {

        User sessionUser = (User)session.getAttribute("sessionUser");
        User user = userService.findById(sessionUser.getId());
        model.addAttribute("user",user);
        return "user/update-form";
    }

    @PostMapping("/update")
    public String update(HttpSession session, UserRequest.UpdateDTO updateDTO) {
        User user = (User) session.getAttribute("sessionUser");
        User updateUser = userService.update(updateDTO, user.getId());
        session.setAttribute("sessionUser", updateUser);

        return "redirect:/update-form";
    }
}
