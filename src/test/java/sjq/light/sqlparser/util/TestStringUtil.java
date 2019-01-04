package sjq.light.sqlparser.util;

import org.junit.Assert;
import org.junit.Test;


public class TestStringUtil {
	
	@Test
	public void indexOf0() {
		int indexOfIgnoreCase = StringUtil.indexOfIgnoreCase("0123456", "012", 0);
		System.out.println(indexOfIgnoreCase);
	}
	
	@Test
	public void indexOf1() {
		int indexOfIgnoreCase = StringUtil.indexOfIgnoreCase("0123456", "01abcd", 0);
		System.out.println(indexOfIgnoreCase);
	}
	
	@Test
	public void indexOf2() {
		int indexOfIgnoreCase = StringUtil.indexOfIgnoreCase("0123456", "012", 1);
		System.out.println(indexOfIgnoreCase);
	}
	
	@Test
	public void indexOf3() {
		int indexOfIgnoreCase = StringUtil.indexOfIgnoreCase("0123456", "123", 1);
		System.out.println(indexOfIgnoreCase);
	}
	
	@Test
	public void indexOf4() {
		int indexOfIgnoreCase = StringUtil.indexOfIgnoreCase("0123456", "234", 2);
		System.out.println(indexOfIgnoreCase);
	}
	
	@Test
	public void indexOf5() {
		int i = StringUtil.indexOfIgnoreCase("abcdefg", "bcd", 1);
		System.out.println(i);
		Assert.assertEquals(i, 1);
	}
	
	@Test
	public void indexOf6() {
		int i = StringUtil.indexOfIgnoreCase("abcdefg", "BCD", 1);
		System.out.println(i);
		Assert.assertEquals(i, 1);
	}
	
	@Test
	public void indexOf7() {
		int i = StringUtil.indexOfIgnoreCase("abcdefg", "De", 1);
		System.out.println(i);
		Assert.assertEquals(i, 3);
	}
	
	@Test
	public void indexOf8() {
		int i = StringUtil.indexOfIgnoreCase("abCdEfG", "De", 1);
		System.out.println(i);
		Assert.assertEquals(i, 3);
	}
}
