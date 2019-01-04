package sjq.light.sqlparser;

import sjq.light.sqlparser.sqltype.DLType;
import sjq.light.sqlparser.sqltype.DetailType;

public class SQLType {
	private DLType dlType;
	private DetailType detailType;

	public SQLType(DLType dlType, DetailType detailType) {
		super();
		this.dlType = dlType;
		this.detailType = detailType;
	}

	public DLType getDlType() {
		return dlType;
	}

	public DetailType getDetailType() {
		return detailType;
	}

}
