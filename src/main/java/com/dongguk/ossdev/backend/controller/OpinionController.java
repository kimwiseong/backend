package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.OpinionRequestDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;
import com.dongguk.ossdev.backend.service.OpinionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/school_record")
public class OpinionController {
    private final OpinionService opinionService;

    @PostMapping("/opinion")
    public ResponseEntity<List<OpinionDto>> create(@RequestBody List<OpinionRequestDto> createRequest) {
        List<OpinionDto> createdDto = opinionService.create(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
    }

    @GetMapping("/opinion")
    public ResponseEntity<List<OpinionDto>> readAll() {
        List<OpinionDto> readDtos = opinionService.readAll();
        return ResponseEntity.status(HttpStatus.OK).body(readDtos);
    }

    @GetMapping("/opinion/{id}")
    public ResponseEntity<OpinionDto> read(@PathVariable Long id) {
        OpinionDto readDto = opinionService.read(id);
        return ResponseEntity.status(HttpStatus.OK).body(readDto);
    }

    @PatchMapping("/opinion/{id}")
    public ResponseEntity<OpinionDto> update(@PathVariable Long id, @RequestBody OpinionRequestDto updateRequest) {
        OpinionDto updatedDto = opinionService.update(id, updateRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedDto);
    }

    @DeleteMapping("/opinion/{id}")
    public ResponseEntity<OpinionDto> delete(@PathVariable Long id) {
        OpinionDto deletedDto = opinionService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(deletedDto);
    }
}
