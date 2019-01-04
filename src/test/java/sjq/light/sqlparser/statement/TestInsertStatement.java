package sjq.light.sqlparser.statement;

import org.junit.Test;

import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.insert.InsertStatement;

public class TestInsertStatement {
	
	@Test
	public void testInsertParse0() throws SqlParseException {
		InsertStatement insertStatement = new InsertStatement("INSERT INTO Persons VALUES ('Gates', 'Bill', 'Xuanwumen 10', 'Beijing')");
		insertStatement.parse();
	}
	
	@Test
	public void testInsertParse1() throws SqlParseException {
		InsertStatement insertStatement = new InsertStatement("INSERT INTO Persons (LastName, Address) VALUES ('Wilson', 'Champs-Elysees')");
		insertStatement.parse();
	}
	
	@Test
	public void testInsertParse2() throws SqlParseException {
		InsertStatement insertStatement = new InsertStatement("INSERT INTO Persons (LastName, VALUES) VALUES ('Wilson', 'Champs-Elysees')");
		insertStatement.parse();
	}
}
