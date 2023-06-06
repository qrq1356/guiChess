package guiChess.UI;

import guiChess.SessionManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private SessionManager sessionManager;
    private JList<String> userList;
    private JTextField createNameField;
    public LoginPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }
    private void initializeUI () {
        setLayout(new BorderLayout());
        // header
        JLabel headerText = new JLabel("Welcome to Chess");
        headerText.setFont(new Font("Arial", Font.BOLD, 24));
        headerText.setHorizontalAlignment(SwingConstants.CENTER);
        add(headerText, BorderLayout.NORTH);
        // user list
        userList = new JList<>(sessionManager.getUserNames());
        add(new JScrollPane(userList), BorderLayout.WEST);
        // user login panel
        JPanel userLoginPanel = new JPanel();
        add(userLoginPanel, BorderLayout.EAST);
        JButton loginButton = new JButton("Login");
        userLoginPanel.add(loginButton);
        // user create panel
        JPanel userCreatePanel = new JPanel();
        add(userCreatePanel, BorderLayout.SOUTH);

        JButton createButton = new JButton("Create User");
        createButton.addActionListener(new CreateUserButtonListener());
        createNameField = new JTextField(10);

        userCreatePanel.add(createNameField);
        userCreatePanel.add(createButton);
    }
    private class CreateUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionManager.createUser(createNameField.getText());
            System.out.println("button pressed" + createNameField.getText());
        }
    }
}
