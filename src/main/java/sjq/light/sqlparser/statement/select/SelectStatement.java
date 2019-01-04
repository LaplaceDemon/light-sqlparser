package sjq.light.sqlparser.statement.select;

import java.util.LinkedList;
import java.util.List;

import sjq.light.sqlparser.exception.SqlParseException;
import sjq.light.sqlparser.statement.ConditionStatement;
import sjq.light.sqlparser.statement.FieldsStatement;
import sjq.light.sqlparser.statement.SqlStatement;
import sjq.light.sqlparser.statement.TableStatement;
import sjq.light.sqlparser.util.StringTokenStream;
import sjq.light.sqlparser.util.StringUtil;

public class SelectStatement extends SqlStatement {
	
	public SelectStatement(String sql) {
		super(sql);
		super.trim();
		this.joinStatement = new LinkedList<>();
	}
	
	private ParseStatus parseStatus = ParseStatus.Select;
	
	FieldsStatement selectStatement;
	TableStatement fromStatement;
	ConditionStatement whereStatement;
	List<JoinStatement> joinStatement;
	GroupByStatement groupByStatement;
	ConditionStatement havingStatement;
	OrderByStatement orderByStatement;
	LimitStatement limitStatement;
	String joinTable;
	
	public FieldsStatement getFieldsStatement() {
		return selectStatement;
	}

	public TableStatement getFromStatement() {
		return fromStatement;
	}

	public ConditionStatement getWhereStatement() {
		return whereStatement;
	}

	public List<JoinStatement> getJoinStatement() {
		return joinStatement;
	}

	public GroupByStatement getGroupByStatement() {
		return groupByStatement;
	}

	public ConditionStatement getHavingStatement() {
		return havingStatement;
	}

	public OrderByStatement getOrderByStatement() {
		return orderByStatement;
	}

	public LimitStatement getLimitStatement() {
		return limitStatement;
	}

