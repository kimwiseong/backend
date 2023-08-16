package com.dongguk.ossdev.backend.controller;

import com.dongguk.ossdev.backend.dto.request.AwardRequestDto;
import com.dongguk.ossdev.backend.service.AwardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AwardController {

    private final AwardService awardService;

    @PostMapping("/award")
    public void create(@RequestBody List<AwardRequestDto> awardRequestDto) {

        for (AwardRequestDto request: awardRequestDto) {
            awardService.create(request);
        }
//         awardService.create(request);
    }

}
