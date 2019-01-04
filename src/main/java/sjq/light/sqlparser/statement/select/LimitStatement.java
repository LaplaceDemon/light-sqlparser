package sjq.light.sqlparser.statement.select;

import java.util.List;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.atomic.NumberExpression;
import sjq.light.expr.atomic.TupleExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;
import sjq.light.sqlparser.statement.SqlStatement;

public class LimitStatement extends SqlStatement {
	private long skip;
	private long limit;
	
	boolean hasParse;
	Parser parser;
	
	public LimitStatement(String sql) {
		super(sql);
		super.trim();
		this.parser = new Parser(sql);
		this.hasParse = false;
		this.skip = 0;
		this.limit = 0;
	}
	
	public void parse() throws ParseExpressionException {
		if(!hasParse) {
			try {
				BaseExpression expression = this.parser.parse();
				if(expression instanceof TupleExpression) {
					TupleExpression tupleExpression = (TupleExpression)expression;
					List<BaseExpression> expressionList = tupleExpression.getExpressionList();
					int size = expressionList.size();
					if(size == 1) {
						limit = (Long)(((NumberExpression)expressionList.get(0)).eval());
					} else if(size == 2) {
						skip = (Long)(((NumberExpression)expressionList.get(0)).eval());
						limit = (Long)(((NumberExpression)expressionList.get(1)).eval());
					}
				} else {
					this.limit = (Long)(((NumberExpression)expression).eval());
				}
				
				hasParse = true;
			} catch(Exception ex) {
				throw new ParseExpressionException();
			}
		}
	}

	public long getSkip() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return skip;
	}

	public long getLimit() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return limit;
	}
	
}