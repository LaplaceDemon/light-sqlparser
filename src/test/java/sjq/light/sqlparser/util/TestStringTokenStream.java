package sjq.light.sqlparser.util;

import org.junit.Assert;
import org.junit.Test;


public class TestStringTokenStream {
	
	@Test
	public void testReadOneChar0() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789");
		{
		char read = stringTokenStream.read();
		Assert.assertEquals(read, '0');
		}
		{
		char read = stringTokenStream.read();
		Assert.assertEquals(read, '1');
		}
	}
	
	@Test
	public void testReadOneChar1() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789",2);
		{
		char read = stringTokenStream.read();
		Assert.assertEquals(read, '2');
		}
		{
		char read = stringTokenStream.read();
		Assert.assertEquals(read, '3');
		}
	}
	
	@Test
	public void testReadLen() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789");
		{
			String word = stringTokenStream.read(3);
			System.out.println(word);
			Assert.assertEquals(word, "012");
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, '3');
		}
		{
			String word = stringTokenStream.read(4);
			System.out.println(word);
			Assert.assertEquals(word, "4567");
		}
	}
	
	@Test
	public void testReadEqual() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789");
		{
			boolean b = stringTokenStream.readEqual("01234");
			System.out.println(b);
			Assert.assertEquals(b, true);
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, '5');
		}
		{
			String word = stringTokenStream.read(3);
			System.out.println(word);
			Assert.assertEquals(word, "678");
		}
	}
	
	@Test
	public void testReadUntilChar() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789");
		{
			String word = stringTokenStream.readUntil('5');
			System.out.println(word);
			Assert.assertEquals(word, "01234");
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, '5');
		}
		{
			String word = stringTokenStream.read(3);
			System.out.println(word);
			Assert.assertEquals(word, "678");
		}
	}
	
	
	@Test
	public void testReadUntilString() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789");
		{
			String word = stringTokenStream.readUntil("567");
			System.out.println(word);
			Assert.assertEquals(word, "01234");
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, '5');
		}
		{
			String word = stringTokenStream.readUntil("89");
			System.out.println(word);
			Assert.assertEquals(word, "67");
		}
	}
	
	@Test
	public void testReadSkip0() {
		StringTokenStream stringTokenStream = new StringTokenStream("   0123456789");
		{
			int n = stringTokenStream.readSkip(' ');
			System.out.println(n);
			Assert.assertEquals(n, 3);
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, '0');
		}
		{
			String word = stringTokenStream.read(3);
			System.out.println(word);
			Assert.assertEquals(word, "123");
		}
	}
	
	@Test
	public void testReadSkip1() {
		StringTokenStream stringTokenStream = new StringTokenStream("0123456789");
		{
			int n = stringTokenStream.readSkip(' ');
			System.out.println(n);
			Assert.assertEquals(n, 0);
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, '0');
		}
		{
			String word = stringTokenStream.read(3);
			System.out.println(word);
			Assert.assertEquals(word, "123");
		}
	}
	


	@Test
	public void testReadEqualIgnoreCase() {
		StringTokenStream stringTokenStream = new StringTokenStream("INSERT x");
		{
			boolean b = stringTokenStream.readEqualIgnoreCase("insert");
			Assert.assertEquals(b, true);
		}
		{
			char read = stringTokenStream.read();
			Assert.assertEquals(read, ' ');
		}
	}
	
	@Test
	public void testReadUntilIgnoreCase() {
		StringTokenStream stringTokenStream = new StringTokenStream("aBcDeFgHiJk");
		{
			String word = stringTokenStream.readUntilIgnoreCase("fgh");
			System.out.println(word);
			Assert.assertEquals(word, "aBcDe");
		}
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, 'F');
		}
		{
			String word = stringTokenStream.readUntilIgnoreCase("K");
			System.out.println(word);
			Assert.assertEquals(word, "gHiJ");
		}
	}
	
	@Test
	public void testReadComplete0() {
		StringTokenStream stringTokenStream = new StringTokenStream("aBcDeFgHiJk");
		{
			char read = stringTokenStream.read();
			System.out.println(read);
			Assert.assertEquals(read, 'a');
		}
		{
			String word = stringTokenStream.readComplete();
			System.out.println(word);
			Assert.assertEquals(word, "BcDeFgHiJk");
		}
	}
	
	@Test
	public void testReadComplete1() {
		StringTokenStream stringTokenStream = new StringTokenStream("aBcDeFgHiJk");
		{
			boolean b = stringTokenStream.readEqualIgnoreCase("abc");
			System.out.println(b);
			Assert.assertEquals(b, true);
		}
		{
			String word = stringTokenStream.readComplete();
			System.out.println(word);
			Assert.assertEquals(word, "DeFgHiJk");
		}
		
		{
			String word = stringTokenStream.readComplete();
			System.out.println(word);
			Assert.assertEquals(word, "");
		}
	}
	
	@Test
	public void testReadCompleteEndIndex0() {
		StringTokenStream stringTokenStream = new StringTokenStream("aBcDeFgHiJk");
		{
			boolean b = stringTokenStream.readEqualIgnoreCase("abc");
			System.out.println(b);
			Assert.assertEquals(b, true);
		}
		{
			String word = stringTokenStream.readComplete(8);
			System.out.println(word);
			Assert.assertEquals(word, "DeFgHi");
		}
		
		{
			String word = stringTokenStream.readComplete(8);
			System.out.println(word);
			Assert.assertEquals(word, "");
		}
	}
	
	@Test
	public void testReadCompleteEndIndex1() {
		StringTokenStream stringTokenStream = new StringTokenStream("aBcDeFgHiJkLmN");
		{
			boolean b = stringTokenStream.readEqualIgnoreCase("abc");
			System.out.println(b);
			Assert.assertEquals(b, true);
		}
		{
			String word = stringTokenStream.readComplete(8);
			System.out.println(word);
			Assert.assertEquals(word, "DeFgHi");
		}
		
		{
			char c = stringTokenStream.read();
			System.out.println(c);
			Assert.assertEquals(c, 'J');
		}
		
		{
			String word = stringTokenStream.readComplete();
			System.out.println(word);
			Assert.assertEquals(word, "kLmN");
		}
	}
}
