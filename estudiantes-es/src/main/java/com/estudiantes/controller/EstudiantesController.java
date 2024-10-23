package com.estudiantes.controller;

import com.estudiantes.dto.MessageDto;

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

import com.estudiantes.entity.EstudiantesEntity;
import com.estudiantes.service.EstudiantesService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;

@RestController
@CrossOrigin(origins = "http://localhost:8082")
@RequestMapping(path = "estudiantes")
public class EstudiantesController {

    @Autowired
    private EstudiantesService estudiantesService;

    @Operation(summary = "Listar todos los estudiantes")
    @GetMapping("/find-all")
    public ResponseEntity<?> getEstudiantes() {
        var estudiantes = estudiantesService.getEstudiantes();
        if (estudiantes == null || estudiantes.isEmpty()) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @Operation(summary = "Listar estudiantes por pagina")
    @GetMapping("/findall-page/{page}/{size}")
    public ResponseEntity<?> findPage(@PathVariable  int page, @PathVariable int size) {
        var estudiantes= estudiantesService.findPage(page,size);
        if(estudiantes == null){
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(estudiantes, HttpStatus.OK);
    }

    @Operation(summary = "Buscar estudiante por id")
    @GetMapping("find-by-id/{id}")
    public ResponseEntity<?> getEstudiantesbyId(@PathVariable("id") Long id) {
        var estudiante = estudiantesService.getEstudiantesbyId(id);
        if (estudiante == null) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

    @Operation(summary = "Buscar estudiante por documento")
    @GetMapping("find-by-documento/{documento}")
    public ResponseEntity<?> getEstudianteByDocumento(@PathVariable("documento") String documento) {
        var estudiante = estudiantesService.getEstudianteByDocumento(documento);
        if (estudiante == null) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(estudiante, HttpStatus.OK);
    }

     @Operation(summary = "Crear estudiante")
    @PostMapping("insertar/")
    public ResponseEntity<MessageDto> insertarEstudiante(@RequestBody EstudiantesEntity estudiantesEntity) {
        boolean response = estudiantesService.save(estudiantesEntity);
        if (response) {
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Guardado con exito"));
        }
        return ResponseEntity.internalServerError().body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, "Error Guardando"));
    }

    @Operation(summary = "Eliminar estudiante por id")
    @DeleteMapping("borrar/{id}")
    public ResponseEntity<MessageDto> borrarEstudiante(@PathVariable long id) {
        boolean response = estudiantesService.deleteById(id);
        if (response) {
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Eliminado con exito"));
        }
        return ResponseEntity.internalServerError().body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, "Error Eliminando"));
    }

    @Operation(summary = "Actualizar estudiante por id")
    @PutMapping("actualizar/{id}")
    public ResponseEntity<MessageDto> updateEstudiante(@RequestBody EstudiantesEntity estudiantesEntity) {        
        boolean response = estudiantesService.update(estudiantesEntity);
        if (response) {
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Actualizado con exito"));
        }
        return ResponseEntity.internalServerError().body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, "Error Actualizando"));
    }
}
