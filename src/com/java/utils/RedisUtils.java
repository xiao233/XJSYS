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
 * redis���湤����
 * @author xjl
 * 2019-01-02 13:45:28
 */
public class RedisUtils {
	
	private static Logger log = Logger.getLogger(RedisUtils.class);
	
	private static JedisSentinelPool jedisSentinelPool ;
	
	/**
	 * ��ʼ��Redis������Ϣ
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
			log.error("����Redis������Ϣʧ�ܣ�"+e.getMessage());
		}
	}
	
	/**
	 * ��ȡ��Ӧ�ֶε���һ�����У�������
	 * 2019-01-03 09:53:53
	 * @param key key��Ӧ�Ե�ֵ����������
	 * @return
	 */
	public static long getRedisSeqNum(String key) {
		Jedis jedis = null;
		
		try {
			jedis = new Jedis();
			long result = jedis.incr(key);
			return result;
		}catch(Exception e) {
			log.error("��ȡ\""+key+"\"����ʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.getRedisSeqNum(String key): Jedis ���ӹرճɹ���");
			}
		}
		return 0;
	}
	
	/**
	 * �ӻ�����ȡֵ������Map<String,String>
	 * 2019-01-03 09:21:28
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> getMap(String key) {
		
		
		Jedis jedis = null;
		//��Ⱥ��ʽ��δ���óɹ�
		// jedis = jedisSentinelPool.getResource();
		
		try {
			jedis = new Jedis();
			byte bytes[] = jedis.get(key.getBytes());
			if(bytes!=null) {
				return (Map<String, String>) SerializeUtils.unserialize(bytes);
			}
			
		}catch(Exception e) {
			log.error("��ȡ\""+key+"\"ֵʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.getMap(String key): Jedis ���ӹرճɹ���");
			}
		}
		
		return null;
	}
	
	/**
	 * �ӻ�����ȡֵ������String
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
			log.error("��ȡ\""+key+"\"ֵʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.getString(String key): Jedis ���ӹرճɹ���");
			}
		}
		return null;
	}
	
	/**
	 * �򻺴��д�ֵ
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
			log.error("���\""+key+"\"ֵʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.setMap(String key,Map<String,String> value): Jedis ���ӹرճɹ���");
			}
		}
		return false;
	}
	
	/**
	 * �򻺴��д�ֵ
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
			log.error("���\""+key+"\"ֵʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.setString(String key,String value):Jedis ���ӹرճɹ���");
			}
		}
		return false;
	}
	
	/**
	 * �򻺴��д�ֵ
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
			log.error("���\""+key+"\"ֵʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.setString(String key,String value,int time): Jedis ���ӹرճɹ���");
			}
		}
		return false;
	}
	
	/**
	 * ɾ������Ӧ�Ļ�����Ϣ
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
			log.error("ɾ��\""+key+"\"ʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.deleteKey(String key): Jedis ���ӹرճɹ���");
			}
		}
		return false;
	}
	
	/**
	 * ���´˼���Ӧ�Ļ���ʱ��
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
			log.error("����\""+key+"\"���ʱ��ʧ�ܣ�"+e.getMessage());
		}finally {
			if(jedis!=null) {
				jedis.close();
				log.info("RedisUtils.updateKey(String key,int time)��Jedis ���ӹرճɹ���");
			}
		}
		return false;
	}
	
}
