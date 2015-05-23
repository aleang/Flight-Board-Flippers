package flippers;

import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;

public class CustomField extends JTextField {
 
     public CustomField(int size) {
         super(size);
     }
 
     @Override
	protected Document createDefaultModel() {
         return new JNumberDocument(this);
     }
 
     static class JNumberDocument extends PlainDocument {

    	CustomField field;
        private JNumberDocument(CustomField field) {
            super();
            this.field = field;
        }
 
        @Override
		public void insertString(int offs, String str, AttributeSet a)
             throws BadLocationException {
 
             if (str == null || field.getText().length() >= flippers.NamePicker.NUMBER_OF_FLIPPERS) {
                 return;
             }
             str = str.toUpperCase();
             String allowedInput = new String();
             for (int i = 0; i < str.length(); i++) {
                 if (MyApplication.ALLOWED_CHARS.contains(str.substring(i, i+1))) {
                     allowedInput += str.charAt(i);
                 }
             }
             super.insertString(offs, allowedInput, a);
         }
     }
 }