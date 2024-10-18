package com.estudiantes.estudiantes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "estudiantes")
@Entity

public class EstudiantesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_estudiante", nullable = false)
    private Integer id_estudiante;

    @Column(name= "nombres", nullable = false)
    private String nombres;

    @Column(name= "apellidos", nullable = false)
    private String apellidos;

    @Column(name= "documento", nullable = false) 
    private String documento;
    
    @Column(name= "programa", nullable = false) 
    private String programa;

    @Column(name= "correo", nullable = false)
    private String correo;

    public EstudiantesEntity() {
    }

    public EstudiantesEntity(Integer id_estudiante, String nombres, String apellidos, String documento, String programa, String correo) {
        this.id_estudiante = id_estudiante;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.programa = programa;
        this.correo = correo;
    }
    
    public Integer getId_estudiante() {
        return id_estudiante;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPrograma() {
        return programa;
    }
    
    public void setId_estudiante(Integer id_estudiante) {
        this.id_estudiante = id_estudiante;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    
}
