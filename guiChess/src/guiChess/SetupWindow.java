package guiChess;

public class SetupWindow extends javax.swing.JFrame {
    boolean UserSelected = false;
    /**
     * Creates new form SetupWindow
     */
    public SetupWindow() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        HeaderPanel = new javax.swing.JPanel();
        HeaderText = new javax.swing.JLabel();
        UserSelectPanel = new javax.swing.JPanel();
        UserSelectHeader = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        UserSelectList = new javax.swing.JList<>();
        LoginPanel = new javax.swing.JPanel();
        LoginUsernameLabel = new javax.swing.JLabel();
        LoginWinLabel = new javax.swing.JLabel();
        LoginLossesLabel = new javax.swing.JLabel();
        LoginUsername = new javax.swing.JLabel();
        LoginWins = new javax.swing.JLabel();
        LoginLosses = new javax.swing.JLabel();
        LoginPasswordEntry = new javax.swing.JPasswordField();
        LoginPasswordLabel = new javax.swing.JLabel();
        LoginButton = new javax.swing.JButton();
        LoginErrorLabel = new javax.swing.JLabel();
        CreateUserPanel = new javax.swing.JPanel();
        UserCreateHeader = new javax.swing.JLabel();
        CreatePanel = new javax.swing.JPanel();
        CreateNameLabel = new javax.swing.JLabel();
        CreateName = new javax.swing.JTextField();
        CreatePasswordLabel = new javax.swing.JLabel();
        CreatePassword = new javax.swing.JPasswordField();
        CreateButton = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 337, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chess");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(450, 470));
        setName("SetupFrame"); // NOI18N
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(450, 470));

        HeaderText.setFont(new java.awt.Font("sansserif", 1, 36)); // NOI18N
        HeaderText.setText("GUI Chess");

        javax.swing.GroupLayout HeaderPanelLayout = new javax.swing.GroupLayout(HeaderPanel);
        HeaderPanel.setLayout(HeaderPanelLayout);
        HeaderPanelLayout.setHorizontalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(HeaderText)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        HeaderPanelLayout.setVerticalGroup(
            HeaderPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HeaderPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(HeaderText))
        );

        UserSelectHeader.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        UserSelectHeader.setText("Select an existing user");

        UserSelectList.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Users", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 12))); // NOI18N
        UserSelectList.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        UserSelectList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        UserSelectList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        UserSelectList.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        UserSelectList.setSelectionBackground(new java.awt.Color(80, 24, 70));
        jScrollPane1.setViewportView(UserSelectList);

        LoginPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        LoginPanel.setEnabled(userSelected);

        LoginUsernameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginUsernameLabel.setText("Username: ");

        LoginWinLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginWinLabel.setText("wins:");

        LoginLossesLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginLossesLabel.setText("losses:");

        LoginUsername.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LoginUsername.setText("username_place");

        LoginWins.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LoginWins.setText("wins_place");

        LoginLosses.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        LoginLosses.setText("losses_place");

        LoginPasswordEntry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginPasswordEntryActionPerformed(evt);
            }
        });

        LoginPasswordLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginPasswordLabel.setText("Password:");

        LoginButton.setText("Login");
        LoginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginButtonActionPerformed(evt);
            }
        });

        LoginErrorLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        LoginErrorLabel.setForeground(new java.awt.Color(200, 0, 60));
        LoginErrorLabel.setText("error label");

        javax.swing.GroupLayout LoginPanelLayout = new javax.swing.GroupLayout(LoginPanel);
        LoginPanel.setLayout(LoginPanelLayout);
        LoginPanelLayout.setHorizontalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addComponent(LoginWinLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LoginWins))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addComponent(LoginUsernameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LoginUsername))
                    .addGroup(LoginPanelLayout.createSequentialGroup()
                        .addComponent(LoginLossesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(LoginLosses)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LoginPasswordLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LoginButton)
                    .addComponent(LoginErrorLabel)
                    .addComponent(LoginPasswordEntry, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LoginPanelLayout.setVerticalGroup(
            LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LoginPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginUsernameLabel)
                    .addComponent(LoginUsername))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginWinLabel)
                    .addComponent(LoginWins))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoginLossesLabel)
                    .addComponent(LoginLosses))
                .addGap(18, 18, 18)
                .addComponent(LoginErrorLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(LoginPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(LoginPasswordEntry)
                    .addComponent(LoginPasswordLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(LoginButton)
                .addGap(15, 15, 15))
        );

        UserCreateHeader.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        UserCreateHeader.setText("or create a new one");

        CreatePanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CreateNameLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CreateNameLabel.setText("name:");

        CreatePasswordLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        CreatePasswordLabel.setText("password:");

        CreateButton.setText("create");
        CreateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreateButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CreatePanelLayout = new javax.swing.GroupLayout(CreatePanel);
        CreatePanel.setLayout(CreatePanelLayout);
        CreatePanelLayout.setHorizontalGroup(
            CreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreatePanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(CreateNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreateName, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreatePasswordLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(CreateButton)
                .addContainerGap())
        );
        CreatePanelLayout.setVerticalGroup(
            CreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreatePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CreatePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CreateNameLabel)
                    .addComponent(CreateName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreatePasswordLabel)
                    .addComponent(CreatePassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreateButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout CreateUserPanelLayout = new javax.swing.GroupLayout(CreateUserPanel);
        CreateUserPanel.setLayout(CreateUserPanelLayout);
        CreateUserPanelLayout.setHorizontalGroup(
            CreateUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CreatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CreateUserPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserCreateHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CreateUserPanelLayout.setVerticalGroup(
            CreateUserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CreateUserPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserCreateHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(CreatePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout UserSelectPanelLayout = new javax.swing.GroupLayout(UserSelectPanel);
        UserSelectPanel.setLayout(UserSelectPanelLayout);
        UserSelectPanelLayout.setHorizontalGroup(
            UserSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserSelectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(LoginPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, UserSelectPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(UserSelectHeader)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(CreateUserPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        UserSelectPanelLayout.setVerticalGroup(
            UserSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UserSelectPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(UserSelectHeader)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(UserSelectPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(LoginPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CreateUserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(HeaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(UserSelectPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(HeaderPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(UserSelectPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void LoginPasswordEntryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginPasswordEntryActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_LoginPasswordEntryActionPerformed

    private void CreateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CreateButtonActionPerformed

    private void LoginButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginButtonActionPerformed
        
    }//GEN-LAST:event_LoginButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SetupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SetupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SetupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SetupWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SetupWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CreateButton;
    private javax.swing.JTextField CreateName;
    private javax.swing.JLabel CreateNameLabel;
    private javax.swing.JPanel CreatePanel;
    private javax.swing.JPasswordField CreatePassword;
    private javax.swing.JLabel CreatePasswordLabel;
    private javax.swing.JPanel CreateUserPanel;
    private javax.swing.JPanel HeaderPanel;
    private javax.swing.JLabel HeaderText;
    private javax.swing.JButton LoginButton;
    private javax.swing.JLabel LoginErrorLabel;
    private javax.swing.JLabel LoginLosses;
    private javax.swing.JLabel LoginLossesLabel;
    private javax.swing.JPanel LoginPanel;
    private javax.swing.JPasswordField LoginPasswordEntry;
    private javax.swing.JLabel LoginPasswordLabel;
    private javax.swing.JLabel LoginUsername;
    private javax.swing.JLabel LoginUsernameLabel;
    private javax.swing.JLabel LoginWinLabel;
    private javax.swing.JLabel LoginWins;
    private javax.swing.JLabel UserCreateHeader;
    private javax.swing.JLabel UserSelectHeader;
    private javax.swing.JList<String> UserSelectList;
    private javax.swing.JPanel UserSelectPanel;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}