package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.annotation.UserId;
import com.dongguk.ossdev.backend.dto.response.SchoolRecordDto;
import com.dongguk.ossdev.backend.service.SchoolRecordService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/school_record")
public class SchoolRecordController {

    private final SchoolRecordService schoolRecordService;

    @PostMapping("")
    public ResponseEntity<SchoolRecordDto> create(@UserId Long userId) {
        SchoolRecordDto schoolRecordDto = schoolRecordService.create(userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(schoolRecordDto);
    }

    @GetMapping("")
    public ResponseEntity<SchoolRecordDto> read(@UserId Long userId) {
        SchoolRecordDto schoolRecordDto = schoolRecordService.readBySort(userId);
        return ResponseEntity.status(HttpStatus.OK).body(schoolRecordDto);
    }
}
