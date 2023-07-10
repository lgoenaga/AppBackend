package com.iudigital.appbackend.repository;

import com.iudigital.appbackend.model.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {

    boolean existsByUserId(Long userId);

    List<Case> findAllByUserId(Long userId);
}
