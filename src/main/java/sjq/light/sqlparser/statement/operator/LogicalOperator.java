package sjq.light.sqlparser.statement.operator;

public abstract class LogicalOperator {
	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
