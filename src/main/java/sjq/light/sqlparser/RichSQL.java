package sjq.light.sqlparser;

public class RichSQL {
	private String sql;
	private String[] sqlTerms;
	private SQLType sqlType;

	public RichSQL(String sql, String[] sqlTerms, SQLType sqlType) {
		super();
		this.sql = sql;
		this.sqlTerms = sqlTerms;
		this.sqlType = sqlType;
	}

	public String getSql() {
		return sql;
	}

	public SQLType getSqlType() {
		return sqlType;
	}

	public String[] getSqlTerms() {
		return sqlTerms;
	}
	
}