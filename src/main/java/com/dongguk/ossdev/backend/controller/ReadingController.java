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

    @PostMapping("/reading")
    public ResponseEntity<ReadingDto> create(@RequestBody ReadingRequestDto createRequest) {
        ReadingDto createdDto = readingService.create(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @GetMapping("/reading")
    public ResponseEntity<List<ReadingDto>> readAll() {
        List<ReadingDto> readDtos = readingService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(readDtos);
    }

    @GetMapping("/reading/{id}")
    public ResponseEntity<ReadingDto> read(@PathVariable Long id) {
        ReadingDto readDto = readingService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(readDto);
    }

    @PatchMapping("/reading/{id}")
    public ResponseEntity<ReadingDto> update(@PathVariable Long id, @RequestBody ReadingRequestDto updateRequest) {
        ReadingDto updatedDto = readingService.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/reading/{id}")
    public ResponseEntity<ReadingDto> delete(@PathVariable Long id) {
        ReadingDto deletedDto = readingService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
