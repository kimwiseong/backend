package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Educational;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EducationalRepository extends JpaRepository<Educational, Long> {
    List<Educational> findBySchoolRecordId(Long schoolRecordId);

    List<Educational> findBySchoolRecordId(Long schoolRecordId, Sort grade);
}