	public void parse() throws SqlParseException {
		StringTokenStream sqlStream = new StringTokenStream(super.sql,super.start);
    	boolean b = sqlStream.readEqualIgnoreCase("select");
    	if (!b) throw new SqlParseException();
    	
    	int n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	String fields = sqlStream.readUntilIgnoreCase('(', ')', "from");
    	System.out.println(parseStatus + ":" + fields);
    	initSubStatement(fields);
    	sqlStream.readSkipLen("from".length());
    	
    	n = sqlStream.readSkip(' ');
    	if(n == 0) throw new SqlParseException();
    	
    	parseStatus = ParseStatus.From;
    	
    	String parseStatementStr = "";
    	while (true) {
	    	char ch;
	    	while(true) {
		    	String parseStr = sqlStream.readUntilIgnoreCase('(', ')', " ");
		    	if(parseStr == null) {
		    		// over
		    		if(parseStatementStr.length() == 0) {
			    		parseStatementStr += sqlStream.readComplete(super.end);
			    	} else {
			    		parseStatementStr += " " + sqlStream.readComplete(super.end);
			    	}
		    		
		    		System.out.println(parseStatus + ":" + parseStatementStr);
		    		initSubStatement(parseStatementStr);
		    		return;
		    	}
		    	
		    	if(parseStatementStr.length() == 0) {
		    		parseStatementStr += parseStr;
		    	} else {
		    		parseStatementStr += " " + parseStr;
		    	}
		    	
		    	sqlStream.readSkip(' ');
		    	
		    	char read = sqlStream.read();
				ch = read;
		    	if(ch == ',' || ch == '=' || ch == '.') {
		    		parseStatementStr += ch;
		    		continue;
		    	} else {
		    		break;
		    	}
	    	}
	    	
	    	
	    	// LEFT 
	    	if(StringUtil.equalIgnoreCase(ch, 'l')) {
	    		char c = sqlStream.read();
	    		if(StringUtil.equalIgnoreCase(c, 'e')) {
	    			b = sqlStream.readEqualIgnoreCase("ft ");
		    		if(!b) continue;
		    		
		    		sqlStream.readSkip(' ');
		    		
		        	b = sqlStream.readEqualIgnoreCase("join");
		        	if(!b) throw new SqlParseException();
		        	
		        	n = sqlStream.readSkip(' ');
		        	if(n == 0) throw new SqlParseException();
		        	
		        	String leftjoin1 = sqlStream.readUntilIgnoreCase(" on ");
		        	joinTable = leftjoin1;
//		        	System.out.println("left join table:" + leftjoin1);
		        	
		        	sqlStream.readSkipLen(" on ".length());
	    			
		        	System.out.println(parseStatus + ":" + parseStatementStr);
		        	initSubStatement(parseStatementStr);
		        	parseStatementStr = "";
		        	parseStatus = ParseStatus.LeftJoinOn;
		        	
	    		} else if(StringUtil.equalIgnoreCase(c, 'i')) {
	    			b = sqlStream.readEqualIgnoreCase("mit ");
		    		if(!b) continue;
		    		
		    		sqlStream.readSkip(' ');
		    		
		    		System.out.println(parseStatus + ":" + parseStatementStr);
		    		initSubStatement(parseStatementStr);
		    		parseStatementStr = "";
		    		parseStatus = ParseStatus.Limit;
		    		
	    		} else {
	    			
	    			continue;
	    		}
	    		
	        	
	    	} else if (StringUtil.equalIgnoreCase(ch, 'r')) {
	    		b = sqlStream.readEqualIgnoreCase("ight ");
	    		if(!b) continue;
	    		
	    		sqlStream.readSkip(' ');
	    		
	        	b = sqlStream.readEqualIgnoreCase("join");
	        	if(!b) throw new SqlParseException();
	        	
	        	n = sqlStream.readSkip(' ');
	        	if(n == 0) throw new SqlParseException();
	        	
	        	String rightjoin1 = sqlStream.readUntilIgnoreCase(" on ");
	        	joinTable = rightjoin1;
	        	
	        	sqlStream.readSkipLen(" on ".length());
	        	
	        	System.out.println(parseStatus + ":" + parseStatementStr);
	        	initSubStatement(parseStatementStr);
	        	parseStatementStr = "";
	        	parseStatus = ParseStatus.RightJoinOn;
	        	
	    	} else if (StringUtil.equalIgnoreCase(ch, 'f')) {
	    		b = sqlStream.readEqualIgnoreCase("ull ");
	    		if(!b) continue;
	    		
	    		sqlStream.readSkip(' ');
	    		
	        	b = sqlStream.readEqualIgnoreCase("join");
	        	if(!b) throw new SqlParseException();
	        	
	        	n = sqlStream.readSkip(' ');
	        	if(n == 0) throw new SqlParseException();
	        	
	        	String fulljoin1 = sqlStream.readUntilIgnoreCase(" on ");
	        	joinTable = fulljoin1;
	        	
	        	sqlStream.readSkipLen(" on ".length());
	        	
	        	System.out.println(parseStatus + ":" + parseStatementStr);
	        	initSubStatement(parseStatementStr);
	        	parseStatementStr = "";
	        	parseStatus = ParseStatus.FullJoinOn;
	    		
	    	} else if (StringUtil.equalIgnoreCase(ch, 'i')) {
	    		b = sqlStream.readEqualIgnoreCase("nner ");
	    		if(!b) continue;
	    		
	    		sqlStream.readSkip(' ');
	    		
	        	b = sqlStream.readEqualIgnoreCase("join");
	        	if(!b) throw new SqlParseException();
	        	
	        	n = sqlStream.readSkip(' ');
	        	if(n == 0) throw new SqlParseException();
	        	
	        	String innnerjoin1 = sqlStream.readUntilIgnoreCase(" on ");
	        	joinTable = innnerjoin1;
	        	
	        	sqlStream.readSkipLen(" on ".length());
	        	
	        	System.out.println(parseStatus + ":" + parseStatementStr);
	        	initSubStatement(parseStatementStr);
	        	parseStatementStr = "";
	        	parseStatus = ParseStatus.InnerJoinOn;
	        	
	    	} else if (StringUtil.equalIgnoreCase(ch, 'u')) {
	    		b = sqlStream.readEqualIgnoreCase("nion ");
	    		if(!b) continue;
	    		
	    		n = sqlStream.readSkip(' ');
	    		
	    		char c = sqlStream.read();
	    		if(c == 'a') {
	    			b = sqlStream.readEqualIgnoreCase("ll ");
	    			if(!b) throw new SqlParseException();
	    		} else if(c == 's') {
	    			sqlStream.pushBack();
	    			// new 'select'
	    		}
	    		
	    	} else if (StringUtil.equalIgnoreCase(ch, 'w')) {
	    		// where 只能有1个，解析完，退出循环。
	    		b = sqlStream.readEqualIgnoreCase("here ");
	    		if(!b) continue;

	    		// 解析where
	    		sqlStream.readSkip(' ');
	    		
	    		System.out.println(parseStatus + ":" + parseStatementStr);
	    		initSubStatement(parseStatementStr);
	    		parseStatementStr = "";
	    		parseStatus = ParseStatus.Where;
	    		
	    	} else if (StringUtil.equalIgnoreCase(ch, 'g')) {
		    		// group by 只能有1个，解析完，退出循环。
	    		b = sqlStream.readEqualIgnoreCase("roup ");
	    		if(!b) continue;
	    		
	    		sqlStream.readSkip(' ');
	    		
	        	b = sqlStream.readEqualIgnoreCase("by");
	        	if(!b) throw new SqlParseException();
	        	
	        	n = sqlStream.readSkip(' ');
	        	if(n == 0) throw new SqlParseException();
	        	
	        	System.out.println(parseStatus + ":" + parseStatementStr);
	        	initSubStatement(parseStatementStr);
	        	parseStatementStr = "";
	        	parseStatus = ParseStatus.GroupBy;
		    		
	    	} else if (StringUtil.equalIgnoreCase(ch, 'o')) {
	    		// order by 只能有1个，解析完，退出循环。
	    		b = sqlStream.readEqualIgnoreCase("rder ");
	    		if(!b) {
	    			sqlStream.pushBack();
	    			continue;
	    		}
	    		
	    		sqlStream.readSkip(' ');
	    		
	        	b = sqlStream.readEqualIgnoreCase("by");
	        	if(!b) throw new SqlParseException();
	        	
	        	n = sqlStream.readSkip(' ');
	        	if(n == 0) throw new SqlParseException();
	        	
	        	System.out.println(parseStatus + ":" + parseStatementStr);
	        	initSubStatement(parseStatementStr);
	        	parseStatementStr = "";
	        	parseStatus = ParseStatus.OrderBy;
	    	} else {
	    		sqlStream.pushBack();
	    	}
    	}
    	
	}
	
