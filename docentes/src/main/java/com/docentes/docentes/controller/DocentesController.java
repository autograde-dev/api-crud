package com.docentes.docentes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.docentes.docentes.entity.DocentesEntity;
import com.docentes.docentes.service.DocentesService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path="docentes")
public class DocentesController {
    @Autowired
    private DocentesService docentesService;

    @GetMapping("/")
    public List<DocentesEntity> getDocentes(){
        return docentesService.getDocentes();
    }

    @GetMapping("/{id}")
    public DocentesEntity getDocentesbyId(@PathVariable("id") Long id){
        return docentesService.getDocentesbyId(id);
    }

    @PostMapping("/")
    public void insertar(@RequestBody DocentesEntity docentesEntity){
        docentesService.save(docentesEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable long id) {
    docentesService.deleteById(id);
    return ResponseEntity.noContent().build(); // Devuelve un 204 No Content si se eliminó correctamente
    }

    @PutMapping("/{id}")
    public void updateDocente(@PathVariable long id, @RequestBody DocentesEntity docentesEntity) {
    docentesService.updateById(id, docentesEntity);   
    
}
}
