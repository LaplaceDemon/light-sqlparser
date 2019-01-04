package sjq.light.sqlparser.util;

public class ReverseStringStream extends StringStream {

    public ReverseStringStream(String expression) {
        this.str = expression;
        this.index = this.str.length()-1;
    }
    
    public char next() {
        char ch = this.str.charAt(index);
        index--;
        return ch;
    }
    
    public boolean hasNext() {
    	if(this.str.length() <= 0) {
    		return false;
    	}
    	
        if(index >= this.str.length()) {
            return false;
        } else {
            return true;
        }
    }

    public void pushBack() {
        index++;
    }

	public int getIndex() {
		return index;
	}
}
