package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.SchoolRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRecordRepository extends JpaRepository<SchoolRecord, Long> {
}
