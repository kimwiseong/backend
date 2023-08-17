package com.dongguk.ossdev.backend.service;

import com.dongguk.ossdev.backend.domain.SchoolRecord;
import com.dongguk.ossdev.backend.domain.User;
import com.dongguk.ossdev.backend.dto.response.SchoolRecordDto;
import com.dongguk.ossdev.backend.repository.SchoolRecordRepository;
import com.dongguk.ossdev.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SchoolRecordService {

    private final SchoolRecordRepository schoolRecordRepository;
    private final UserRepository userRepository;


    public SchoolRecordDto create(Long userId) {
        log.info("{}", userId);
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));

        // 연관 관계 설정
        SchoolRecord schoolRecord = new SchoolRecord();
        schoolRecord.setUser(user);

        schoolRecordRepository.save(schoolRecord);

        return SchoolRecordDto.builder()
                .school_record_id(schoolRecord.getId())
                .build();
    }
}
