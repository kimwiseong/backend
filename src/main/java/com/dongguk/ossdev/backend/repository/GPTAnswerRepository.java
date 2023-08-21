package com.dongguk.ossdev.backend.repository;

import com.dongguk.ossdev.backend.domain.GPTAnswer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GPTAnswerRepository extends JpaRepository<GPTAnswer, Long> {

}
