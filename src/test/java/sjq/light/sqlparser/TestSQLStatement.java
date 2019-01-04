package sjq.light.sqlparser;

import org.junit.Assert;
import org.junit.Test;

import sjq.light.sqlparser.statement.SqlStatement;

public class TestSQLStatement {
	
	@Test
	public void testTrim0() {
		SqlStatement sqlStatement = new SqlStatement("select * from a;");
		Assert.assertEquals("select * from a", sqlStatement.getTrimSql());
	}
	
	@Test
	public void testTrim1() {
		SqlStatement sqlStatement = new SqlStatement("  select * from a;  ");
		Assert.assertEquals("select * from a", sqlStatement.getTrimSql());
	}
	
	@Test
	public void testTrim2() {
		SqlStatement sqlStatement = new SqlStatement("  select * from a  ;  ");
		Assert.assertEquals("select * from a", sqlStatement.getTrimSql());
	}
}
