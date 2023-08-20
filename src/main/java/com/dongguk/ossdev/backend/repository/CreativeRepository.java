package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Creative;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CreativeRepository extends JpaRepository<Creative, Long> {

    List<Creative> findBySchoolRecordId(Long schoolRecordId);

    List<Creative> findBySchoolRecordId(Long schoolRecordId, Sort grade);

//    Optional<List<Creative>> findBySchoolRecordIdSortByDate(Long schoolRecordId);
}
