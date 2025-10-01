package com.tenco.codingtest.user;

import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void join(UserRequest.JoinDTO joinDTO) {

        if (userRepository.existsByLoginId(joinDTO.getLoginId())) {
            throw new DuplicateRequestException("이미 사용 중인 아이디입니다.");
        }
        User user = joinDTO.toEntity();

        userRepository.save(user);
    }

    @Transactional
    public User login(UserRequest.LoginDTO loginDTO) {
        User user = userRepository.findByLoginId(loginDTO.getLoginId())
                .orElseThrow(() -> new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다."));

        if (!Objects.equals(loginDTO.getPassword(), user.getPassword())) {
            throw new RuntimeException("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
        return user;
    }

    @Transactional
    public User update(UserRequest.UpdateDTO updateDTO, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("회원 정보를 찾을 수 없습니다."));

        user.setPassword(updateDTO.getPassword());
        user.setEmail(updateDTO.getEmail());
        user.setUsername(updateDTO.getUsername());
        return user;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> {
            return new RuntimeException("사용자를 찾을 수 없습니다.");
        });
    }
}
