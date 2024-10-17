package com.estudiantes.estudiantes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.estudiantes.estudiantes.entity.EstudiantesEntity;
import com.estudiantes.estudiantes.repository.EstudiantesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstudiantesService {
    @Autowired
    EstudiantesRepository estudiantesRepository;

    public List<EstudiantesEntity> getEstudiantes(){
        return (List<EstudiantesEntity>) estudiantesRepository.findAll();
    }

    public EstudiantesEntity getEstudiantesbyId(long id){
        return estudiantesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + id));
    }

    public boolean save(EstudiantesEntity estudiantesEntity){
        
        estudiantesRepository.save(estudiantesEntity);
        return true;
    }

    public boolean deleteById(Long id){
        
        estudiantesRepository.deleteById(id);
        return true;
    }

    public boolean updateById(Long id, EstudiantesEntity estudiantesEntity){
        
        EstudiantesEntity estudiante = estudiantesRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + id));
        estudiante.setNombres(estudiantesEntity.getNombres());
        estudiante.setApellidos(estudiantesEntity.getApellidos());
        estudiante.setDocumento(estudiantesEntity.getDocumento());
        estudiante.setPrograma(estudiantesEntity.getPrograma());
        estudiante.setCorreo(estudiantesEntity.getCorreo());
        estudiantesRepository.save(estudiante);
        return true;
   
    }

}
