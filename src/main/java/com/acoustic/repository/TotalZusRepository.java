package com.acoustic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acoustic.entity.TotalZus;

@Repository
public interface TotalZusRepository extends JpaRepository<TotalZus, Long> {



}
