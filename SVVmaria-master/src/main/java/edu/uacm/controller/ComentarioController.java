package edu.uacm.controller;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Controller;
import org.springframework.test.annotation.Rollback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.uacm.domain.Comentario;
import edu.uacm.domain.Post;
import edu.uacm.domain.Usuario;


@Controller
@RequestMapping("/comentario")
public class ComentarioController {
	
    @PersistenceContext
    private EntityManager em;
    
    

    @Transactional
	@Rollback(false)
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@RequestParam("postId") Long postId,
    		@RequestParam("userId") Long userId,
    		@RequestParam("comentario") String comentario) {
    
    	
    	Comentario com=new Comentario();
        Usuario usuario=new Usuario();
        Post post=new Post();
        usuario= em.find(Usuario.class, userId);
        post=em.find(Post.class, postId);
        
        com.setPost(post);
        com.setUsario(usuario);
        com.setComentario(comentario);
        
        
        usuario.getComentarios().add(com);
        post.getComentarios().add(com);
        em.persist(usuario);
        em.persist(post);

        
        em.persist(com);
        em.close();
        
        return new ModelAndView("redirect:/comentario/new");
    }


}
