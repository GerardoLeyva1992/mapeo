package edu.uacm.util;
import org.apache.log4j.Logger;  
import org.hibernate.SessionFactory;  
import org.hibernate.cfg.Configuration;  
  

  
public final class HibernateUtils {  
  
    private static final Logger LOG = Logger.getLogger(HibernateUtils.class);  
  
 

    public static SessionFactory newSessionFactory(final String pathToHibernateCfgXml) {  
        LOG.info("Loading Hibernate Session Factory with configurations from file "  
                + pathToHibernateCfgXml + "...");  
        Configuration hibernateConfiguration = new Configuration();  
        hibernateConfiguration.configure(pathToHibernateCfgXml);  
        return hibernateConfiguration.buildSessionFactory();  
    }  
  
}  