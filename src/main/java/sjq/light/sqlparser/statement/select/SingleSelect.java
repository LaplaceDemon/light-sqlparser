package sjq.light.sqlparser.statement.select;

import java.util.ArrayList;
import java.util.List;

import sjq.light.sqlparser.statement.Condition;

public class SingleSelect {
	private List<String> selectColumns = new ArrayList<>();
	private String table;
	private List<List<Condition>> conditions = new ArrayList<>();

	public List<String> getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(List<String> selectColumns) {
		this.selectColumns = selectColumns;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public List<List<Condition>> getConditions() {
		return conditions;
	}

	public void setConditions(List<List<Condition>> conditions) {
		this.conditions = conditions;
	}

}
