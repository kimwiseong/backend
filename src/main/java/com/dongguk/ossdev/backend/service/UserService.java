package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.User;
import com.dongguk.ossdev.backend.dto.request.UserRequestDto;
import com.dongguk.ossdev.backend.dto.response.UserDto;
import com.dongguk.ossdev.backend.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserDto read(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        return UserDto.createUserDto(user);
    }

    @Transactional
    public UserDto update(Long userId, UserRequestDto userRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        user.update(userRequestDto);
        return UserDto.createUserDto(user);
    }

    public void delete(Long userId) {
        userRepository.delete(
                userRepository.findById(userId)
                        .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다.")));
    }
}
