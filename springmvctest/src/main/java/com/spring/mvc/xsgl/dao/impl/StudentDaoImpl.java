package com.spring.mvc.xsgl.dao.impl;

import com.spring.mvc.xsgl.dao.StudentDao;
import com.spring.mvc.xsgl.entity.Student;
import com.spring.mvc.xsgl.entity.Page;
import com.spring.mvc.xsgl.utils.RedisUtils;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
@Repository("studentDao")
public class StudentDaoImpl implements StudentDao {
    public void saveStudent(Student student) {
        Jedis jedis = RedisUtils.getJedis();
        //保存用户的id在一个SET中
        jedis.zadd("studentSortSet", student.getAvgscore(), student.getId());
        //保存用户的信息在Hash中
        jedis.hset(student.getId(), "name", student.getName());
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        jedis.hset(student.getId(), "birthday", dateFormat.format(student.getBirthday()));
        jedis.hset(student.getId(), "avgscore", student.getAvgscore().toString());
        jedis.hset(student.getId(), "description", student.getDescription());
        RedisUtils.returnResource(jedis);
    }

    public void deleteStudent(String id) {
        Jedis jedis = RedisUtils.getJedis();
        jedis.zrem("studentSortSet", id);
        jedis.del(id);
        RedisUtils.returnResource(jedis);
    }

    public List<Student> findStudentList() {
        Jedis jedis = RedisUtils.getJedis();
        List<Student> listStu = new ArrayList<Student>();
        //获取到所有的学生id
        Set<String> ids = jedis.zrevrange("studentSortSet", 0, -1);
        //遍历出所有的学生id
        Iterator<String> iterator = ids.iterator();
        while (iterator.hasNext()) {
            Student student =  new Student();
            String id = iterator.next();
            //获取到学生的信息,并保存到学生中
            List<String> strings = jedis.hmget(id, "name","birthday","avgscore","description");
            student.setId(id);
            student.setName(strings.get(0));
            Date birthday;
            try {
                birthday = new SimpleDateFormat("yyyy-MM-dd").parse(strings.get(1));
                student.setBirthday(birthday);
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            student.setAvgscore(Integer.parseInt(strings.get(2)));
            student.setDescription(strings.get(3));
            listStu.add(student);
        }
        RedisUtils.returnResource(jedis);
        return listStu;
    }

    public Student getStudentById(String id) {
        Jedis jedis = RedisUtils.getJedis();
        Student student = new Student();
        List<String> strings = jedis.hmget(id, "name","birthday","avgscore","description");
        student.setId(id);
        student.setName(strings.get(0));
        Date birthday;
        try {
            birthday = new SimpleDateFormat("yyyy-MM-dd").parse(strings.get(1));
            student.setBirthday(birthday);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        student.setAvgscore(Integer.parseInt(strings.get(2)));
        student.setDescription(strings.get(3));
        RedisUtils.returnResource(jedis);
        return student;
    }

    public Page findPageRecords(String num) {
        Jedis jedis = RedisUtils.getJedis();
        int pageNum = 1;
        if(num != null && !num.trim().equals("")){
            pageNum = Integer.parseInt(num);
        }
        //得到学生总的数量
        Set<String> ids = jedis.zrevrange("studentSortSet", 0, -1);
        int totalRecords = ids.size();
        Page page = new Page(pageNum, totalRecords);
        //查询分页记录
        List<Student> students = findStudentList();
        List<Student> records = new ArrayList<Student>();
        for(int i=page.getStartIndex();i<page.getStartIndex()+page.getPageSize();i++){
            if(i<students.size()){
                records.add(students.get(i));
            }
        }

        page.setRecords(records);
        RedisUtils.returnResource(jedis);
        return page;
    }
}
