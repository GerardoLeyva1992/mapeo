package edu.uacm.controller;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uacm.domain.Usuario;

@Controller
@RequestMapping("/posts")
public class PostController {

	

    @PersistenceContext
    private EntityManager em;
/*
    @RequestMapping(value="", method=RequestMethod.GET)
    public String listPosts(Model model) {
        model.addAttribute("posts", repository.findAll());
        return "posts/list";
    }

    @RequestMapping(value = "/{idaeropuerto}/delete", method = RequestMethod.GET)
    public ModelAndView delete(@PathVariable Long idaeropuerto) {
        repository.delete(idaeropuerto);
        return new ModelAndView("redirect:/posts");
    }

    @RequestMapping(value="/new", method = RequestMethod.GET)
    public String newProject() {
        return "posts/new";
    }
    */
    @Transactional
	@Rollback(false)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("nombre") String nombre,
    		@RequestParam("correo") String correo,
    		@RequestParam("pass") String pass,
    		@RequestParam("ciudad") String ciudad,
    		@RequestParam("imagen") String imagen) {
        Usuario post=new Usuario();
        Date now=new Date();
        post.setNombre(nombre);
        post.setCiudad(ciudad);
        post.setCorreo(correo);
        post.setPass(pass);
        post.setFechaAlta(now);
        post.setImagenPerfil(imagen);
        
        //repository.save(post);
        em.persist(post);
        em.close();
        
        return new ModelAndView("redirect:/posts/new");
    }
/*
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView update(@RequestParam("post_idaeropuerto") Long idaeropuerto,
                               @RequestParam("nombreaeropuerto") String nombreaeropuerto
                               ) {
    
    	System.out.println("aqui ando en editar: "+idaeropuerto);
    	
    	
    	Imagen post = new Imagen();
         	post.setIdaeropuerto(idaeropuerto);
        post.setNombreaeropuerto(nombreaeropuerto);
        post.setVersion(Integer.parseInt(idaeropuerto.toString()));
        repository.save(post);
        return new ModelAndView("redirect:/posts");
    }

    @RequestMapping(value = "/{idaeropuerto}/edit", method = RequestMethod.GET)
    public String edit(@PathVariable Long idaeropuerto,
                       Model model) {
    	System.out.println("aqui ando: "+idaeropuerto);

        Imagen post = repository.findOne(idaeropuerto);
        model.addAttribute("post", post);
        return "posts/edit";
    }

*/
}
