package sjq.light.sqlparser.statement;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.equation.AssignmentStatement;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.select.JoinStatement;
import sjq.light.sqlparser.statement.select.JoinType;
import sjq.light.sqlparser.statement.select.LimitStatement;
import sjq.light.sqlparser.statement.select.OrderByStatement;
import sjq.light.sqlparser.statement.select.SelectStatement;

public class TestSelectStatement {
	@Test
	public void testSelectBase0() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT * FROM Persons");
		selectStatement.parse();
		TableStatement fromStatement = selectStatement.getFromStatement();
		String tableName = fromStatement.getTableName(0);
		Assert.assertEquals(tableName, "Persons");
	}
	
	@Test
	public void testSelectBase1() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT LastName,FirstName FROM Persons");
		selectStatement.parse();
		
		TableStatement fromStatement = selectStatement.getFromStatement();
		String tableName = fromStatement.getTableName(0);
		Assert.assertEquals(tableName, "Persons");
		
		FieldsStatement fieldsStatement = selectStatement.getFieldsStatement();
		List<BaseExpression> fields = fieldsStatement.getFields();
		Assert.assertEquals(fields.toString(), "[LastName, FirstName]");
	}
	
	@Test
	public void testSelectBase3() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("select u_name,u_age,u_score from T_USER");
		selectStatement.parse();
		
		TableStatement fromStatement = selectStatement.getFromStatement();
		String tableName = fromStatement.getTableName(0);
		Assert.assertEquals(tableName, "T_USER");
		
		FieldsStatement fieldsStatement = selectStatement.getFieldsStatement();
		List<BaseExpression> fields = fieldsStatement.getFields();
		Assert.assertEquals(fields.toString(), "[u_name, u_age, u_score]");
		
	}
	
