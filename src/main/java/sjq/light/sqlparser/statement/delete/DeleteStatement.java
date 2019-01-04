package sjq.light.sqlparser.statement.delete;

import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.ConditionStatement;
import sjq.light.sqlparser.statement.SqlStatement;
import sjq.light.sqlparser.statement.TableStatement;
import sjq.light.sqlparser.util.StringTokenStream;

public class DeleteStatement extends SqlStatement {
    public DeleteStatement(String sql) {
		super(sql);
		super.trim();
	}
	
    private TableStatement tableStatement;
    private ConditionStatement conditionStatement;
	
    public TableStatement getTableStatement() {
		return tableStatement;
	}

	public ConditionStatement getConditionStatement() {
		return conditionStatement;
	}


	public void parse() throws SqlParseException {
    	StringTokenStream sqlStream = new StringTokenStream(super.sql,super.start);
    	boolean b = sqlStream.readEqualIgnoreCase("delete");
    	if(!b) {
    		throw new SqlParseException();
    	}
    	
    	int n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	char ch = sqlStream.read();
    	if(ch == '*') {
    		n = sqlStream.readSkip(' ');
        	if(n == 0) throw new SqlParseException();
    	} else if (ch == 'F') {
    		sqlStream.pushBack();
    	}
    	
    	b = sqlStream.readEqualIgnoreCase("from");
    	if(!b) throw new SqlParseException();
    	
    	n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	String tableName = sqlStream.readUntil(' ');
    	System.out.println("table:" + tableName);
    	this.tableStatement = new TableStatement(tableName);
    	
    	n = sqlStream.readSkip(' ');
    	
    	b = sqlStream.readEqualIgnoreCase("where");
    	if(!b) throw new SqlParseException();
    	
    	n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	String where = sqlStream.readComplete(super.end);
    	System.out.println("where:" + where);
    	this.conditionStatement = new ConditionStatement(where);
    }
}
