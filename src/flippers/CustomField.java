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
 
     protected Document createDefaultModel() {
         return new JNumberDocument(this);
     }
 
     static class JNumberDocument extends PlainDocument {

    	CustomField field;
        private JNumberDocument(CustomField field) {
            super();
            this.field = field;
        }
 
         public void insertString(int offs, String str, AttributeSet a)
             throws BadLocationException {
 
             if (str == null || field.getText().length() >= flippers.NamePicker.NUMBER_OF_FLIPPERS) {
                 return;
             }
             char[] chars = str.toUpperCase().toCharArray();
             String allowedInput = new String();
             for (int i = 0; i < chars.length; i++) {
                 if (Character.isLetter(chars[i]) || chars[i] == ' ')
                     allowedInput += chars[i];
             }
             super.insertString(offs, allowedInput, a);
         }
     }
 }