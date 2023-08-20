package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Opinion;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    List<Opinion> findBySchoolRecordId(Long schoolRecordId);

    List<Opinion> findBySchoolRecordId(Long schoolRecordId, Sort grade);
}
