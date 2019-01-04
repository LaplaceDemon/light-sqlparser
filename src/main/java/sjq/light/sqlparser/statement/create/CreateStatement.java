package sjq.light.sqlparser.statement.create;


import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.SqlStatement;
import sjq.light.sqlparser.util.StringTokenStream;

public class CreateStatement extends SqlStatement {

	public CreateStatement(String sql) {
		super(sql);
	}
	
	private CreateType createType;
	
	private DBObjectDefStatement dbObjectDefStatement;
	
	public void parse() throws SqlParseException, ParseExpressionException {
		StringTokenStream sqlStream = new StringTokenStream(super.sql,super.start);
    	boolean b = sqlStream.readEqualIgnoreCase("create");
    	if(!b) throw new SqlParseException();
    	
    	int n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	String createTypeStr = sqlStream.readUntilIgnoreCase(" ");
    	if(!b) throw new SqlParseException();
    	if(createTypeStr.equalsIgnoreCase("table")) {
    		this.createType = CreateType.CreateTable;
    		
    		n = sqlStream.readSkip(' ');
        	if (n == 0) throw new SqlParseException();
        	
        	String targetName = sqlStream.readUntil(' ', '(');
        	String columnsDefStr = sqlStream.readComplete(super.end);
        	this.dbObjectDefStatement = new TableDefStatement(targetName, columnsDefStr);
    		
    	} else if(createTypeStr.equalsIgnoreCase("database")) {
    		this.createType = CreateType.CreateDatabase;
    		
    		n = sqlStream.readSkip(' ');
        	if(n == 0) throw new SqlParseException();
        	
        	String targetName = sqlStream.readComplete(super.end);
        	
        	this.dbObjectDefStatement = new DBObjectDefStatement(targetName, "");
        	
    		return;
    	}
    	
	}

	public CreateType getCreateType() {
		return createType;
	}

	public DBObjectDefStatement getDbObjectDefStatement() {
		return dbObjectDefStatement;
	}
	
}
