package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.annotation.UserId;
import com.dongguk.ossdev.backend.dto.response.ResponseDto;
import com.dongguk.ossdev.backend.dto.response.SchoolRecordDto;
import com.dongguk.ossdev.backend.service.SchoolRecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SchoolRecordController {

    private final SchoolRecordService schoolRecordService;

    @PostMapping("/school_record")
    public ResponseEntity<SchoolRecordDto> create(HttpServletRequest request) {
        SchoolRecordDto schoolRecordDto = schoolRecordService.create(Long.valueOf(request.getAttribute("USER_ID").toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolRecordDto);
    }

//    @PostMapping("/school_record")
//    public void create(@UserId Long userId) {
//        log.info("userId = {}", userId);
//
//        schoolRecordService.create(userId);
//
////        return ResponseEntity.ok(schoolRecordService.create(userId));
//    }

}
