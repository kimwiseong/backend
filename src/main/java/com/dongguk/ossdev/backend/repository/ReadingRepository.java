package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Reading;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findBySchoolRecordId(Long schoolRecordId);

    List<Reading> findBySchoolRecordId(Long schoolRecordId, Sort grade);
}
