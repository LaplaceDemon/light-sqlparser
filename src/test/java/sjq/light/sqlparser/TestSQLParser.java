package sjq.light.sqlparser;

import java.util.Arrays;

import org.junit.Test;

public class TestSQLParser {
	
	@Test
	public void testSplitTerm() {
		String[] splitTerm = SQLParser.splitTerm("select c1,c2,c3 from A as a where a.name= 'a' and a.age >10");
		System.out.println(Arrays.toString(splitTerm));
	}
	
	@Test
	public void testParse() {
		String[] splitTerm = SQLParser.splitTerm("select c1,c2,c3 from A as a where a.name= 'a' and a.age >10");
		System.out.println(Arrays.toString(splitTerm));
		
		SQLParser.parser("select c1,c2,c3 from A as a where a.name= 'a' and a.age >10");
	}
	
	
}
