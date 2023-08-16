package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.Opinion;
import com.dongguk.ossdev.backend.dto.request.OpinionRequestDto;
import com.dongguk.ossdev.backend.dto.response.OpinionDto;

import com.dongguk.ossdev.backend.repository.OpinionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpinionService {
    private final OpinionRepository opinionRepository;

    @Transactional
    public List<OpinionDto> create(List<OpinionRequestDto> createRequest) {
        List<Opinion> createEntityList = createRequest.stream()
                .map(createOpinion -> createOpinion.toEntity())
                .collect(Collectors.toList());

        createEntityList.stream()
                .map(saveEntity -> opinionRepository.save(saveEntity))
                .collect(Collectors.toList());

        List<OpinionDto> createDtos = createEntityList.stream()
                .map(opinion -> OpinionDto.createOpinionDto(opinion))
                .collect(Collectors.toList());
        return createDtos;
    }

    public List<OpinionDto> readAll() {
        List<Opinion> opinionEntityList = opinionRepository.findAll();
        List<OpinionDto> showDtos = opinionEntityList.stream()
                .map(opinion -> OpinionDto.createOpinionDto(opinion))
                .collect(Collectors.toList());
        return showDtos;
    }

    public OpinionDto read(Long id) {
        Opinion opinionEntity = opinionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("행동 특성 및 종합의견을 찾을 수 없습니다."));
        OpinionDto showDto = OpinionDto.createOpinionDto(opinionEntity);
        return showDto;
    }

    @Transactional
    public OpinionDto update(Long id, OpinionRequestDto updateRequest) {
        Opinion target = opinionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("행동 특성 및 종합의견을 찾을 수 없습니다."));
        target.patch(updateRequest);
        Opinion updateOpinionEntity = updateRequest.toEntity();
        OpinionDto updatedDto = OpinionDto.createOpinionDto(updateOpinionEntity);
        return updatedDto;
    }

    @Transactional
    public OpinionDto delete(Long id) {
        Opinion target = opinionRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("독서 활동 상황을 찾을 수 없습니다."));
        OpinionDto deletedDto = OpinionDto.createOpinionDto(target);
        opinionRepository.delete(target);
        return deletedDto;
    }
}
