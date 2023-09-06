package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.UserRequestDto;
import com.dongguk.ossdev.backend.dto.response.UserDto;
import com.dongguk.ossdev.backend.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public ResponseEntity<UserDto> read(HttpServletRequest request) {
        log.info("get /user {}", Long.valueOf(request.getAttribute("USER_ID").toString()));
        UserDto userDto = userService.read(Long.valueOf(request.getAttribute("USER_ID").toString()));
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @PatchMapping("")
    public ResponseEntity<UserDto> update(HttpServletRequest request,
                                          @RequestBody UserRequestDto requestDto) {
        UserDto userDto = userService.update(Long.valueOf(request.getAttribute("USER_ID").toString()), requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(HttpServletRequest request) {
        userService.delete(Long.valueOf(request.getAttribute("USER_ID").toString()));
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
