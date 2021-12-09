package com.glen.BlogPostSpringBoot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.glen.BlogPostSpringBoot.dao.CourseDao;
import com.glen.BlogPostSpringBoot.pojos.Course;

@Service
public class CourseService {
	
	CourseDao courseDao;
	
	@Autowired
	public CourseService(CourseDao courseDao) {
		this.courseDao = courseDao;
	}

	public List<Course> getAllCourses() {
		
		return courseDao.list();
	}
	
	public String[][] getAllCoursesAsStringArray(){
		List<Course> courses =  courseDao.list();
		String[][] arrayOfCourses = new String[courses.size()][4];
		for(int i=0;i<courses.size();i++) {
			arrayOfCourses[i][0]=String.valueOf(courses.get(i).getId());
			arrayOfCourses[i][1] = courses.get(i).getTitle();
			arrayOfCourses[i][2] = courses.get(i).getDescription();
			arrayOfCourses[i][3] = courses.get(i).getLink();
		}
		return arrayOfCourses;
		
	}
 
}
