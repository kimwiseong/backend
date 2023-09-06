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
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/school_record")
public class SchoolRecordController {

    private final SchoolRecordService schoolRecordService;

    @PostMapping("")
    public ResponseEntity<SchoolRecordDto> create(HttpServletRequest request) {
        SchoolRecordDto schoolRecordDto = schoolRecordService
                .create(Long.valueOf(request.getAttribute("USER_ID").toString()));

        return ResponseEntity.status(HttpStatus.CREATED).body(schoolRecordDto);
    }

    @GetMapping("")
    public ResponseEntity<SchoolRecordDto> read(HttpServletRequest request) {
        SchoolRecordDto schoolRecordDto = schoolRecordService
                .readBySort(Long.valueOf(request.getAttribute("USER_ID").toString()));
        return ResponseEntity.status(HttpStatus.OK).body(schoolRecordDto);
    }
}
