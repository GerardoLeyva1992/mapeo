package edu.uacm.controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uacm.domain.Usuario;

@Controller
@RequestMapping("/user")
public class UsuarioController {
	
    @PersistenceContext
    private EntityManager em;

    @Transactional
	@Rollback(false)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("nombre") String nombre,
    		@RequestParam("correo") String correo,
    		@RequestParam("pass") String pass,
    		@RequestParam("ciudad") String ciudad,
    		@RequestParam("imagen") String imagen) {
        Usuario user=new Usuario();
        Date now=new Date();
        user.setNombre(nombre);
        user.setCiudad(ciudad);
        user.setCorreo(correo);
        user.setPass(pass);
        user.setFechaAlta(now);
        user.setImagenPerfil(imagen);
        
        em.persist(user);
        em.close();
        
        return new ModelAndView("redirect:/user/new");
    }

}
