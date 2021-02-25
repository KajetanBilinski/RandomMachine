import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerFields implements KeyListener {
    JFormattedTextField field;
    KeyListenerFields(JFormattedTextField field)
    {
        this.field=field;
    }
    public void keyTyped(KeyEvent e) {

    }

    public void keyPressed(KeyEvent e)
    {
        if(field.getText().length()==1&&e.getKeyCode()==KeyEvent.VK_BACK_SPACE)
            field.setValue(null);
        else if(field.getText().isEmpty()&&e.getKeyCode()==KeyEvent.VK_MINUS)
            field.setText("-");
    }

    public void keyReleased(KeyEvent e) {

    }
}
