package sjq.light.sqlparser.statement.update;

import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.ConditionStatement;
import sjq.light.sqlparser.statement.SqlStatement;
import sjq.light.sqlparser.util.StringTokenStream;

public class UpdateStatement extends SqlStatement {
    public UpdateStatement(String sql) {
		super(sql);
		super.trim();
	}
    
    private SetValuesStatement setValuesStatement;
    private ConditionStatement conditionStatement;
    
    public void parse() throws SqlParseException {
    	StringTokenStream sqlStream = new StringTokenStream(super.sql,super.start);
    	boolean b = sqlStream.readEqualIgnoreCase("update");
    	if (!b) throw new SqlParseException();

    	int n = sqlStream.readSkip(' ');
    	if (n == 0) throw new SqlParseException();
    	
    	String tableName = sqlStream.readUntil(' ');
    	System.out.println("tableName : " + tableName);
    	
    	sqlStream.readSkip(' ');
    	
    	b = sqlStream.readEqualIgnoreCase("set");
    	if (!b) throw new SqlParseException();
    	
    	n = sqlStream.readSkip(' ');
    	if (n == 0) throw new SqlParseException();
    	
    	String set = sqlStream.readUntilIgnoreCase('\'', "where");
    	System.out.println("set:" + set);
    	this.setValuesStatement = new SetValuesStatement(set);
    	
    	sqlStream.readSkipLen("where".length());
    	
    	n = sqlStream.readSkip(' ');
    	if (n == 0) throw new SqlParseException();
    	
    	String where = sqlStream.readComplete(this.end);
    	System.out.println("where:" + where);
    	this.conditionStatement = new ConditionStatement(where);
    }

	public SetValuesStatement getSetValuesStatement() {
		return setValuesStatement;
	}

	public ConditionStatement getConditionStatement() {
		return conditionStatement;
	}
    
    
}
