package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.GPTQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPTQuestionRepository extends JpaRepository<GPTQuestion, Long> {
}
