package com.docentes.controller;

import com.docentes.dto.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.docentes.entity.DocentesEntity;
import com.docentes.service.DocentesService;

@RestController
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping(path = "docentes")
public class DocentesController {

    @Autowired
    private DocentesService docentesService;

    @GetMapping("/find-all")
    public ResponseEntity<?> getEstudiantes() {
        var docentes = docentesService.getDocentes();
        if (docentes == null || docentes.isEmpty()) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(docentes, HttpStatus.OK);
    }

    @GetMapping("/findall-page/{page}/{size}")
    public ResponseEntity<?> findPage(@PathVariable  int page, @PathVariable int size) {
        var docentes= docentesService.findPage(page,size);
        if(docentes == null){
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(docentes, HttpStatus.OK);
    }

    @GetMapping("find-by-id/{id}")
    public ResponseEntity<?> getEstudiantesbyId(@PathVariable("id") Long id) {
        var docente = docentesService.getDocentesbyId(id);
        if (docente == null || docente.getIdDocente()==null) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(docente, HttpStatus.OK);
    }

    @GetMapping("find-by-documento/{documento}")
    public ResponseEntity<?> getEstudianteByDocumento(@PathVariable("documento") String documento) {
        var docente = docentesService.getDocentebyDocumento(documento);
        if (docente == null || docente.getIdDocente()==null) {
            return new ResponseEntity<>("{}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(docente, HttpStatus.OK);
    }

    @PostMapping("insertar/")
    public ResponseEntity<MessageDto> insertarDocente(@RequestBody DocentesEntity docentesEntity) {
        boolean response = docentesService.save(docentesEntity);
        if (response) {
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Guardado con exito"));
        }
        return ResponseEntity.internalServerError().body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, "Error Guardando"));
    }

    @DeleteMapping("borrar/{id}")
    public ResponseEntity<MessageDto> borrarDocente(@PathVariable long id) {
        boolean response = docentesService.deleteById(id);
        if (response) {
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Eliminado con exito"));
        }
        return ResponseEntity.internalServerError().body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, "Error Eliminando"));
    }

    @PutMapping("actualizar/{id}")
    public ResponseEntity<MessageDto> updateDocente(@RequestBody DocentesEntity docentesEntity) {
        boolean response = docentesService.update(docentesEntity);
        if (response) {
            return ResponseEntity.ok(new MessageDto(HttpStatus.OK, "Actualizado con exito"));
        }
        return ResponseEntity.internalServerError().body(new MessageDto(HttpStatus.INTERNAL_SERVER_ERROR, "Error Actualizando"));
    }
}
