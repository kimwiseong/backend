package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.EducationalRequestDto;
import com.dongguk.ossdev.backend.dto.request.OpinionRequestDto;
import com.dongguk.ossdev.backend.dto.response.EducationalDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;
import com.dongguk.ossdev.backend.service.EducationalService;
import com.dongguk.ossdev.backend.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school_record")
public class EducationalController {
    private final EducationalService educationalService;

    @PostMapping("/educational/{schoolRecordId}")
    public ResponseEntity<List<EducationalDto>> create(@PathVariable Long schoolRecordId, @RequestBody List<EducationalRequestDto> createRequest) {
        List<EducationalDto> createdDto = educationalService.create(schoolRecordId, createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @GetMapping("/educational/{schoolRecordId}")
    public ResponseEntity<List<EducationalDto>> read(@PathVariable Long schoolRecordId) {
        List<EducationalDto> readDtos = educationalService.read(schoolRecordId);
        return ResponseEntity.status(HttpStatus.OK).body(readDtos);
    }

    @PatchMapping("/educational/{id}")
    public ResponseEntity<EducationalDto> update(@PathVariable Long id, @RequestBody EducationalRequestDto updateRequest) {
        EducationalDto updatedDto = educationalService.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/educational/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        educationalService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
