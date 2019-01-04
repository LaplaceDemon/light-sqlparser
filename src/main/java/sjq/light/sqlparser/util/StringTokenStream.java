package sjq.light.sqlparser.util;

public class StringTokenStream extends StringStream {
	
	public StringTokenStream(String str) {
		this.str = str;
		this.index = 0;
	}
	
	public StringTokenStream(String str, int index) {
		this.str = str;
		this.index = index;
	}
	
	public char read() {
		return this.next();
	}
	
	public String read(int len) {
		String copySubString = str.substring(this.index, this.index + len);
		this.index += len;
		return copySubString;
	}
	
	/**
	 * ²»°üÀ¨ untilChar
	 * @param untilChar
	 * @return
	 */
	public String readUntil(char untilChar) {
		int startIndex = this.index;
		while(super.hasNext()) {
			char nextChar = super.next();
			if(nextChar == untilChar) {
				break;
			}
		}
		
		this.index --;
		String str =  this.str.substring(startIndex, this.index);
		return str;
	}
	
	public String readUntil(char... chs) {
		int startIndex = this.index;
		boolean flag = false;
		while(super.hasNext()) {
			char nextChar = super.next();
			for(char ch : chs) {
				if(nextChar == ch) {
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		
		this.index --;
		String str =  this.str.substring(startIndex, this.index);
		return str;
	}
	
	public String readUntil(String untilStr) {
		int startIndex = this.index;
		int endIndex = this.str.indexOf(untilStr, this.index);
		String str =  this.str.substring(startIndex, endIndex);
		this.index = endIndex;
		return str;
	}
	
	public boolean readEqual(String str) {
		int startIndex = this.index;
		int i = 0;
		int strLen = str.length();
		while(super.hasNext()) {
			char nextChar = super.next();
			char strChar = str.charAt(i);
			if(nextChar != strChar) {
				this.index = startIndex;
				return false;
			}
			i++;
			if(i >= strLen) {
				break;
			}
		}
		
		return true;
	}
	
	public boolean readEqualIgnoreCase(String str) {
		int startIndex = this.index;
		int i = 0;
		int strLen = str.length();
		while(super.hasNext()) {
			char nextChar = super.next();
			if(nextChar>=65 && nextChar<=90) nextChar += 32;
			char strChar = str.charAt(i);
			if(strChar>=65 && strChar<=90) strChar += 32;
			if(nextChar != strChar) {
				this.index = startIndex;
				return false;
			}
			i++;
			if(i >= strLen) {
				break;
			}
		}
		
		return true;
	}
	
	public int readSkip(char ch) {
		int i = 0;
		while(super.hasNext()) {
			i++;
			char nextChar = super.next();
			if(nextChar != ch) {
				break;
			}
		}
		i--;
		this.index--;
		return i;
	}
	
	public void readSkipLen(int len) {
		this.index+=len;
	}

	public String readUntilIgnoreCase(String untilStr) {
		int startIndex = this.index;
		int endIndex = StringUtil.indexOfIgnoreCase(this.str, untilStr, this.index);
		String str =  this.str.substring(startIndex, endIndex);
		this.index = endIndex;
		return str;
	}
	
	public String readUntilIgnoreCase(char notin0, char notin1, String untilStr) {
		int startIndex = this.index;
		int endIndex = StringUtil.indexOfIgnoreCase(this.str, untilStr, this.index, notin0, notin1);
		if(endIndex == -1) {
			return null;
		}
		String str =  this.str.substring(startIndex, endIndex);
		this.index = endIndex;
		return str;
	}
	
	public String readUntilIgnoreCase(char notin, String untilStr) {
		int startIndex = this.index;
		int endIndex = StringUtil.indexOfIgnoreCase(this.str, untilStr, this.index, notin);
		String str =  this.str.substring(startIndex, endIndex);
		this.index = endIndex;
		return str;
	}
	
	public String readComplete() {
		String str =  this.str.substring(this.index, this.str.length());
		this.index = this.str.length();
		return str;
	}

	public String readComplete(int endIndex) {
		String str =  this.str.substring(this.index, endIndex + 1);
		this.index = endIndex+1;
		return str;
	}

	@Override
	public String toString() {
		return "[str=" + str + ", index=" + index + ", char=" + str.charAt(index) + "]";
	}

	
}
