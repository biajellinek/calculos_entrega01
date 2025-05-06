package app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.entity.Calculo;

public interface CalculoRepository extends JpaRepository<Calculo, Long>{

}
