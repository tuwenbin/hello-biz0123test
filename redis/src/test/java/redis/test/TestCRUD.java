package redis.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import redis.RedisCRUD;
import redis.Student;

public class TestCRUD {
	@Test
	public void testaddStudents() throws Exception{
		RedisCRUD crud =  new RedisCRUD();
		Student student = new Student();
		student.setId("001");
		student.setName("zs");
		SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
		student.setBirthday(dateFormat.parse("2011-09-12"));
		student.setAvgscore(98);
		student.setDescription("考得很好,111");
		crud.add(student);
	}

	@Test
	public void testgetStudents() throws Exception{
		RedisCRUD crud =  new RedisCRUD();
		List<Student> list = crud.getStudents();
		System.out.println(list.size());
	}

	@Test
	public void testdelStudents(){
		RedisCRUD crud =  new RedisCRUD();
		crud.delete("001");
	}

	@Test
	public void testgetStudentById(){
		RedisCRUD crud =  new RedisCRUD();
		Student student = crud.getStudentById("001");
		System.out.println(student);
	}
}
