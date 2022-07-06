package com.pw.web;

import com.pw.domain.Alumno;
import com.pw.servicios.AlumnoService;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
public class ControllerInicio {

//    @Qualifier("alumnoDao")
//    @Autowired
//    CrudRepository crudRepository;

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user){
        var alumnos = alumnoService.listarAlumnos();

        log.info("Usuario que hizo Login: " + user);
        log.info("Ejecutando el controlador Spring MVC");

        //Lista para eleccion de asistencia
        List<String> asiste = new ArrayList<String>();
        asiste.add("Presente");
        asiste.add("Ausente");

        //Lista para adulapuntos
        List<Integer> adula = new ArrayList<Integer>();
        adula.add(0);
        adula.add(1);
        adula.add(2);
        adula.add(3);
        adula.add(4);

        model.addAttribute("localDateTime", LocalDateTime.now());
        model.addAttribute("asiste", asiste);
        model.addAttribute("adula", adula);
        model.addAttribute("alumnos", alumnos);

        return "index";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid Alumno alumno, Model model, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }
        alumnoService.guardar(alumno);
        model.addAttribute("alumno", alumno);
        return "redirect:/";
    }

    @GetMapping("/editar")
    public String editar(Alumno alumno, Model model){
        alumno = alumnoService.encontrarAlumno(alumno);
        model.addAttribute("alumno", alumno);

        return "redirect:/";
    }
}
