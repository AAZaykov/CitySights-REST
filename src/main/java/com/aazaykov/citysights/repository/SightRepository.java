package com.aazaykov.citysights.repository;

import com.aazaykov.citysights.entity.Sight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SightRepository extends JpaRepository<Sight, Long> {
}
