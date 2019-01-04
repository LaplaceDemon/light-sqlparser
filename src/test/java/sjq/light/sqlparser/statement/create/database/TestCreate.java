package sjq.light.sqlparser.statement.create.database;

import org.junit.Assert;
import org.junit.Test;

import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.create.CreateStatement;
import sjq.light.sqlparser.statement.create.CreateType;
import sjq.light.sqlparser.statement.create.TableDefStatement;

public class TestCreate {
	
	@Test
	public void testParseDatabase0() throws SqlParseException, ParseExpressionException {
		CreateStatement createDatabaseStatement = new CreateStatement("create database `t_test_db`");
		createDatabaseStatement.parse();
		
		CreateType createType = createDatabaseStatement.getCreateType();
		Assert.assertEquals(createType, CreateType.CreateDatabase);
		
		String database = createDatabaseStatement.getDbObjectDefStatement().getName();
		Assert.assertEquals(database, "`t_test_db`");
	}
	
	@Test
	public void testParseDatabase1() throws SqlParseException, ParseExpressionException {
		CreateStatement createDatabaseStatement = new CreateStatement("create database t_test_db");
		createDatabaseStatement.parse();
		
		CreateType createType = createDatabaseStatement.getCreateType();
		Assert.assertEquals(createType, CreateType.CreateDatabase);
		
		String database = createDatabaseStatement.getDbObjectDefStatement().getName();
		Assert.assertEquals(database, "t_test_db");
	}
	
	
}
