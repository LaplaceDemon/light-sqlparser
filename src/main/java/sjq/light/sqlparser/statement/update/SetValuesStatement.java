package sjq.light.sqlparser.statement.update;

import java.util.ArrayList;
import java.util.List;

import sjq.light.expr.BaseExpression;
import sjq.light.expr.atomic.TupleExpression;
import sjq.light.expr.equation.AssignmentStatement;
import sjq.light.expr.parse.ParseExpressionException;
import sjq.light.expr.parse.Parser;
import sjq.light.sqlparser.statement.SqlStatement;

public class SetValuesStatement extends SqlStatement {

	boolean hasParse;
	Parser parser;
	int assignmentStatementNum;
	List<AssignmentStatement> assignmentStatementList;
	
	public SetValuesStatement(String sql) {
		super(sql);
		parser = new Parser(sql);
		hasParse = false;
	}
	
	int setValueNum;

	public int getSetValueNum() {
		return setValueNum;
	}
	
	public void parse() throws ParseExpressionException {
		if(!hasParse) {
			BaseExpression baseExpression = this.parser.parse();
			if(baseExpression instanceof AssignmentStatement) {
				assignmentStatementNum = 1;
				assignmentStatementList = new ArrayList<>(assignmentStatementNum);
				assignmentStatementList.add((AssignmentStatement)baseExpression);
			} else if(baseExpression instanceof TupleExpression) {
				TupleExpression tupleExpression = (TupleExpression)baseExpression;
				List<BaseExpression> list = tupleExpression.getExpressionList();
				assignmentStatementNum = list.size();
				assignmentStatementList = new ArrayList<>(assignmentStatementNum);
				for(int i = 0;i<assignmentStatementNum;++i) {
					assignmentStatementList.add((AssignmentStatement)list.get(i));
				}
			}
		}
	}

	public List<AssignmentStatement> getExpression() throws ParseExpressionException {
		if(!hasParse) parse();
		return assignmentStatementList;
	}
	
	
}