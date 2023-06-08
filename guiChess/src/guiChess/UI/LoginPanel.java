package guiChess.UI;

import guiChess.SessionManager;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private static final Color BACKGROUND_COLOR = new Color(240, 240, 240),
            FOREGROUND_COLOR = new Color(0, 43, 54),
            BACKGROUND_COLOR_2 = new Color(230, 230, 230),
            PRIMARY_COLOR = new Color(108, 113, 196),
            ERROR_COLOR = new Color(220, 50, 47);

    private static final Font HEADER_FONT = new Font("Arial", Font.BOLD, 24),
            LABEL_FONT = new Font("Arial", Font.PLAIN, 16),
            BUTTON_FONT = new Font("Arial", Font.BOLD, 16);
    private static final Border SOFT_BEVEL_BORDER = BorderFactory.createSoftBevelBorder(0);
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
        GridBagConstraints c = new GridBagConstraints(1, 0, 1, 1, 0.1, 0.1,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                new Insets(5, 0, 5, 0), 0, 0);
        // colours
        setBackground(BACKGROUND_COLOR);
        setForeground(FOREGROUND_COLOR);

        // header panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBorder(SOFT_BEVEL_BORDER);
        add(headerPanel, c);
        constructHeaderPanel(headerPanel);

        // User Login Panel
        JPanel selectPanel = new JPanel();
        selectPanel.setBorder(SOFT_BEVEL_BORDER);
        c.weighty = 0.5;
        c.gridy = 1;
        add(selectPanel, c);
        constructSelectPanel(selectPanel);

        // User Create Panel
        JPanel createPanel = new JPanel();
        createPanel.setBorder(SOFT_BEVEL_BORDER);
        c.weighty = 0.4;
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
        leftSpacePanel.setBackground(BACKGROUND_COLOR);
        add(leftSpacePanel, c);
        JPanel rightSpacePanel = new JPanel();
        c.gridx = 2;
        rightSpacePanel.setBackground(BACKGROUND_COLOR);
        add(rightSpacePanel, c);
    }

    private void constructHeaderPanel(JPanel owner) {
        owner.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        owner.setBackground(BACKGROUND_COLOR);
        JLabel headerLabel = new JLabel("Welcome to Chess!");
        headerLabel.setFont(HEADER_FONT);
        headerLabel.setForeground(FOREGROUND_COLOR);
        owner.add(headerLabel, c);
    }

    private void constructCreatePanel(JPanel owner) {
        owner.setBackground(BACKGROUND_COLOR);
        owner.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(0, 4, 0, 4);

        // header label
        JLabel createLabel = new JLabel("Create a user");
        createLabel.setFont(HEADER_FONT);
        createLabel.setForeground(FOREGROUND_COLOR);
        c.gridx = 1;
        owner.add(createLabel, c);

        // Username label
        JLabel createNameLabel = new JLabel("Username:");
        createNameLabel.setFont(LABEL_FONT);
        createNameLabel.setForeground(FOREGROUND_COLOR);
        c.gridx = 0;
        c.gridy = 2;
        owner.add(createNameLabel, c);

        // Username text field
        createNameField = new JTextField();
        createNameField.setBackground(BACKGROUND_COLOR_2);
        createNameField.setForeground(FOREGROUND_COLOR);
        createNameField.setPreferredSize(new Dimension(200, 24));
        c.gridx = 1;
        owner.add(createNameField, c);

        // Create button
        JButton createButton = new JButton("Create");
        createButton.setFont(BUTTON_FONT);
        createButton.setBackground(PRIMARY_COLOR);
        createButton.setForeground(BACKGROUND_COLOR);
        c.gridx = 2;
        createButton.addActionListener(new CreateUserButtonListener());
        owner.add(createButton, c);

        // Error label
        createErrorLabel = new JLabel();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        createErrorLabel.setForeground(ERROR_COLOR);
        owner.add(createErrorLabel, c);
    }

    private void constructSelectPanel(JPanel owner) {
        owner.setLayout(new GridBagLayout());
        owner.setBackground(BACKGROUND_COLOR);
        GridBagConstraints c = new GridBagConstraints();

        // header label
        JLabel selectLabel = new JLabel("Select a user");
        selectLabel.setFont(HEADER_FONT);
        selectLabel.setForeground(FOREGROUND_COLOR);
        c.gridy = 0;
        owner.add(selectLabel, c);

        // User list
        userList = new JList<>(sessionManager.getUserNames());
        userList.setFont(LABEL_FONT);
        userList.setSelectionBackground(PRIMARY_COLOR);
        userList.setSelectionForeground(BACKGROUND_COLOR);
        userList.setBackground(BACKGROUND_COLOR_2);
        userList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        userList.setLayoutOrientation(JList.VERTICAL);
        userList.setVisibleRowCount(-1);

        JScrollPane listScroll = new JScrollPane(userList);
        listScroll.setPreferredSize(new Dimension(200, 200));
        c.gridy = 1;
        owner.add(listScroll, c);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setFont(BUTTON_FONT);
        loginButton.setBackground(PRIMARY_COLOR);
        loginButton.setForeground(BACKGROUND_COLOR);

        c.gridy = 2;
        loginButton.addActionListener(new LoginButtonListener());
        owner.add(loginButton, c);
    }

    private class CreateUserButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int i = sessionManager.createUser(createNameField.getText());
            switch (i) {
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