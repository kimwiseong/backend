package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Career;
import com.dongguk.ossdev.backend.domain.SchoolRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SchoolRecordRepository extends JpaRepository<SchoolRecord, Long> {
    Optional<SchoolRecord> findByUserId(Long userId);

    @Query("select s.id from SchoolRecord s where s.user.id = :userId")
    Optional<Long> findSchoolRecordIdByUserId(@Param("userId") Long userId);
}
