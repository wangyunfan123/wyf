import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

//Only numbers and tools can be entered
public class NumberTextField extends PlainDocument {

    public NumberTextField() {
        super();
    }
    public void insertString
            (int offset, String str, AttributeSet attr)
            throws BadLocationException {
        if (str == null){
            return;
        }
        char[] upper = str.toCharArray();
        int length=0;
        for (int i = 0; i < upper.length; i++) {
            // Limit only numbers
            if (upper[i]>='0' && upper[i]<='9'){
                upper[length++] = upper[i];
            }
        }
        super.insertString(offset, new String(upper,0,length), attr);
    }
}
