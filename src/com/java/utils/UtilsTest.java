package com.java.utils;

import java.security.KeyStore.Entry;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.java.entites.UserInf;

public class UtilsTest {
	@Test
	public void testSerializeUtils() {
		Map<String,UserInf> map = new HashMap<String,UserInf>();
		UserInf user1  =  new UserInf();
		user1.setUserName("xiao");
		user1.setUserPaw("xiao");
		UserInf user2  =  new UserInf();
		user1.setUserName("jun");
		user1.setUserPaw("jun");
		map.put("user1", user1);
		map.put("user2", user2);
		
		byte bt[] = SerializeUtils.serialize(map);
		System.out.println(bt.toString());
		Map<String,UserInf> mapN = (Map<String, UserInf>) SerializeUtils.unserialize(bt);
	}
	
	@Test
	public void testRedisUtils() {
		String key = "xiao";
		String value = "jun";
		RedisUtils.setString(key, value);
		System.out.println(RedisUtils.getString(key)+"-----"+RedisUtils.getRedisSeqNum("user:num"));
		
		key = "xiao:1";
		value = "jun:1";
		RedisUtils.setString(key, value,10);
		System.out.println(RedisUtils.getString(key)+"-----"+RedisUtils.getRedisSeqNum("user:num"));
		
		//RedisUtils.updateKey(key, 12);
		try {
			System.out.println("===========ÇëµÈ´ý11Ãë=============");
			Thread.sleep(11000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(RedisUtils.getString(key)+"-----"+RedisUtils.deleteKey(key)
		+"-----"+RedisUtils.getRedisSeqNum("user:num"));
		
		String keyMap = "li";
		Map<String,String> valueMap = new HashMap<String,String>();
		valueMap.put("li-1", "xiao-1");
		valueMap.put("li-2", "xiao-2");
		valueMap.put("li-3", "xiao-3");
		RedisUtils.setMap(keyMap, valueMap);
		
		Map<String,String> valueMap1 = RedisUtils.getMap(keyMap);
		for(java.util.Map.Entry<String,String> entry: valueMap1.entrySet()) {
			System.out.println(entry.getKey()+"--"+entry.getValue());
		}
	}
}
