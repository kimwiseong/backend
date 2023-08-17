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

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school_record")
public class EducationalController {
    private final EducationalService educationalService;

    @PostMapping("/educational")
    public ResponseEntity<List<EducationalDto>> create(@RequestBody List<EducationalRequestDto> createRequest) {
        List<EducationalDto> createdDto = educationalService.create(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @GetMapping("/educational")
    public ResponseEntity<List<EducationalDto>> readAll() {
        List<EducationalDto> readDtos = educationalService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(readDtos);
    }

    @GetMapping("/educational/{id}")
    public ResponseEntity<EducationalDto> read(@PathVariable Long id) {
        EducationalDto readDto = educationalService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(readDto);
    }

    @PatchMapping("/educational/{id}")
    public ResponseEntity<EducationalDto> update(@PathVariable Long id, @RequestBody EducationalRequestDto updateRequest) {
        EducationalDto updatedDto = educationalService.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/educational/{id}")
    public ResponseEntity<EducationalDto> delete(@PathVariable Long id) {
        EducationalDto deletedDto = educationalService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
