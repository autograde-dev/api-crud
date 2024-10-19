package com.docentes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.docentes.entity.DocentesEntity;
import com.docentes.repository.DocentesRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DocentesService {
    @Autowired
    DocentesRepository docentesRepository;

    public List<DocentesEntity> getDocentes(){
        return docentesRepository.findAll();
    }

    public Page<DocentesEntity> findPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return docentesRepository.findAll(pageable);
    }

    public DocentesEntity getDocentesbyId(long id){
        return docentesRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con ID: " + id));
    }
    
    public DocentesEntity getDocentebyDocumento(String documento){
        return docentesRepository.findByDocumento(documento).orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con ID: " + documento));
    }

    public boolean save(DocentesEntity docentesEntity){
        var estudiante = docentesRepository.findByDocumento(docentesEntity.getDocumento());
        if(estudiante.isPresent()){
            return false;
        }
        docentesRepository.save(docentesEntity);
        return true;
    }

    public boolean deleteById(Long id){
        var docentes = docentesRepository.findById(id);
        if(docentes.isEmpty()){
            return false;
        }
        docentesRepository.deleteById(id);
        return true;
    }

    public boolean update(DocentesEntity docentesEntity){
        
        DocentesEntity docente = docentesRepository.findById(docentesEntity.getIdDocente())
        .orElseThrow(() -> new EntityNotFoundException("Docente no encontrado con ID: " + docentesEntity.getIdDocente()));
        docente.setPrimerNombre(docentesEntity.getPrimerNombre());
        docente.setSegundoNombre(docentesEntity.getSegundoNombre());
        docente.setPrimerApellido(docentesEntity.getPrimerApellido());
        docente.setSegundoApellido(docentesEntity.getSegundoApellido());
        docente.setDocumento(docentesEntity.getDocumento());
        docente.setCorreo(docentesEntity.getCorreo());
        docentesRepository.save(docente);
        return true;
   
    }

}
