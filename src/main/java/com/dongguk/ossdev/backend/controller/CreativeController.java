package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.CareerRequestDto;
import com.dongguk.ossdev.backend.dto.request.CreativeRequestDto;
import com.dongguk.ossdev.backend.dto.response.CareerDto;
import com.dongguk.ossdev.backend.dto.response.CreativeDto;
import com.dongguk.ossdev.backend.service.CreativeService;
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
public class CreativeController {

    private final CreativeService creativeService;

    @PostMapping("/creative/{schoolRecordId}")
    public ResponseEntity<List<CreativeDto>> create(@PathVariable Long schoolRecordId,
                                                    @RequestBody List<CreativeRequestDto> requestDtoList) {
        List<CreativeDto> creativeDtoList = creativeService.create(schoolRecordId, requestDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(creativeDtoList);
    }

    @GetMapping("/creative/{schoolRecordId}")
    public ResponseEntity<List<CreativeDto>> read(@PathVariable Long schoolRecordId) {
        List<CreativeDto> creativeDtoList = creativeService.read(schoolRecordId);
        return ResponseEntity.status(HttpStatus.OK).body(creativeDtoList);
    }

    @PatchMapping("/creative/{creativeId}")
    public ResponseEntity<CreativeDto> update(@PathVariable Long creativeId,
                                            @RequestBody CreativeRequestDto requestDto) {
        CreativeDto creativeDto = creativeService.updateById(creativeId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(creativeDto);
    }

    @DeleteMapping("/creative/{creativeId}")
    public ResponseEntity<?> delete(@PathVariable Long creativeId) {
        creativeService.deleteById(creativeId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
