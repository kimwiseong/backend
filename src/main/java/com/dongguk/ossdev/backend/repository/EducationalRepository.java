package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Educational;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalRepository extends JpaRepository<Educational, Long> {

}
