package sjq.light.sqlparser;

import sjq.light.sqlparser.sqltype.DDLType;
import sjq.light.sqlparser.sqltype.DLType;
import sjq.light.sqlparser.sqltype.DMLType;
import sjq.light.sqlparser.sqltype.DetailType;

public class SQLParser {
	
	public static RichSQL richSQL(String sql){
		String[] sqlTerm = SQLParser.splitTerm(sql);
		SQLType type = SQLCategorizer.type(sqlTerm);
		RichSQL richSQL = new RichSQL(sql,sqlTerm,type);
		return richSQL;
	}
	
	public static String[] splitTerm (String sql) {
		String[] term = sql.split(" |,|=");
		return term;
	}
	
	
	public static void type(String[] sql){
		
	}
	
	public static void parser(String[] sql){
		
	}
	
	public static void parser(String sql){
    }
}


class SQLCategorizer {
	public static SQLType type (String[] splitSql){
		DLType dlType = null;
		DetailType detailType = null;
		if(splitSql[0].equalsIgnoreCase("create")){
			dlType = DLType.DDL;
			detailType = DDLType.CreateTable;
		} else if (splitSql[0].equalsIgnoreCase("alter")) {
			dlType = DLType.DDL;
			detailType = DDLType.DropTable;
		} else if (splitSql[0].equalsIgnoreCase("drop")) {
			dlType = DLType.DDL;
			detailType = DDLType.DropTable;
		} else if (splitSql[0].equalsIgnoreCase("insert")) {
			dlType = DLType.DDL;
			detailType = DMLType.Insert;
		} else if (splitSql[0].equalsIgnoreCase("update")) {
			dlType = DLType.DDL;
			detailType = DMLType.Update;
		} else if (splitSql[0].equalsIgnoreCase("delete")) {
			dlType = DLType.DDL;
			detailType = DMLType.Delete;
		} else if (splitSql[0].equalsIgnoreCase("select")) {
			dlType = DLType.DDL;
			detailType = DMLType.Select;
		}
		
		SQLType sqlType = new SQLType(dlType,detailType);
		return sqlType;
	}
}

