package sjq.light.sqlparser.statement.create.database;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.create.CreateStatement;
import sjq.light.sqlparser.statement.create.CreateType;
import sjq.light.sqlparser.statement.create.TableDefStatement;
import sjq.light.sqlparser.statement.create.table.ColumnDef;

public class TestCreateTable {
	
	@Test
	public void testParse1() throws SqlParseException, ParseExpressionException {
		CreateStatement createDatabaseStatement = new CreateStatement("CREATE TABLE Persons(Id_P int,LastName varchar(255), FirstName varchar(255), Address varchar(255), City varchar(255))");
		createDatabaseStatement.parse();

		String database = createDatabaseStatement.getDbObjectDefStatement().getName();
		Assert.assertEquals(database, "Persons");
		
		CreateType createType = createDatabaseStatement.getCreateType();
		Assert.assertEquals(createType, CreateType.CreateTable);
		
		TableDefStatement tableDefStatement = (TableDefStatement)createDatabaseStatement.getDbObjectDefStatement();
		tableDefStatement.parse();
		
		List<ColumnDef> columnDefStatementList = tableDefStatement.getColumnDefStatementList();
		
		{
			ColumnDef columnDef = columnDefStatementList.get(0);
			Assert.assertEquals(columnDef.getName(), "Id_P");
			Assert.assertEquals(columnDef.getType().toString(), "int");
		}
		
		{
			ColumnDef columnDef = columnDefStatementList.get(1);
			Assert.assertEquals(columnDef.getName(), "LastName");
			Assert.assertEquals(columnDef.getType().toString(), "varchar < 255 >");
		}
	}
}
