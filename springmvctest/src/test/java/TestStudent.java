import com.spring.mvc.xsgl.entity.Student;
import com.spring.mvc.xsgl.service.StudentService;
import com.spring.mvc.xsgl.utils.RedisUtils;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Set;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public class TestStudent {
    ApplicationContext context = new ClassPathXmlApplicationContext("/springmvctest-servlet.xml");
    StudentService studentService = (StudentService)context.getBean("studentService");
    @Test
    public void testSave(){
        Student  student = new Student();
        student.setId("001");
        student.setName("zs");
        SimpleDateFormat  dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        try {
            student.setBirthday(dateFormat.parse("2011-09-12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        student.setAvgscore(98);
        student.setDescription("考得很好,111");
        this.studentService.saveStudent(student);

    }

    @Test
    public void test(){
        Jedis jedis = RedisUtils .getJedis();
        Set<String> set = jedis.keys("*");
        System.out.println(set.size());
    }

    @Test
    public void testdelStudents(){
        this.studentService.deleteStudent("001");
    }

    @Test
    public void testgetStudentById(){
        Student student = this.studentService.getStudentById("001");
        System.out.println(student);
    }
}
