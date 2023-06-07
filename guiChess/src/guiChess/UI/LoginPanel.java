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
    private JLabel createErrorLabel;
    public LoginPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }
    private void initializeUI() {
        setLayout(new GridLayout(3, 1, 0, 20));
        // Header
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        JLabel headerLabel = new JLabel("Welcome to Chess");
        headerPanel.add(headerLabel);
        add(headerPanel);

        // Login
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(1,2,0,0));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // user list
        userList = new JList<>(sessionManager.getUserNames());
        loginPanel.add(new JScrollPane(userList));
        // login button
        JButton LoginButton = new JButton("Login");
        loginPanel.add(LoginButton);

        add(loginPanel);
        // create
        JPanel createPanel = new JPanel();
        createPanel.setLayout(new GridLayout(1,1,0,0));
        createPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        // create name field
        createNameField = new JTextField(10);
        createPanel.add(createNameField);

        // create button
        JButton createButton = new JButton("Create User");
        createButton.addActionListener(new CreateUserButtonListener());
        createPanel.add(createButton);
        add(createPanel);
        // create error label
        createErrorLabel = new JLabel();
        createPanel.add(createErrorLabel);
    }
    private class CreateUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = sessionManager.createUser(createNameField.getText());
            switch(i) {
                case 0:
                    sessionManager.loadUser(createNameField.getText());
                    sessionManager.toUser();
                    break;
                case 1:
                    createErrorLabel.setText("Invalid username. [a-zA-Z0-9] | [<50]");
                    break;
                case 2:
                    createErrorLabel.setText("Username already exists.");
                    break;
                case 3:
                    createErrorLabel.setText("Unknown error.");
                    break;
            }
        }
    }
    private class LoginButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            sessionManager.loadUser(userList.getSelectedValue());
            sessionManager.toUser();
        }
    }
}
