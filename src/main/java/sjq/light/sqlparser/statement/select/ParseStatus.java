package sjq.light.sqlparser.statement.select;

public enum ParseStatus {
	Select,
	From,
	Where,
	LeftJoinOn,
	RightJoinOn,
	FullJoinOn,
	InnerJoinOn,
	GroupBy,
	Having,
	OrderBy,
	Limit
}
