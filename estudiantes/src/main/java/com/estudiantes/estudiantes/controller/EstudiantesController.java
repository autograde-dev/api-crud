package com.estudiantes.estudiantes.controller;

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

import com.estudiantes.estudiantes.entity.EstudiantesEntity;
import com.estudiantes.estudiantes.service.EstudiantesService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path="estudiantes")
public class EstudiantesController {
    @Autowired
    private EstudiantesService estudiantesService;

    @GetMapping("/")
    public List<EstudiantesEntity> getEstudiantes(){
        return estudiantesService.getEstudiantes();
    }

    @GetMapping("/{id}")
    public EstudiantesEntity getEstudiantesbyId(@PathVariable("id") Long id){
        return estudiantesService.getEstudiantesbyId(id);
    }

    @PostMapping("/")
    public void insertar(@RequestBody EstudiantesEntity estudiantesEntity){
        estudiantesService.save(estudiantesEntity);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocente(@PathVariable long id) {
        estudiantesService.deleteById(id);
    return ResponseEntity.noContent().build(); // Devuelve un 204 No Content si se eliminó correctamente
    }

    @PutMapping("/{id}")
    public void updateEstudiante(@PathVariable long id, @RequestBody EstudiantesEntity estudiantesEntity) {
        estudiantesService.updateById(id, estudiantesEntity);  
    } 
}
