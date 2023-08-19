package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.CareerRequestDto;
import com.dongguk.ossdev.backend.dto.response.CareerDto;
import com.dongguk.ossdev.backend.service.CareerService;
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
public class CareerController {

    private final CareerService careerService;

    @PostMapping("/career/{schoolRecordId}")
    public ResponseEntity<List<CareerDto>> create(@PathVariable Long schoolRecordId,
                                                  @RequestBody List<CareerRequestDto> requestDtoList) {
        List<CareerDto> careerDtoList = careerService.create(schoolRecordId, requestDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(careerDtoList);
    }

    @GetMapping("/career/{schoolRecordId}")
    public ResponseEntity<List<CareerDto>> read(@PathVariable Long schoolRecordId) {
        List<CareerDto> careerDtoList = careerService.read(schoolRecordId);
        return ResponseEntity.status(HttpStatus.OK).body(careerDtoList);
    }

    @PatchMapping("/career/{careerId}")
    public ResponseEntity<CareerDto> update(@PathVariable Long careerId,
                                            @RequestBody CareerRequestDto requestDto) {
        CareerDto careerDto = careerService.updateById(careerId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(careerDto);
    }

    @DeleteMapping("/career/{careerId}")
    public ResponseEntity<?> delete(@PathVariable Long careerId) {
        careerService.deleteById(careerId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
