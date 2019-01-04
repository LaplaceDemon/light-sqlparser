package sjq.light.sqlparser.statement.create;

import sjq.light.sqlparser.statement.SqlStatement;

public class DBObjectDefStatement extends SqlStatement {

	private String name;
	
	public DBObjectDefStatement(String name, String sql) {
		super(sql);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
