package sjq.light.sqlparser.statement;

import java.util.ArrayList;
import java.util.List;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.atomic.SymbolExpression;
import sjq.light.expr.atomic.TupleExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;

public class TableStatement extends SqlStatement {
	boolean hasParse;
	Parser parser;
	int tableNum;
	List<String> tables;
	
	public TableStatement(String sql) {
		super(sql);
		parser = new Parser(sql);
		hasParse = false;
	}
	
	public int getTableNum() {
		return tableNum;
	}
	
	public List<String> getTables() {
		return tables;
	}
	
	public String getTableName(int index) throws ParseExpressionException {
		if(!hasParse) this.parse();
		return tables.get(index);
	}
	
	public void parse() throws ParseExpressionException {
		if(!hasParse) {
			BaseExpression baseExpression = parser.parse();
			if(baseExpression instanceof SymbolExpression) {
				this.tableNum = 1;
				this.tables = new ArrayList<>(this.tableNum);
				this.tables.add(baseExpression.toString());
			} else if (baseExpression instanceof TupleExpression) {
				TupleExpression tupleExpression = (TupleExpression)baseExpression;
				List<BaseExpression> expressionList = tupleExpression.getExpressionList();
				this.tableNum = expressionList.size();
				for(int i = 0;i<this.tableNum;i++) {
					this.tables.add(expressionList.get(i).toString());
				}
			}
		}
	}
	
}
