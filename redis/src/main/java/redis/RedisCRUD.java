package redis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import redis.clients.jedis.Jedis;

public class RedisCRUD {
	Jedis jedis = JedisUtils.getJedis();

	/**
	 *查询到所有的学生
	 * @return
	 * @throws Exception
	 * @throws Exception
	 */
	public List<Student> getStudents(){
		List<Student> listStu = new ArrayList<Student>();
		//获取到所有的学生id
		Set<String> ids = jedis.sunion("studentSet");
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
		return listStu;
	}

	/**
	 * 根据id查询出学生
	 * @param id
	 * @return
	 */
	public Student getStudentById(String id){
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
		return student;
	}

	/**
	 * 保存学生
	 * @param student
	 */
	public void add(Student student){
		//保存用户的id在一个LIST中
		jedis.sadd("studentSet",student.getId());
		//保存用户的信息在Hash中
		jedis.hset(student.getId(), "name", student.getName());
		SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
		jedis.hset(student.getId(), "birthday", dateFormat.format(student.getBirthday()));
		jedis.hset(student.getId(), "avgscore", student.getAvgscore().toString());
		jedis.hset(student.getId(), "description", student.getDescription());
	}

	/**
	 * 删除学生
	 * @param id
	 */
	public void delete(String id){
		jedis.srem("studentSet", id);
		jedis.del("id");
	}

	/**
	 * 根据页码查询出相应的学生信息
	 */
	public Page findPageRecords(String num) {
		int pageNum = 1;
		if(num != null && !num.trim().equals("")){
			pageNum = Integer.parseInt(num);
		}
		//得到学生总的数量
		Set<String> ids = jedis.sunion("studentSet");
		int totalRecords = ids.size();
		Page page = new Page(pageNum, totalRecords);
		//查询分页记录
		List<Student> students = getStudents();
		List<Student> records = new ArrayList<Student>();
		for(int i=page.getStartIndex();i<page.getStartIndex()+page.getPageSize();i++){
			if(i<students.size()){
				records.add(students.get(i));
			}
		}

		page.setRecords(records);
		return page;
	}

}
