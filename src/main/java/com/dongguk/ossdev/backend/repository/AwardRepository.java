package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Award;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AwardRepository extends JpaRepository<Award, Long> {

    List<Award> findBySchoolRecordId(Long schoolRecordId);

    List<Award> findBySchoolRecordId(Long schoolRecordId, Sort date);

}
