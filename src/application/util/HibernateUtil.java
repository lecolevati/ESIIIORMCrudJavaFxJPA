package application.util;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import application.model.Aluno;
import application.model.Curso;

public class HibernateUtil {
	 private static SessionFactory sessionFactory;
	    public static SessionFactory getSessionFactory() {
	        if (sessionFactory == null) {
	            try {
	                Configuration configuration = new Configuration();

	                // Hibernate settings equivalent to hibernate.cfg.xml's properties
	                Properties settings = new Properties();
	                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
	                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/ormmaven?createDatabaseIfNotExist=true");
	                settings.put(Environment.USER, "root");
	                settings.put(Environment.PASS, "P4ssw0rd");
	                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
	                settings.put(Environment.SHOW_SQL, "true");
	                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
	                settings.put(Environment.HBM2DDL_AUTO, "update");

	                configuration.setProperties(settings);
	                configuration.addAnnotatedClass(Aluno.class);
	                configuration.addAnnotatedClass(Curso.class);
	                
	                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	                    .applySettings(configuration.getProperties()).build();

	                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	        return sessionFactory;
	    }
}
