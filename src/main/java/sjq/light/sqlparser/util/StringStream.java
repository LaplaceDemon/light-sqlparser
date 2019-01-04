package sjq.light.sqlparser.util;

public class StringStream {
    protected String str;
    protected int index;
    
    public StringStream() {
    }

    public StringStream(String str) {
        this.str = str;
        this.index = 0;
    }
    
    public char next() {
        char ch = this.str.charAt(index);
        index++;
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
        index--;
    }

	public int getIndex() {
		return index;
	}

}
