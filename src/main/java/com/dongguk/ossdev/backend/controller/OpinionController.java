package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.OpinionRequestDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;
import com.dongguk.ossdev.backend.service.OpinionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/school_record")
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/opinion/{schoolRecordId}")
    public ResponseEntity<List<OpinionDto>> create(@PathVariable Long schoolRecordId, @RequestBody List<OpinionRequestDto> createRequest) {
        log.info("con");
        List<OpinionDto> createdDto = opinionService.create(schoolRecordId, createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @GetMapping("/opinion/{schoolRecordId}")
    public ResponseEntity<List<OpinionDto>> read(@PathVariable Long schoolRecordId) {
        List<OpinionDto> readDtos = opinionService.read(schoolRecordId);
        return ResponseEntity.status(HttpStatus.OK).body(readDtos);
    }

    @PatchMapping("/opinion/{id}")
    public ResponseEntity<OpinionDto> update(@PathVariable Long id, @RequestBody OpinionRequestDto updateRequest) {
        OpinionDto updatedDto = opinionService.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/opinion/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        opinionService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
