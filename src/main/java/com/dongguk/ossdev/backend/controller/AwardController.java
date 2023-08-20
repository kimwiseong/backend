package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.AwardRequestDto;
import com.dongguk.ossdev.backend.dto.response.AwardDto;
import com.dongguk.ossdev.backend.service.AwardService;
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
public class AwardController {

    private final AwardService awardService;

    @PostMapping("/award/{schoolRecordId}")
    public ResponseEntity<List<AwardDto>> create(@PathVariable Long schoolRecordId,
                                                 @RequestBody List<AwardRequestDto> requestDtoList) {
        log.info("award create controller");
        List<AwardDto> awardDtoList = awardService.create(schoolRecordId, requestDtoList);
        return ResponseEntity.status(HttpStatus.CREATED).body(awardDtoList);
    }

    @GetMapping("/award/{schoolRecordId}")
    public ResponseEntity<List<AwardDto>> read(@PathVariable Long schoolRecordId) {
        List<AwardDto> awardDtoList = awardService.read(schoolRecordId);
        return ResponseEntity.status(HttpStatus.OK).body(awardDtoList);
    }

    @PatchMapping("/award/{awardId}")
    public ResponseEntity<AwardDto> update(@PathVariable Long awardId,
                                           @RequestBody AwardRequestDto requestDto) {
        AwardDto awardDto = awardService.updateById(awardId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(awardDto);
    }

    @DeleteMapping("/award/{awardId}")
    public ResponseEntity<?> delete(@PathVariable Long awardId) {
        awardService.deleteById(awardId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
