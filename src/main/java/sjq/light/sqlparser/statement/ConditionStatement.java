package sjq.light.sqlparser.statement;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;

public class ConditionStatement extends SqlStatement {
	boolean hasParse;
	Parser parser;
	BaseExpression expression;
	
	public ConditionStatement(String sql) {
		super(sql);
		parser = new Parser(sql);
	}
	
	public void parse() throws ParseExpressionException {
		if(!hasParse) {
			this.expression = parser.parse();
		}
	}

	public BaseExpression getExpression() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return this.expression;
	}

}
