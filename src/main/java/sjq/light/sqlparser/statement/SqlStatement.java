package sjq.light.sqlparser.statement;

import java.util.Objects;

import sjq.light.sqlparser.util.ReverseStringStream;
import sjq.light.sqlparser.util.StringStream;

public class SqlStatement {
	protected String sql;
	protected int start;
	protected int end;
	
	public SqlStatement(String sql, int start, int end) {
		Objects.requireNonNull(sql);
		this.sql = sql;
		this.start = start;
		this.end = end;
	}
	
	public SqlStatement(String sql) {
		Objects.requireNonNull(sql);
		this.sql = sql;
		this.start = 0;
		this.end = this.sql.length() - 1;
	}
	
	public void trim() {
		{
			StringStream strStream = new StringStream(sql);
			while(strStream.hasNext()) {
				char ch = strStream.next();
				if(ch == ' ') {
					continue;
				}
				
				break;
			}
			this.start = strStream.getIndex()-1;
		}
		
		{
			ReverseStringStream strReverseStream = new ReverseStringStream(sql);
			while(strReverseStream.hasNext()) {
				char ch = strReverseStream.next();
				if(ch == ' ' || ch == ';') {
					continue;
				}
				
				break;
			}
			this.end = strReverseStream.getIndex() + 1;
		}
	}

	public String getSql() {
		return sql;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public String getTrimSql() {
		return this.sql.substring(this.start, this.end + 1);
	}
	
}
