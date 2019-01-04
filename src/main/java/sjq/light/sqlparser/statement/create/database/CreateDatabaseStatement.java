package sjq.light.sqlparser.statement.create.database;

import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.SqlStatement;
import sjq.light.sqlparser.util.StringTokenStream;

public class CreateDatabaseStatement extends SqlStatement {

	private String database;
	
	public CreateDatabaseStatement(String sql) {
		super(sql);
	}
	
	public void parse() throws SqlParseException, ParseExpressionException {
    	StringTokenStream sqlStream = new StringTokenStream(super.sql,super.start);
    	boolean b = sqlStream.readEqualIgnoreCase("create");
    	if(!b) throw new SqlParseException();
    	
    	int n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	b = sqlStream.readEqualIgnoreCase("database");
    	if(!b) throw new SqlParseException();
    	
    	n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	database = sqlStream.readComplete(super.end);
    }

	public String getDatabase() {
		return database;
	}
	
}
