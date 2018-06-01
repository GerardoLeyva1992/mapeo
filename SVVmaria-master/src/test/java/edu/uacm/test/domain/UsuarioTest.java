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
	public void insertarAvion(){
	    Usuario post=new Usuario();
        Date now=new Date();
        post.setNombre("Gerardo");
        post.setCiudad("Mexico");
        post.setCorreo("gerardoleyvat@hotmail.com");
        post.setPass("papapa");
        post.setFechaAlta(now);
        post.setImagenPerfil("sdfsdfsdfsdf");
        em.persist(post);
        em.flush();
        
       
	}	
	
}
