package sjq.light.sqlparser.util;

public class StringUtil {
	public static int indexOfIgnoreCase(String str, String findStr, int searchIndex) {
		int untilStrLength = findStr.length();
		int strLength = str.length();
		char firstChatr = findStr.charAt(0);
    	if(firstChatr > 65  && firstChatr < 90) firstChatr += 32;
        for (int i = searchIndex; i < strLength; ++i) {
        	char charAt = str.charAt(i);
        	if(charAt > 65  && charAt < 90) charAt += 32;
        	if(charAt != firstChatr) {
    			continue;
    		}
        	
        	boolean flag = true;
        	for (int j = 1; j < untilStrLength; ++j) {
        		char strChar = str.charAt(i + j);
        		if(strChar > 65  && strChar < 90) strChar += 32;
        		char findStrChar = findStr.charAt(j);
        		if(findStrChar > 65  && findStrChar < 90) findStrChar += 32;
        		if(strChar != findStrChar) {
        			flag = false;
        			break;
        		}
        	}
        	
        	if(flag) {
        		return i;
        	}
        }
        
        return -1;
	}
	
	public static int indexOfIgnoreCase(String str, String findStr, int searchIndex, char notin) {
		int untilStrLength = findStr.length();
		int strLength = str.length();
		char firstChatr = findStr.charAt(0);
    	if(firstChatr > 65  && firstChatr < 90) firstChatr += 32;
    	int inLevel = 0;
        for (int i = searchIndex; i < strLength; ++i) {
        	char charAt = str.charAt(i);
        	
        	if(charAt == notin) {
        		if(inLevel == 0) {
        			inLevel++;
        		} else {
        			inLevel--;
        		}
        	}
        	
        	if(inLevel > 0) {
        		continue;
        	}
        	
        	if(charAt > 65  && charAt < 90) charAt += 32;
        	if(charAt != firstChatr) {
    			continue;
    		}
        	
        	boolean flag = true;
        	for (int j = 1; j < untilStrLength; ++j) {
        		char strChar = str.charAt(i + j);
        		if(strChar > 65  && strChar < 90) strChar += 32;
        		char findStrChar = findStr.charAt(j);
        		if(findStrChar > 65  && findStrChar < 90) findStrChar += 32;
        		if(strChar != findStrChar) {
        			flag = false;
        			break;
        		}
        	}
        	
        	if(flag) {
        		return i;
        	}
        }
        
        return -1;
	}
	
	public static int indexOfIgnoreCase(String str, String findStr, int searchIndex, char notin0, char notin1) {
		int untilStrLength = findStr.length();
		int strLength = str.length();
		char firstChatr = findStr.charAt(0);
    	if(firstChatr > 65  && firstChatr < 90) firstChatr += 32;
    	int inLevel = 0;
        for (int i = searchIndex; i < strLength; ++i) {
        	char charAt = str.charAt(i);
        	
        	if(charAt == notin0) {
        		inLevel++;
        	} else if(charAt == notin1){
        		inLevel--;
        	}
        	
        	if(inLevel > 0) {
        		continue;
        	}
        	
        	if(charAt > 65  && charAt < 90) charAt += 32;
        	if(charAt != firstChatr) {
    			continue;
    		}
        	
        	boolean flag = true;
        	for (int j = 1; j < untilStrLength; ++j) {
        		char strChar = str.charAt(i + j);
        		if(strChar > 65  && strChar < 90) strChar += 32;
        		char findStrChar = findStr.charAt(j);
        		if(findStrChar > 65  && findStrChar < 90) findStrChar += 32;
        		if(strChar != findStrChar) {
        			flag = false;
        			break;
        		}
        	}
        	
        	if(flag) {
        		return i;
        	}
        }
        
        return -1;
	}

	public static boolean equalIgnoreCase(char ch0, char ch1) {
		if(ch0 > 65  && ch0 < 90) ch0 += 32;
		if(ch1 > 65  && ch1 < 90) ch1 += 32;
		if(ch0 == ch1) {
			return true;
		}
		return false;
	}

//	public static int indexOfIgnoreCase(String str, int searchIndex, String[] strSet) {
////		int untilStrLength = findStr.length();
//		int strLength = str.length();
////		char firstChatr = findStr.charAt(0);
//    	if(firstChar > 65  && firstChar < 90) firstChar += 32;
//        for (int i = searchIndex; i < strLength; ++i) {
//        	char charAt = str.charAt(i);
//        	if(charAt > 65  && charAt < 90) charAt += 32;
//        	if(charAt != firstChatr) {
//    			continue;
//    		}
//        	
//        	boolean flag = true;
//        	for (int j = 1; j < untilStrLength; ++j) {
//        		char strChar = str.charAt(i + j);
//        		if(strChar > 65  && strChar < 90) strChar += 32;
//        		char findStrChar = findStr.charAt(j);
//        		if(findStrChar > 65  && findStrChar < 90) findStrChar += 32;
//        		if(strChar != findStrChar) {
//        			flag = false;
//        			break;
//        		}
//        	}
//        	
//        	if(flag) {
//        		return i;
//        	}
//        }
//        
//        return -1;
//	}
}
