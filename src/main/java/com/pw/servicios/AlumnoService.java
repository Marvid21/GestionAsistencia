package com.pw.servicios;

import com.pw.domain.Alumno;

import java.util.List;

public interface AlumnoService {

    public List<Alumno> listarAlumnos();

    public void guardar(Alumno alumno);

    public void eliminar(Alumno alumno);

    public Alumno encontrarAlumno(Alumno alumno);
}
