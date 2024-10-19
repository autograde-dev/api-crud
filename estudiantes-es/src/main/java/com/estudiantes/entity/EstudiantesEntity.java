package com.estudiantes.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "estudiantes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudiantesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_estudiante")
    private Long idEstudiante;

    @Column(name = "primer_nombre", nullable = false)
    private String primerNombre;
    
    @Column(name = "segundo_nombre")
    private String SegundoNombre;
    
    @Column(name = "primer_apellido", nullable = false)
    private String primerApellido;
    
    @Column(name = "segundo_apellido")
    private String segundoApellido;

    @Column(name= "documento", nullable = false, unique = true)
    private String documento;
    
    @Column(name= "programa", nullable = false) 
    private String programa;

    @Column(name= "correo", nullable = false)
    private String correo;

}