	public void initSubStatement(String str) {
		switch (this.parseStatus) {
		case Select:
			this.selectStatement = new FieldsStatement(str);
			break;
		case From:
			this.fromStatement = new TableStatement(str);
			break;
		case Where:
			this.whereStatement = new ConditionStatement(str);
			break;
		case LeftJoinOn: {
			JoinStatement joinStatement = new JoinStatement(this.joinTable, str, JoinType.LeftJoin);
			this.joinStatement.add(joinStatement);
			break;
		}
		case RightJoinOn: {
			JoinStatement joinStatement = new JoinStatement(this.joinTable, str, JoinType.RightJoin);
			this.joinStatement.add(joinStatement);
			break;
		}
		case FullJoinOn: {
			JoinStatement joinStatement = new JoinStatement(this.joinTable, str, JoinType.FullJoin);
			this.joinStatement.add(joinStatement);
			break;
		}
		case InnerJoinOn: {
			JoinStatement joinStatement = new JoinStatement(this.joinTable, str, JoinType.InnerJoin);
			this.joinStatement.add(joinStatement);
			break;
		}
		case GroupBy: {
			this.groupByStatement = new GroupByStatement(str);
			break;
		}
		case Having: {
			this.havingStatement = new ConditionStatement(str);
			break;
		}
		case OrderBy: {
			this.orderByStatement = new OrderByStatement(str);
			break;
		}
		case Limit: {
			this.limitStatement = new LimitStatement(str);
			break;
		}
		default:
			break;
		}
			
		
	}

	

}
