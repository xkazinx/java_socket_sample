package Visual;

import com.company.ClientTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class GUI extends JFrame {
    private JTextField _textField = new JTextField(40);
    private JTextArea _messageArea = new JTextArea(8, 40);

    private PrintWriter _msg_destination;

    public GUI(PrintWriter msg_destination) throws IOException {
        super("myChat");
        _msg_destination = msg_destination;
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _textField.setEditable(false);
        _messageArea.setEditable(false);
        this.getContentPane().add(_textField, "Center");
        _textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                _msg_destination.println(_textField.getText());
                _textField.setText("");
            }
        });
        this.getContentPane().add(new JScrollPane(_messageArea), "North");
        this.pack();
        this.setVisible(true);
    }

    public String GetName() {
        return JOptionPane.showInputDialog(
                this,
                "Ingrese su nick:",
                "Registro",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void UserValidated() {
        _textField.setEditable(true);
    }

    public void AddNewMsg(String msg) {
        _messageArea.append(msg);
    }
}
