package com.pw.domain;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@Table(name = "alumno")
public class Alumno implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAlumnos;

    @NotNull
    private Integer alCedula;

    @NotNull
    @NotEmpty
    private String alApellido;

    @NotNull
    @NotEmpty
    private String alNombre;

    private String alAsistencia;

    private Integer alAdulaPuntos;
}
