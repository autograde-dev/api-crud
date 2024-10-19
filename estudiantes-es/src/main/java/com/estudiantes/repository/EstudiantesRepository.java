package com.estudiantes.repository;


import com.estudiantes.entity.EstudiantesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstudiantesRepository extends JpaRepository<EstudiantesEntity, Long>{
 Optional<EstudiantesEntity> findByDocumento(String documento);
}
