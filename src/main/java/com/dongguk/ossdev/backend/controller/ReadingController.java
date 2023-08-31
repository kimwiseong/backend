package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.ReadingRequestDto;
import com.dongguk.ossdev.backend.dto.response.ReadingDto;
import com.dongguk.ossdev.backend.service.ReadingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school_record")
public class ReadingController {
    private final ReadingService readingService;

    @PostMapping("/reading/{schoolRecordId}")
    public ResponseEntity<List<ReadingDto>> create(@PathVariable Long schoolRecordId, @RequestBody List<ReadingRequestDto> createRequest) {
        List<ReadingDto> createdDto = readingService.create(schoolRecordId, createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @GetMapping("/reading/{schoolRecordId}")
    public ResponseEntity<List<ReadingDto>> read(@PathVariable Long schoolRecordId) {
        List<ReadingDto> readDtos = readingService.read(schoolRecordId);
        return ResponseEntity.status(HttpStatus.OK).body(readDtos);
    }

    @PatchMapping("/reading/{id}")
    public ResponseEntity<ReadingDto> update(@PathVariable Long id, @RequestBody ReadingRequestDto updateRequest) {
        ReadingDto updatedDto = readingService.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/reading/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        readingService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
