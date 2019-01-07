package com.java.utils;

import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import com.java.constants.CommonConstants;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

/**
 * redis缓存工具类
 * @author xjl
 * 2019-01-02 13:45:28
 */
public class RedisUtils {
	
	private static Logger log = Logger.getLogger(RedisUtils.class);
	
	private static JedisSentinelPool jedisSentinelPool ;
	
	/**
	 * 初始化Redis配置信息
	 * 2019-01-02 15:19:45
	 */
	public  static void init() {
		LoadProperties loadProperties = new LoadProperties();
		try {
			Properties properties = loadProperties.getConfigProperties(CommonConstants.REDIS_CONFIG);
			
			GenericObjectPoolConfig config = new GenericObjectPoolConfig();
			config.setMaxTotal(Integer.parseInt(properties.getProperty("redis.pool.maxActive")));
			config.setMaxIdle(Integer.parseInt(properties.getProperty("redis.pool.maxIdle")));
			config.setMinIdle(Integer.parseInt(properties.getProperty("redis.pool.minIdle")));
			config.setMaxWaitMillis(Integer.parseInt(properties.getProperty("redis.pool.maxWait")));
			config.setTestOnBorrow(Boolean.valueOf(properties.getProperty("redis.pool.testOnBorrow")));
			config.setTestOnReturn(Boolean.valueOf(properties.getProperty("redis.pool.testOnReturn")));
			
			String hostPort = properties.getProperty("redis.ip.port");
			int timeout = Integer.parseInt(properties.getProperty("redis.timeout"));
			String auth = properties.getProperty("redis.auth");
			String pwd = properties.getProperty("redis.pwd");
			//int database = Integer.parseInt(properties.getProperty("redis.database"));
			
			Set<String> sentinels = new HashSet<String>();
			String hostPorts[] = hostPort.split(",");
			for (int i = 0; i < hostPorts.length; i++) {
				sentinels.add(hostPorts[i]);
			}
			
			if(jedisSentinelPool == null) {
				if(pwd != null && !"".equals(pwd)) {
					jedisSentinelPool = new JedisSentinelPool(auth, sentinels,config,timeout,pwd);
				}else if(timeout!=0) {
					jedisSentinelPool = new JedisSentinelPool(auth, sentinels,config,timeout);
				}else {
					jedisSentinelPool = new JedisSentinelPool(auth, sentinels,config);
				}
			}
			
		} catch (IOException e) {
			log.error("加载Redis配置信息失败！"+e.getMessage());
		}
	}
	
	/**
	 * 获取对应字段的下一个序列（自增）
	 * 2019-01-03 09:53:53
	 * @param key key对应对的值必须是数字
	 * @return
	 */
	public static long getRedisSeqNum(String key) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			long result = jedis.incr(key);
			return result;
		}catch(Exception e) {
			log.error("获取\""+key+"\"序列失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.getRedisSeqNum(String key): Jedis 连接关闭成功！");
			}
		}
		return 0;
	}
	
	/**
	 * 从缓存中取值，返回Map<String,String>
	 * 2019-01-03 09:21:28
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getMap(String key) {
		
		
		Jedis jedis = null;
		//集群方式还未配置成功
		// jedis = jedisSentinelPool.getResource();
		
		try {
			jedis = new Jedis();
			byte bytes[] = jedis.get(key.getBytes());
			if(bytes!=null) {
				return (Map<String, String>) SerializeUtils.unserialize(bytes);
			}
			
		}catch(Exception e) {
			log.error("获取\""+key+"\"值失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.getMap(String key): Jedis 连接关闭成功！");
			}
		}
		
		return null;
	}
	
	/**
	 * 从缓存中取值，返回String
	 * 2019-01-03 09:29:03
	 * @param key
	 * @return
	 */
	public static String getString(String key) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			if(jedis!=null) {
				return jedis.get(key);
			}
			
			
		}catch(Exception e) {
			log.error("获取\""+key+"\"值失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.getString(String key): Jedis 连接关闭成功！");
			}
		}
		return null;
	}
	
	/**
	 * 向缓存中存值
	 * 2019-01-03 09:31:18
	 * @param key
	 * @param value
	 */
	public static boolean setMap(String key,Map<String,String> value) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			String result = jedis.set(key.getBytes(), SerializeUtils.serialize(value));
			if(result.equals("OK")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.error("存放\""+key+"\"值失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.setMap(String key,Map<String,String> value): Jedis 连接关闭成功！");
			}
		}
		return false;
	}
	
	/**
	 * 向缓存中存值
	 * 2019-01-03 09:31:18
	 * @param key
	 * @param value
	 */
	public static boolean setString(String key,String value) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			String result = jedis.set(key, value);
			if(result.equals("OK")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.error("存放\""+key+"\"值失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.setString(String key,String value):Jedis 连接关闭成功！");
			}
		}
		return false;
	}
	
	/**
	 * 向缓存中存值
	 * 2019-01-03 09:41:35
	 * @param key
	 * @param value
	 * @param time s
	 * @return
	 */
	public static boolean setString(String key,String value,int time) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			String result = jedis.setex(key, time,value);
			if(result.equals("OK")) {
				return true;
			}else {
				return false;
			}
		}catch(Exception e) {
			log.error("存放\""+key+"\"值失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.setString(String key,String value,int time): Jedis 连接关闭成功！");
			}
		}
		return false;
	}
	
	/**
	 * 删除键对应的缓存信息
	 * 2019-01-03 09:46:37
	 * @param key
	 * @return
	 */
	public static boolean deleteKey(String key) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			long result = jedis.del(key);
			return result==0?false:true;
		}catch(Exception e) {
			log.error("删除\""+key+"\"失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.deleteKey(String key): Jedis 连接关闭成功！");
			}
		}
		return false;
	}
	
	/**
	 * 更新此键对应的缓存时间
	 * 2019-01-03 09:49:43
	 * @param key
	 * @param time
	 * @return
	 */
	public static boolean updateKey(String key,int time) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			long result = jedis.expire(key, time);
			return result==1?true:false;
		}catch(Exception e) {
			log.error("更新\""+key+"\"存活时间失败！"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.updateKey(String key,int time)：Jedis 连接关闭成功！");
			}
		}
		return false;
	}
	
}
