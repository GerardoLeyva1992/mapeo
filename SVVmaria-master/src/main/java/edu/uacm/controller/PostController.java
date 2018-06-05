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

import edu.uacm.domain.Post;
import edu.uacm.domain.Usuario;

@Controller
@RequestMapping("/posts")
public class PostController {

	

    @PersistenceContext
    private EntityManager em;

    @Transactional
	@Rollback(false)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("idUser") Long idUser,
    		@RequestParam("titulo") String titulo,
    		@RequestParam("textoPost") String textoPost,
    		@RequestParam("imagen") String imagen) {
        Post post=new Post();
        Date now=new Date();
        
        Usuario a=new Usuario();
        
        a=em.find(Usuario.class, idUser);
        
        post.setUsario(a);
        post.setImagePost(imagen);
        post.setTextoPost(textoPost);
        post.setTitulo(titulo);
        post.setFechaDePublicacion(now);
        
        a.getListaPosts().add(post);
        
        em.persist(a);
        em.persist(post);
        em.close();
        
        return new ModelAndView("redirect:/posts/new");
    }
}
