package test;

import org.junit.Test;

import com.java.utils.StringUtils;

public class TestUtils {
	@Test
	public void testRandom() {
		for (int i = 0; i < 20; i++) {
			System.out.println(StringUtils.getRandom(4));
		}
	}
}
