package com.java.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

/**
 * ���л��������л�
 * @author xjl
 * 2019-01-03 08:50:45
 */
public class SerializeUtils {
	
	private static Logger log = Logger.getLogger(SerializeUtils.class);
	
	/**
	 * ���������л�
	 * 2019-01-03 08:58:09
	 * @param object
	 * @return
	 */
	public static byte[] serialize(Object object) {
		ByteArrayOutputStream byos = null;
		ObjectOutputStream oos = null;
		
		try {
			byos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(byos);
			oos.writeObject(object);
			byte bt[] = byos.toByteArray();
			return bt;
		} catch (IOException e) {
			log.error(e.getMessage());
		}finally {
			try {
				if(byos!=null) {
					byos.close();
				}
				if(oos!=null) {
					oos.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());
			}
		}
		return null;
		
	}
	
	/**
	 * �����л���������л�<br/>
	 * ����bt����ͨ��ObjectOutputStream���л������ĳ����쳣��
	 * <br/>
	 * java.io.StreamCorruptedException: invalid stream header.
	 * <br/>
	 * 2019-01-03 09:02:14
	 * @param bt
	 * @return
	 */
	public static Object unserialize(byte bt[]) {
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		
		
		try {
			bais = new ByteArrayInputStream(bt);
			ois = new ObjectInputStream(bais);
			return ois.readObject();
		} catch (IOException e) {
			log.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error(e.getMessage());
		}finally {
			try {
				if(bais!=null) {
					bais.close();
				}
				if(ois!=null) {
					ois.close();
				}
			} catch (IOException e) {
				log.error(e.getMessage());;
			}
		}
		return null;
		
	}
}
