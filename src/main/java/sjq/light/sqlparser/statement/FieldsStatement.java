package sjq.light.sqlparser.statement;

import java.util.ArrayList;
import java.util.List;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.atomic.TupleExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;

public class FieldsStatement extends SqlStatement {
	boolean hasParse;
	Parser parser;
	private List<BaseExpression> fields;
	private int size;
	
	public FieldsStatement(String sql) {
		super(sql);
		super.trim();
		this.parser = new Parser(sql);
		this.hasParse = false;
	}
	
	public void parse() throws ParseExpressionException {
		if(!hasParse) {
			BaseExpression expression = this.parser.parse();
			if(expression instanceof TupleExpression) {
				TupleExpression tupleExpression = (TupleExpression)expression;
				List<BaseExpression> expressionList = tupleExpression.getExpressionList();
				this.size = expressionList.size();
				this.fields = new ArrayList<>(this.size);
				for(int i = 0; i < this.size; ++i) {
					this.fields.add(expressionList.get(i));
				}
			} else {
				this.size = 1;
				this.fields = new ArrayList<>(1);
				this.fields.add(expression);
			}
			
			hasParse = true;
		}
	}
	
	public int size() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return this.size;
	}
	
	public List<BaseExpression> getFields() throws ParseExpressionException {
		if(!hasParse) this.parse();
		return this.fields;
	}
	
	public BaseExpression getField(int index) throws ParseExpressionException {
		if(!hasParse) this.parse();
		return this.fields.get(index);
	}

}
