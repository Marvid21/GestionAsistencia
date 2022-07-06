package com.pw.servicios;

import com.pw.dao.AlumnoDao;
import com.pw.domain.Alumno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AlumnoServiceImpl implements AlumnoService{

    @Autowired
    private AlumnoDao alumnoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Alumno> listarAlumnos() {
        return (List<Alumno>) alumnoDao.findAll();
    }

    @Override
    public void guardar(Alumno alumno) {
        alumnoDao.save(alumno);
    }

    @Override
    public void eliminar(Alumno alumno) {
        alumnoDao.delete(alumno);
    }

    @Override
    @Transactional(readOnly = true)
    public Alumno encontrarAlumno(Alumno alumno) {
        return alumnoDao.findById(alumno.getIdAlumnos()).orElse(null);
    }
}
