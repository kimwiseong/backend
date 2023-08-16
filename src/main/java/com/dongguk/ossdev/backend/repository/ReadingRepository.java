package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingRepository extends JpaRepository<Reading, Long> {

}
