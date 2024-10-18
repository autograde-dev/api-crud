package com.docentes.docentes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "docentes")
@Entity

public class DocentesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name= "id_docente", nullable = false)
    private Integer id_docente;

    @Column(name= "nombres", nullable = false)
    private String nombres;

    @Column(name= "apellidos", nullable = false)
    private String apellidos;

    @Column(name= "documento", nullable = false) 
    private String documento;

    @Column(name= "correo", nullable = false)
    private String correo;

    public DocentesEntity(Integer id_docente, String nombres, String apellidos, String documento, String correo) {
        this.id_docente = id_docente;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.documento = documento;
        this.correo = correo;
    }

    public DocentesEntity() {
    }

    public Integer getId_docente() {
        return id_docente;
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

    public void setId_docente(Integer id_docente) {
        this.id_docente = id_docente;
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
    
    
}
