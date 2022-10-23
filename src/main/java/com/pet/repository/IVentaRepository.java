package com.pet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pet.model.Venta;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Integer> {

	List<Venta> findByEstado(String estado);
}
