package com.glen.BlogPostSpringBoot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import java.awt.EventQueue;
import com.glen.BlogPostSpringBoot.dao.DAO;
import com.glen.BlogPostSpringBoot.pojos.Course;
import com.glen.BlogPostSpringBoot.swing.MainFrame;

@SpringBootApplication
public class SpringBootJdbcApplication {
	
	
	private static DAO<Course> dao;
	
	@SuppressWarnings("static-access")
	@Autowired
	public SpringBootJdbcApplication(DAO<Course> dao) {
		super();
		this.dao = dao;
	}




	public static void main(String[] args) {
		ConfigurableApplicationContext ctx= new SpringApplicationBuilder(SpringBootJdbcApplication.class)
				.headless(false)
				.web(WebApplicationType.NONE)
				.run(args);
		
		EventQueue.invokeLater(() -> {
	        MainFrame ex = ctx.getBean(MainFrame.class);
	        
	    });
		
//		
	}

}
