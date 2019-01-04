package sjq.light.sqlparser.statement.select;

import java.util.ArrayList;
import java.util.List;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.atomic.AttributeExpression;
import sjq.light.expr.atomic.SymbolExpression;
import sjq.light.expr.atomic.TupleExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;
import sjq.light.expr.sql.order.OrderType;
import sjq.light.sqlparser.statement.SqlStatement;

public class OrderByStatement extends SqlStatement {
	boolean hasParse;
	Parser parser;
	private List<BaseExpression> fields;
	private int size;
	
	public OrderByStatement(String sql) {
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
					BaseExpression baseExpression = expressionList.get(i);
					if(baseExpression instanceof sjq.light.expr.sql.order.OrderByStatement) {
						this.fields.add(baseExpression);
					} else if(baseExpression instanceof SymbolExpression || baseExpression instanceof AttributeExpression) {
						sjq.light.expr.sql.order.OrderByStatement order = new sjq.light.expr.sql.order.OrderByStatement((SymbolExpression)baseExpression, OrderType.Asc);
						this.fields.add(order);
					}
				}
			} else {
				this.size = 1;
				this.fields = new ArrayList<>(1);
				
				if(expression instanceof sjq.light.expr.sql.order.OrderByStatement) {
					this.fields.add(expression);
				} else if(expression instanceof SymbolExpression || expression instanceof AttributeExpression) {
					sjq.light.expr.sql.order.OrderByStatement order = new sjq.light.expr.sql.order.OrderByStatement((SymbolExpression)expression, OrderType.Asc);
					this.fields.add(order);
				}
				
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
