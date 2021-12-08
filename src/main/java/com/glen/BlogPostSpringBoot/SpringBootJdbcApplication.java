package com.glen.BlogPostSpringBoot;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.glen.BlogPostSpringBoot.dao.DAO;
import com.glen.BlogPostSpringBoot.pojos.Course;

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
		SpringApplication.run(SpringBootJdbcApplication.class, args);
		
		System.out.println("\n Create Courses..................................\n");
		Course php= new Course("Php", "Php for beginers", "http://udemy/php");
//		dao.create(php);
		
		System.out.println("\n Get Course By id..................................\n");
		Optional<Course> course =dao.get(10);
		System.out.println(course.get());
		
		System.out.println("\n Update Course By id..................................\n");
		Course phpUpdate= new Course("Php For beginers", "Php for beginers", "http://udemy/phpForBeginers");
		dao.update(phpUpdate, 18);
		
		System.out.println("\n Delete Course By id..................................\n");
		dao.delete(17);
		
		System.out.println("\n All Courses..................................\n");
		List<Course> courses =dao.list();
		courses.forEach(System.out::println);
	}

}
