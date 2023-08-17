package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Long> {

}
