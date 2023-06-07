package guiChess.UI;

import guiChess.SessionManager;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private final SessionManager sessionManager;
    // dynamic components need higher scope.
    private JList<String> userList;
    private JTextField createNameField;
    private JLabel createErrorLabel;

    public LoginPanel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;
        initializeUI();
    }
    private void initializeUI() {
        // layout
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints(1,0,1,1,1,0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(10,0,10,0), 0, 0);
        Border softBevel = BorderFactory.createSoftBevelBorder(0);
        // header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(softBevel);
        add(headerPanel, c);
        constructHeaderPanel(headerPanel);
        // User Login Panel
        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(softBevel);
        c.weighty = 0.6;
        c.gridy = 1;
        add(selectPanel, c);
        constructSelectPanel(selectPanel);
        // User Create Panel
        JPanel createPanel = new JPanel();
        createPanel.setBorder(softBevel);
        c.weighty = 0.3;
        c.gridy = 2;
        add(createPanel, c);
        constructCreatePanel(createPanel);
        // Empty space panels on the left and right
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        c.gridy = 0;
        c.gridheight = 3;
        JPanel leftSpacePanel = new JPanel();
        c.gridx = 0;
        add(leftSpacePanel, c);
        JPanel rightSpacePanel = new JPanel();
        c.gridx = 2;
        add(rightSpacePanel, c);
    }
    private void constructHeaderPanel(JPanel owner) {
        JLabel headerLabel = new JLabel("Welcome to Chess!");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        owner.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        owner.add(headerLabel, c);
    }
    private void constructCreatePanel(JPanel owner) {
        owner.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // Username label
        JLabel createNameLabel = new JLabel("Username:");
        c.gridx = 0;
        c.gridy = 1;
        owner.add(createNameLabel, c);
        // Username text field
        createNameField = new JTextField();
        createNameField.setPreferredSize(new Dimension(200, 30));
        c.gridx = 1;
        owner.add(createNameField, c);
        // Create button
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new CreateUserButtonListener());
        c.gridx = 2;
        owner.add(createButton, c);
        // Error label
        createErrorLabel = new JLabel();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        owner.add(createErrorLabel, c);
    }
    private void constructSelectPanel(JPanel owner) {
        owner.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // User list
        userList = new JList<>(sessionManager.getUserNames());
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(userList);
        listScroller.setPreferredSize(new Dimension(200, 200));
        c.gridx = 0;
        c.gridy = 0;
        owner.add(listScroller, c);
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginButtonListener());
        c.gridy = 1;
        owner.add(loginButton, c);
    }

    private class CreateUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = sessionManager.createUser(createNameField.getText());
            switch(i) {
                case 0:
                    System.out.println(createNameField.getText());
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
            System.out.println(userList.getSelectedValue());
            sessionManager.loadUser(userList.getSelectedValue());
            sessionManager.toUser();
        }
    }
}
