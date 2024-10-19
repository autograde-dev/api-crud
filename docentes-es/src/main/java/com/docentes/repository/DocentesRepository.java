package com.docentes.repository;


import com.docentes.entity.DocentesEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocentesRepository extends JpaRepository<DocentesEntity, Long>{
    Optional<DocentesEntity> findByDocumento(String documento);
}
