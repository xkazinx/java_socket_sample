package Visual;

import com.company.ClientTask;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by GaBPC on 22/07/2016.
 */
public class GUI extends JFrame {
    private JTextField _textField = new JTextField(40);
    private JTextArea _messageArea = new JTextArea(8, 40);

    private ArrayList<String> _mensajes = new ArrayList<>();



    public GUI() throws IOException {
        super("myChat");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _textField.setEditable(false);
        _messageArea.setEditable(false);
        this.getContentPane().add(_textField, "Center");
        _textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ResetInput();
            }
        });
        this.getContentPane().add(new JScrollPane(_messageArea), "North");
        this.pack();
        this.setVisible(true);
    }

    private synchronized void ResetInput() {
        _mensajes.add(_textField.getText());
        _textField.setText("");
        notifyAll();
    }

    public synchronized String GetNewMsg() {
        String aux = null;
        try {
            while (_mensajes.size() <= 0)
                wait();
            aux = _mensajes.remove(0);
            System.out.println("M: " + aux);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aux;
    }

    public String GetName() {
        return JOptionPane.showInputDialog(
                this,
                "Choose a screen name:",
                "Screen name selection",
                JOptionPane.PLAIN_MESSAGE);
    }

    public void UserValidated() {
        _textField.setEditable(true);
    }

    public void AddNewMsg(String msg) {
        _messageArea.append(msg);
    }
}
