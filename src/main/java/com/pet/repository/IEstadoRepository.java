package com.pet.repository;

import com.pet.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEstadoRepository extends JpaRepository<Estado, Integer> {

}
