package Visual;

import com.company.Control;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import javax.swing.text.StringContent;
import java.awt.event.*;
import java.io.IOException;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class GUI extends JFrame {
    private JTextField _textField = new JTextField(40);
    private JTextArea _messageArea = new JTextArea(8, 40);

    private Control _send_msg_control;

    public GUI(Control control) throws IOException {
        super("myChat");

        /*AutoScroll*/
        DefaultCaret caret = (DefaultCaret) _messageArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        _send_msg_control = control;

        this.setSize(100, 100);
        _textField.setEditable(false);
        _messageArea.setEditable(false);
        this.getContentPane().add(_textField, "Center");
        _textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _send_msg_control.SendMsg(_textField.getText());
                _textField.setText("");
            }
        });
        this.getContentPane().add(new JScrollPane(_messageArea), "North");


        WindowListener exitListener = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                _send_msg_control.EndSession();
            }
        };
        this.addWindowListener(exitListener);

        this.pack();
        this.setVisible(true);
    }

    public String GetName() {
        String aux = JOptionPane.showInputDialog(
                this,
                "Ingrese su nick:",
                "Registro",
                JOptionPane.PLAIN_MESSAGE);
        if (aux == null)
            aux = "NoName";
        return aux;
    }

    public void UserValidated() {
        _textField.setEditable(true);
    }

    public void AddNewMsg(String msg) {
        _messageArea.append(msg);
    }
}
