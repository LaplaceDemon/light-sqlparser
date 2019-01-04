package sjq.light.sqlparser.statement;

import org.junit.Assert;
import org.junit.Test;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.delete.DeleteStatement;

public class TestDeleteStatement {
	@Test
	public void testDeleteParse0() throws SqlParseException {
		DeleteStatement deleteStatement = new DeleteStatement("DELETE FROM Person WHERE LastName = 'Wilson'");
		deleteStatement.parse();
	}
	
	@Test
	public void testDeleteParse1() throws SqlParseException {
		DeleteStatement deleteStatement = new DeleteStatement("DELETE * FROM Person WHERE LastName = 'Wilson'");
		deleteStatement.parse();
	}
	
	@Test
	public void testDeleteParseTable() throws SqlParseException, ParseExpressionException {
		DeleteStatement deleteStatement = new DeleteStatement("DELETE * FROM Person WHERE LastName = 'Wilson'");
		deleteStatement.parse();
		
		String tableName = deleteStatement.getTableStatement().getTableName(0);
		Assert.assertEquals(tableName, "Person");
	}
	
	@Test
	public void testDeleteParseWhere() throws SqlParseException, ParseExpressionException {
		DeleteStatement deleteStatement = new DeleteStatement("DELETE * FROM Person WHERE LastName = 'Wilson'");
		deleteStatement.parse();
		
		ConditionStatement conditionStatement = deleteStatement.getConditionStatement();
		
		BaseExpression expression = conditionStatement.getExpression();
		System.out.println(expression);
	}
}
