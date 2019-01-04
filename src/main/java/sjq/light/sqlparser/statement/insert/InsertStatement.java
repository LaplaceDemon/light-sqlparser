package sjq.light.sqlparser.statement.insert;

import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.FieldsStatement;
import sjq.light.sqlparser.statement.SqlStatement;
import sjq.light.sqlparser.statement.TableStatement;
import sjq.light.sqlparser.util.StringTokenStream;

public class InsertStatement extends SqlStatement {
    public InsertStatement(String sql) {
		super(sql);
		super.trim();
	}
    
    public TableStatement tableStatement;
    public FieldsStatement fieldsStatement;
	public FieldsStatement valuesStatement;
    
    public void parse() throws SqlParseException {
    	StringTokenStream sqlStream = new StringTokenStream(super.sql,super.start);
    	boolean b = sqlStream.readEqualIgnoreCase("insert");
    	if(!b) {
    		throw new SqlParseException();
    	}
    	
    	int readSkipN = sqlStream.readSkip(' ');
    	if(readSkipN == 0) {
    		throw new SqlParseException();
    	}
    	
    	b = sqlStream.readEqualIgnoreCase("into");
    	if(!b) {
    		throw new SqlParseException();
    	}
    	
    	readSkipN = sqlStream.readSkip(' ');
    	if(readSkipN == 0) {
    		throw new SqlParseException();
    	}
    	
    	String tableName = sqlStream.readUntil(' ');
    	System.out.println("tableName : " + tableName);
    	this.tableStatement = new TableStatement(tableName);
    	
    	readSkipN = sqlStream.readSkip(' ');
    	if(readSkipN == 0) {
    		throw new SqlParseException();
    	}
    	
    	char c = sqlStream.read();
    	sqlStream.pushBack();
    	if(c == '(') {
    		String columnsStr = sqlStream.readUntilIgnoreCase('(', ')',"VALUES");
    		System.out.println("columns : " + columnsStr);
    		this.fieldsStatement = new FieldsStatement(columnsStr);
    	}
    	
    	sqlStream.readSkipLen("values".length());
    	
    	sqlStream.readSkip(' ');
    	b = sqlStream.readEqual("(");
    	if(!b) {
    		throw new SqlParseException();
    	}
    	sqlStream.pushBack();
    	
    	String values = sqlStream.readComplete(super.end);
    	System.out.println("values : " + values);
    	this.valuesStatement = new FieldsStatement(values);
    }
    
}
