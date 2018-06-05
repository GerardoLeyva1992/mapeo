package edu.uacm.test.domain;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import edu.uacm.Application;
import edu.uacm.domain.Comentario;
import edu.uacm.domain.Post;
import edu.uacm.domain.Usuario;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes=Application.class)
@WebAppConfiguration
public class UsuarioTest {
	@PersistenceContext
private EntityManager em;	

	@Test
	@Transactional
	@Rollback(false)
	public void insertarUser(){
		//usuario 1
	    Usuario user=new Usuario();
        Date now=new Date();
        user.setNombre("Gerardoo");
        user.setCiudad("Mexico");
        user.setCorreo("gerardoleyvat@hotmail.com");
        user.setPass("papapa");
        user.setFechaAlta(now);
        user.setImagenPerfil("sdfsdfsdfsdf");
       
        //usuario2
        Usuario user2=new Usuario();
        Date now2=new Date();
        user2.setNombre("Job");
        user2.setCiudad("Mexicot");
        user2.setCorreo("job@hotmail.com");
        user2.setPass("papapat");
        user2.setFechaAlta(now2);
        user2.setImagenPerfil("sdfsdfsdfsdft");
       
        
        //nuevo post de usuario
        Post p=new Post();
    
        p.setUsario(user);
        p.setTextoPost("quien quiere pelea");
        Date publicaFecha=new Date();
        p.setFechaDePublicacion(publicaFecha);
        //p.setHoraPublicacion(publicaFecha.);
        p.setTitulo("Pelea");
        p.setImagePost("no puso");
        
        //algunos comentarios
        Comentario c=new Comentario();
        
        Comentario c2=new Comentario();
        
        
        //usuario2 hace comentario sobre post de usuario
        c2.setComentario("yo quiero pelea");
        c2.setUsario(user2);
        c2.setPost(p);
        
        
        //usuario responde  comentario
        c.setComentario("la pelea es mala");
        c.setUsario(user);
        c.setPost(p);
        
        
        
        
        
        
        
       
        
    
        
       p.getComentarios().add(c2);
       p.getComentarios().add(c);
       
       
       
       user.getComentarios().add(c2);
       user2.getComentarios().add(c);
       
       
       user.getListaPosts().add(p);
       
       
        
        
       em.persist(user);//registr usuario
       em.persist(user2);//registra usuario2

       em.persist(p);//usuario genera un post
       em.persist(c2);//usuario hace un cometario en su post
       em.persist(c);//usuario2 hace un comentario en post de usuario
       
       
	for(Comentario s: p.getComentarios())//ver comentarios de post de usuario
       System.out.println("hola "+s.getComentario());
        
       
       
        
        
        
        
        em.flush();
        
       
	}	
	
	
	
	
}
