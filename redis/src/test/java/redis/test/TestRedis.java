package redis.test;

import java.util.Set;

import org.junit.Test;

import redis.JedisUtils;
import redis.clients.jedis.Jedis;

public class TestRedis{
	@Test
	public void test(){
		Jedis jedis = JedisUtils.getJedis();
		Set<String> set = jedis.keys("*");
		System.out.println(set.size());
	}
}
