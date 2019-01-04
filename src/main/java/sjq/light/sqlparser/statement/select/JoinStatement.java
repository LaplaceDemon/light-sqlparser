package sjq.light.sqlparser.statement.select;


import sjq.light.expr.BaseExpression;
import sjq.light.expr.equation.AssignmentStatement;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;
import sjq.light.sqlparser.statement.SqlStatement;

public class JoinStatement extends SqlStatement {
	private boolean hasParse;
	private JoinType joinType;
	private String tableName;
	private Parser joinOnParser;
	private AssignmentStatement expression;
	
	public JoinStatement(String tableName, String sql, JoinType joinType) {
		super(sql);
		this.joinType = joinType;
		this.tableName = tableName;
		this.joinOnParser = new Parser(sql);
		hasParse = false;
	}

	public JoinType getJoinType() {
		return joinType;
	}

	public String getJoinTable() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return tableName;
	}

	public void parse() throws ParseExpressionException {
		if(!hasParse) {
			BaseExpression expression = this.joinOnParser.parse();
			if(expression instanceof AssignmentStatement) {
				this.expression = (AssignmentStatement)expression;
			} else {
				throw new ParseExpressionException();
			}
		}
	}

	public AssignmentStatement getJoinCondition() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return this.expression;
	}
	

}
