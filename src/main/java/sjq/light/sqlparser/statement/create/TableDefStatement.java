package sjq.light.sqlparser.statement.create;

import java.util.ArrayList;
import java.util.List;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.CommonStatement;
import sjq.light.expr.atomic.NumberExpression;
import sjq.light.expr.atomic.PriorityExpression;
import sjq.light.expr.atomic.StringExpression;
import sjq.light.expr.atomic.SymbolExpression;
import sjq.light.expr.atomic.TupleExpression;
import sjq.light.expr.function.FuncExpression;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;
import sjq.light.expr.util.IncomputableException;
import sjq.light.sqlparser.statement.create.table.ColumnDef;

public class TableDefStatement extends DBObjectDefStatement {

	private List<ColumnDef> columnDefStatementList;
	private Parser parser;
	private String primarykey;
	
	public TableDefStatement(String name, String sql) {
		super(name, sql);
		this.parser = new Parser(sql);
	}
	
	
	public List<ColumnDef> getColumnDefStatementList() {
		return columnDefStatementList;
	}

	public Parser getParser() {
		return parser;
	}



	public String getPrimarykey() {
		return primarykey;
	}



	public void parse() throws ParseExpressionException {
		BaseExpression parseExpression = this.parser.parse();
		if(!(parseExpression instanceof PriorityExpression)) {
			throw new ParseExpressionException();
		}
		
		BaseExpression baseExpression = ((PriorityExpression)parseExpression).getBaseExpression();
		
		if(baseExpression instanceof TupleExpression) {
			TupleExpression tupleExpression = (TupleExpression)baseExpression;
			int size = tupleExpression.countExpressionList();
			this.columnDefStatementList = new ArrayList<>(size);
			List<BaseExpression> expressionList = tupleExpression.getExpressionList();
			for(int i = 0;i<size;++i) {
				BaseExpression expression = expressionList.get(i);
				if(expression instanceof CommonStatement) {
					ColumnDef columnDef = new ColumnDef();
					CommonStatement commonStatement = (CommonStatement)expression;
					int s = commonStatement.size();
					int index = 0;
					{
						BaseExpression elem0 = commonStatement.getIndex(index);
						String name = elem0.toString();
						if(name.equalsIgnoreCase("PRIMARY") && s == 2) {
							index++;
							if(index < s) {
								BaseExpression elem1 = commonStatement.getIndex(index);
								FuncExpression funcException = (FuncExpression)elem1;
								String funcName = funcException.getFunc();
								if(funcName.equalsIgnoreCase("KEY")) {
									BaseExpression keynameExpression = funcException.getPriorityExpression().getBaseExpression();
									if(keynameExpression instanceof SymbolExpression) {
										this.primarykey = keynameExpression.toString();
										break;
									} else {
										throw new ParseExpressionException();
									}
								}
							}
						} else {
							// not PRIMARY
							columnDef.setName(name);
							index++;
							if(index < s) {
								BaseExpression elem1 = commonStatement.getIndex(index);
								columnDef.setType(elem1);
								index++;
							}
						}
					}
					
					while(index < s) {
						BaseExpression keyWord0 = commonStatement.getIndex(index);
						if(keyWord0 instanceof SymbolExpression) {
							SymbolExpression symbolKeyWord = (SymbolExpression)keyWord0;
							String stringValue = symbolKeyWord.toString();
							if(stringValue.equalsIgnoreCase("not")) {
								index++;
								BaseExpression keyWord1 = commonStatement.getIndex(index);
								if(keyWord1 instanceof SymbolExpression && keyWord1.toString().equals("null")) {
									columnDef.setNull(true);
								}
							} else if(stringValue.equalsIgnoreCase("unique")) {
								columnDef.setUnique(true);
							} else if(stringValue.equalsIgnoreCase("AUTO_INCREMENT")) {
								columnDef.setAutoIncrement(true);
							} else if(stringValue.equalsIgnoreCase("DEFAULT")) {
								index++;
								BaseExpression valueExpression = commonStatement.getIndex(index);
								if(valueExpression instanceof StringExpression) {
									try {
										columnDef.setDefaultValue((String)valueExpression.eval());
									} catch (IncomputableException e) {
										throw new ParseExpressionException();
									}
								} else if(valueExpression instanceof NumberExpression) {
									try {
										columnDef.setDefaultValue(valueExpression.eval().toString());
									} catch (IncomputableException e) {
										throw new ParseExpressionException();
									}
								} else if(valueExpression instanceof SymbolExpression) {
									try {
										columnDef.setDefaultValue(valueExpression.eval().toString());
									} catch (IncomputableException e) {
										throw new ParseExpressionException();
									}
								} else if(valueExpression instanceof FuncExpression) {
									try {
										columnDef.setDefaultValue(valueExpression.eval().toString());
									} catch (IncomputableException e) {
										throw new ParseExpressionException();
									}
								}
								
							}
						}
						index++;
					}
					
					this.columnDefStatementList.add(columnDef);
					
				}
			}
		}
		
	}
	
	
}
