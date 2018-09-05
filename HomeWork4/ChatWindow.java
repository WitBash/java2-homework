package HomeWork4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ChatWindow extends JFrame {
    public ChatWindow() {
        init();
    }

    public void init() {
        setTitle("WiB Chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(500, 200, 400, 400);

        JTextArea jtaChatWindow = new JTextArea();
        JScrollPane jsp = new JScrollPane(jtaChatWindow);
        add(jsp, BorderLayout.CENTER);

        JPanel jp = new JPanel(new BorderLayout());
        add(jp, BorderLayout.SOUTH);

        JTextField jtfInputMessage = new JTextField();
        jp.add(jtfInputMessage, BorderLayout.CENTER);
        JButton buttonSendMessage = new JButton("Send");
        jp.add(buttonSendMessage, BorderLayout.EAST);

        buttonSendMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaChatWindow.setText(jtfInputMessage.getText());
                jtfInputMessage.setText("");
            }
        });

        jtfInputMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                jtaChatWindow.setText(jtfInputMessage.getText());
                jtfInputMessage.setText("");
            }
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        ChatWindow chatWindow = new ChatWindow();
    }
}
