package sjq.light.sqlparser.statement.create.table;

import sjq.light.expr.BaseExpression;

public class ColumnDef {

	public ColumnDef() {
		this.isNull = false;
		this.unique = false;
		this.autoIncrement = false;
	}
	
	private String name;
	
	private BaseExpression type;
	
	private boolean isNull;
	
	private String defaultValue;
	
	private String comment;
	
	private boolean unique;
	
	private boolean autoIncrement;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BaseExpression getType() {
		return type;
	}

	public void setType(BaseExpression type) {
		this.type = type;
	}

	public boolean isNull() {
		return isNull;
	}

	public void setNull(boolean isNull) {
		this.isNull = isNull;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}
	
}
