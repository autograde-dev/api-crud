package com.estudiantes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.estudiantes.entity.EstudiantesEntity;
import com.estudiantes.repository.EstudiantesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class EstudiantesService {
    @Autowired
    EstudiantesRepository estudiantesRepository;

    public List<EstudiantesEntity> getEstudiantes(){
        return estudiantesRepository.findAll();
    }

    public Page<EstudiantesEntity> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return estudiantesRepository.findAll(pageable);
    }

    public EstudiantesEntity getEstudiantesbyId(long id){
        return estudiantesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + id));
    }
    public EstudiantesEntity getEstudianteByDocumento(String documento){
        return estudiantesRepository.findByDocumento(documento).orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + documento));
    }

    public boolean save(EstudiantesEntity estudiantesEntity){
        var estudiante = estudiantesRepository.findByDocumento(estudiantesEntity.getDocumento());
        if(estudiante.isPresent()){
            return false;
        }
        estudiantesRepository.save(estudiantesEntity);
        return true;
    }

    public boolean deleteById(Long id){
        var estudiante = estudiantesRepository.findById(id);
        if(estudiante.isEmpty()){
            return false;
        }
        estudiantesRepository.deleteById(id);
        return true;
    }

    public boolean update(EstudiantesEntity estudiantesEntity){
        EstudiantesEntity estudiante = estudiantesRepository.findById(estudiantesEntity.getIdEstudiante())
        .orElseThrow(() -> new EntityNotFoundException("Estudiante no encontrado con ID: " + estudiantesEntity.getIdEstudiante()));
        estudiante.setPrimerNombre(estudiantesEntity.getPrimerNombre());
        estudiante.setSegundoNombre(estudiantesEntity.getSegundoNombre());
        estudiante.setPrimerApellido(estudiantesEntity.getPrimerApellido());
        estudiante.setSegundoApellido(estudiantesEntity.getSegundoApellido());
        estudiante.setDocumento(estudiantesEntity.getDocumento());
        estudiante.setPrograma(estudiantesEntity.getPrograma());
        estudiante.setCorreo(estudiantesEntity.getCorreo());
        estudiantesRepository.save(estudiante);
        return true;
   
    }

}
