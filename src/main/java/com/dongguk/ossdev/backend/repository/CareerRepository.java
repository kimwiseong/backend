package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Career;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CareerRepository extends JpaRepository<Career, Long> {

    Optional<List<Career>> findBySchoolRecordId(Long schoolRecordId);

    Optional<List<Career>> findBySchoolRecordId(Long schoolRecordId, Sort grade);

//    Optional<List<Career>> findBySchoolRecordIdSortByDate(Long schoolRecordId);
}
