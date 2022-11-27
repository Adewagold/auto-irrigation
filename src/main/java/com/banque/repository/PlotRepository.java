package com.banque.repository;

import com.banque.model.Plot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Plot Repository to handle database read-write operations
 */
@Repository
public interface PlotRepository extends JpaRepository<Plot,Long> {
    List<Plot> findAll();
}
