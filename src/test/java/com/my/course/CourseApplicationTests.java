package com.my.course;


import com.my.course.course.dao.CourseDao;

import com.my.course.model.Student;
import com.my.course.model.StudentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CourseApplication.class)
public class CourseApplicationTests {
	@Autowired
	private CourseDao courseDao;
	@Test
	public void testCourseImpl(){
		List<StudentDTO> studentList=courseDao.selectStudentListByCourseId(141501);

		System.out.println(studentList.get(0).toString());
	}

}
