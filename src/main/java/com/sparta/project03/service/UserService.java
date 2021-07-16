package com.sparta.project03.service;

import com.sparta.project03.dto.SignupRequestDto;
import com.sparta.project03.domain.User;
import com.sparta.project03.exception.ApiRequestException;
import com.sparta.project03.repository.UserRepository;
import com.sparta.project03.utill.SignupValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto requestDto) {
        //ID
        String username = requestDto.getUsername();
        //정규식 검사
        if(!SignupValidator.idValid(username)){
            throw new ApiRequestException("유효하지 않은 아이디입니다.");
        }
        //중복검사
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new ApiRequestException("중복된 아이디입니다.");
        }

        //PW
        String password = requestDto.getPassword();
        String passwordConfirm = requestDto.getPasswordConfirm();
        //정규식 검사
        if(!SignupValidator.pwValid(username, password)){
            throw new ApiRequestException("유효하지 않은 비밀번호입니다.");
        }
        //비밀번호 재입력 일치 검사
        if (!password.equals(passwordConfirm)) {
            throw new ApiRequestException("비밀번호가 일치하지 않습니다.");
        }
        //모든조건 충족시 비밀번호 암호화
        password = passwordEncoder.encode(requestDto.getPassword());

        //Email
        String email = requestDto.getEmail();
        if (!SignupValidator.emailValid(email)){
            throw new ApiRequestException("유효하지 않은 이메일주소입니다.");
        }

        // 모든 조건 통과 및 암호화된 사용자 계정정보를 DB에 저장
        User user = new User(username, password, email);
        userRepository.save(user);
    }
}