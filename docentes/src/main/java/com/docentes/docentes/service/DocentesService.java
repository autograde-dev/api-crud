package com.docentes.docentes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.docentes.docentes.entity.DocentesEntity;
import com.docentes.docentes.repository.DocentesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DocentesService {
    @Autowired
    DocentesRepository docentesRepository;

    public List<DocentesEntity> getDocentes(){
        return (List<DocentesEntity>) docentesRepository.findAll();
    }

    public DocentesEntity getDocentesbyId(long id){
        return docentesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con ID: " + id));
    }

    public boolean save(DocentesEntity docentesEntity){
        
        docentesRepository.save(docentesEntity);
        return true;
    }

    public boolean deleteById(Long id){
        
        docentesRepository.deleteById(id);
        return true;
    }

    public boolean updateById(Long id, DocentesEntity docentesEntity){
        
        DocentesEntity docente = docentesRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con ID: " + id));
        docente.setNombres(docentesEntity.getNombres());
        docente.setApellidos(docentesEntity.getApellidos());
        docente.setDocumento(docentesEntity.getDocumento());
        docente.setCorreo(docentesEntity.getCorreo());
        docentesRepository.save(docente);
        return true;
   
    }

}