//	@Test
//	public void testSelectBase4() throws SqlParseException {
//		SelectStatement selectStatement = new SelectStatement("select u_name,u_age,u_score from T_USER");
//		selectStatement.parse();
//		
//		
//	}
	
	@Test
	public void testSelectWhere0() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT * FROM Persons WHERE City='Beijing'");
		selectStatement.parse();
		
		TableStatement fromStatement = selectStatement.getFromStatement();
		String tableName = fromStatement.getTableName(0);
		Assert.assertEquals(tableName, "Persons");
		
		FieldsStatement fieldsStatement = selectStatement.getFieldsStatement();
		List<BaseExpression> fields = fieldsStatement.getFields();
		Assert.assertEquals(fields.toString(), "[* < null , null >]");
		
		ConditionStatement whereStatement = selectStatement.getWhereStatement();
		BaseExpression expression = whereStatement.getExpression();
		Assert.assertEquals(expression.toString(), " = [ City , \"Beijing\" ]");
	}
	
	@Test
	public void testSelectLeftJoin0() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT column_name FROM table_name1 LEFT JOIN table_name2 ON table_name1.column_name = table_name2.column_name");
		selectStatement.parse();
		
		TableStatement fromStatement = selectStatement.getFromStatement();
		String tableName = fromStatement.getTableName(0);
		Assert.assertEquals(tableName, "table_name1");
		
		FieldsStatement fieldsStatement = selectStatement.getFieldsStatement();
		List<BaseExpression> fields = fieldsStatement.getFields();
		Assert.assertEquals(fields.toString(), "[column_name]");
		
		List<JoinStatement> joinStatement = selectStatement.getJoinStatement();
		Assert.assertNotNull(joinStatement);
		Assert.assertEquals(joinStatement.size(), 1);
		
		JoinType joinType = joinStatement.get(0).getJoinType();
		String joinTableName = joinStatement.get(0).getJoinTable();
		AssignmentStatement joinCondition = joinStatement.get(0).getJoinCondition();
		
		Assert.assertEquals(joinType, JoinType.LeftJoin);
		Assert.assertEquals(joinTableName, "table_name2");
		Assert.assertEquals(joinCondition.toString(), " = [ [table_name1, column_name] , [table_name2, column_name] ]");
	}
	
	@Test
	public void testSelectLeftJoinOrderBy0() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT Persons.LastName, Persons.FirstName, Orders.OrderNo FROM Persons LEFT JOIN Orders ON Persons.Id_P=Orders.Id_P ORDER BY Persons.LastName");
		selectStatement.parse();
		
		FieldsStatement fieldsStatement = selectStatement.getFieldsStatement();
		List<BaseExpression> fields = fieldsStatement.getFields();
		Assert.assertEquals(fields.toString(), "[[Persons, LastName], [Persons, FirstName], [Orders, OrderNo]]");
		
		TableStatement fromStatement = selectStatement.getFromStatement();
		String tableName = fromStatement.getTableName(0);
		Assert.assertEquals(tableName, "Persons");
		
		List<JoinStatement> joinStatement = selectStatement.getJoinStatement();
		Assert.assertNotNull(joinStatement);
		Assert.assertEquals(joinStatement.size(), 1);
		
		JoinType joinType = joinStatement.get(0).getJoinType();
		String joinTableName = joinStatement.get(0).getJoinTable();
		AssignmentStatement joinCondition = joinStatement.get(0).getJoinCondition();
		
		Assert.assertEquals(joinType, JoinType.LeftJoin);
		Assert.assertEquals(joinTableName, "Orders");
		Assert.assertEquals(joinCondition.toString(), " = [ [Persons, Id_P] , [Orders, Id_P] ]");
		
		OrderByStatement orderByStatement = selectStatement.getOrderByStatement();
		Assert.assertEquals(orderByStatement.getField(0).toString(), "[Persons, LastName]");
	}
	
	@Test
	public void testSelectLeftJoinOrderBy1() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT Persons.LastName, Persons.FirstName, Orders.OrderNo FROM Persons LEFT JOIN Orders ON Persons.Id_P=Orders.Id_P ORDER BY Persons.LastName DESC");
		selectStatement.parse();
		
		OrderByStatement orderByStatement = selectStatement.getOrderByStatement();
		Assert.assertEquals(orderByStatement.getField(0).toString(), "Desc[Persons, LastName]");
	}
	
	@Test
	public void testSelectLeftJoinOrderBy2() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT Persons.LastName, Persons.FirstName, Orders.OrderNo FROM Persons LEFT JOIN Orders ON Persons.Id_P=Orders.Id_P ORDER BY Persons.LastName DESC, Orders.OrderNo");
		selectStatement.parse();
		
		OrderByStatement orderByStatement = selectStatement.getOrderByStatement();
		Assert.assertEquals(orderByStatement.getField(0).toString(), "Desc[Persons, LastName]");
		Assert.assertEquals(orderByStatement.getField(1).toString(), "Asc[Orders, OrderNo]");
	}
	
	@Test
	public void testSelectLimit0() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT column_name FROM table_name1 limit 20");
		selectStatement.parse();
		LimitStatement limitStatement = selectStatement.getLimitStatement();
		long skip = limitStatement.getSkip();
		long limit = limitStatement.getLimit();
		Assert.assertEquals(skip, 0);
		Assert.assertEquals(limit, 20);
	}
	
	@Test
	public void testSelectLimit1() throws SqlParseException, ParseExpressionException {
		SelectStatement selectStatement = new SelectStatement("SELECT column_name FROM table_name1 limit 10, 20");
		selectStatement.parse();
		LimitStatement limitStatement = selectStatement.getLimitStatement();
		long skip = limitStatement.getSkip();
		long limit = limitStatement.getLimit();
		Assert.assertEquals(skip, 10);
		Assert.assertEquals(limit, 20);
	}
	
	@Test
	public void testSelectRightJoin() throws SqlParseException {
		SelectStatement selectStatement = new SelectStatement("SELECT s FROM table_name1 RIGHT JOIN table_name2 ON table_name1.column_name=table_name2.column_name");
		selectStatement.parse();
	}

}
