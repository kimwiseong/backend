package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Award;
import com.dongguk.ossdev.backend.dto.request.AwardRequestDto;
import com.dongguk.ossdev.backend.repository.AwardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AwardService {

    private final AwardRepository awardRepository;

    public void create(AwardRequestDto awardRequestDto) {
        Award award = awardRequestDto.toEntity();
        awardRepository.save(award);
    }
}
