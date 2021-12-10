package com.glen.BlogPostSpringBoot.dao;

import java.util.List;
import java.util.Optional;

import javax.swing.tree.RowMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.glen.BlogPostSpringBoot.pojos.Course;

@Component
public class CourseDao implements DAO<Course>{
	
	private static final Logger logger = LoggerFactory.getLogger(CourseDao.class);
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public CourseDao(JdbcTemplate jdbcTemplate) {
		
		this.jdbcTemplate = jdbcTemplate;
	}
	

	@Override
	public List<Course> list() {
		String sql="SELECT id,title,description,link FROM course";
		return jdbcTemplate.query(sql, (rec,rownum)->{//RowMapper implementation
			Course course=new Course();
			course.setId(rec.getInt("id"));
			course.setTitle(rec.getString("title"));
			course.setDescription(rec.getString("description"));
			course.setLink(rec.getString("link"));
			return course;
		});
	}

	@Override
	public boolean create(Course course) {
		String sql = "INSERT INTO course(title,description,link) VALUES(?,?,?)";
		int rowsAffected=jdbcTemplate.update(sql,
				course.getTitle(),
				course.getDescription(),
				course.getLink()
		);
		if(rowsAffected==1)
			return true;
		return false;
	}

	@Override
	public Optional<Course> get(int id) {
		 String sql="SELECT id,title,description,link FROM course WHERE id=?";
		 Course courseToReturn=null;
		 try {
			 courseToReturn=jdbcTemplate.queryForObject(sql,(rec,rownum)->{//RowMapper implementation
					Course course=new Course();
					course.setId(rec.getInt("id"));
					course.setTitle(rec.getString("title"));
					course.setDescription(rec.getString("description"));
					course.setLink(rec.getString("link"));
					return course;
				},new Object[] { id });
		 }catch(Exception e)
		 {
			 logger.info("course not found"+id);
		 }
		 return Optional.ofNullable(courseToReturn);
	}

	@Override
	public boolean update(Course course, int id) {
		String sql="UPDATE course SET title=?,description=?,link=? WHERE id=?";
		int rowsAffected = jdbcTemplate.update(sql,
				course.getTitle(),
				course.getDescription(),
				course.getLink(),
				id
		);
		if(rowsAffected==1)
			return true;
		return false;
	}

	@Override
	public boolean delete(int id) {
		String sql="DELETE FROM course WHERE id=?";
		int rowsAffected=jdbcTemplate.update(sql,id);
		if(rowsAffected==1)
			return true;
		return false;
	}

}
