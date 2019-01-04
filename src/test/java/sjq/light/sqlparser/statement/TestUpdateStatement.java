package sjq.light.sqlparser.statement;

import java.util.List;

import org.junit.Test;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.equation.AssignmentStatement;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.update.SetValuesStatement;
import sjq.light.sqlparser.statement.update.UpdateStatement;

public class TestUpdateStatement {
	@Test
	public void testUpdateParse0() throws SqlParseException {
		UpdateStatement updateStatement = new UpdateStatement("UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson'");
		updateStatement.parse();
	}
	
	@Test
	public void testUpdateParse1() throws SqlParseException {
		UpdateStatement updateStatement = new UpdateStatement("UPDATE Person SET Address = 'Zhongshan 23', City = 'WHERE' WHERE LastName = 'Wilson'");
		updateStatement.parse();
	}
	
	@Test
	public void testUpdateParseWhere() throws SqlParseException, ParseExpressionException {
		UpdateStatement updateStatement = new UpdateStatement("UPDATE Person SET Address = 'Zhongshan 23', City = 'WHERE' WHERE LastName = 'Wilson'");
		updateStatement.parse();
		
		ConditionStatement conditionStatement = updateStatement.getConditionStatement();
		
		BaseExpression expression = conditionStatement.getExpression();
		System.out.println(expression);
	}
	
	@Test
	public void testUpdateParseSetValue() throws SqlParseException, ParseExpressionException {
		UpdateStatement updateStatement = new UpdateStatement("UPDATE Person SET Address = 'Zhongshan 23', City = 'WHERE' WHERE LastName = 'Wilson'");
		updateStatement.parse();
		
		SetValuesStatement setValuesStatement = updateStatement.getSetValuesStatement();
		
		List<AssignmentStatement> expression = setValuesStatement.getExpression();
		System.out.println(expression);
	}
}
