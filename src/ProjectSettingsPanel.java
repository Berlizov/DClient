import Components.CButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 350z6233 on 15.11.2014.
 */
public class ProjectSettingsPanel extends Panel {
    String project;
    public ProjectSettingsPanel(UsersTypes type, String project, SenderInterface parentSender) {
        super(parentSender);
        this.project=project;
        if(type==UsersTypes.ADMIN)
            openAdmin();
        if(type==UsersTypes.PRODUCT_OWNER)
            openProductOwner();
    }
    private void openAdmin(){
        JLabel helloLabel = new JLabel();
        JTextField loginField = new JTextField();
        CButton loginButton = new CButton();
        JTextField passwordField = new JTextField();
        JLabel loginLabel = new JLabel();
        JLabel passLabel = new JLabel();

        helloLabel.setFont(new java.awt.Font("Tahoma", 0, 24));
        helloLabel.setForeground(Color.WHITE);
        helloLabel.setBackground(Constants.MAIN_COLOR);
        helloLabel.setOpaque(true);
        helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        helloLabel.setText(project);
        loginButton.setText("Изменить");
        loginButton.setBackgroundColor(Constants.MAIN_COLOR);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //login();
            }
        });


        loginLabel.setText("Логин");
        passLabel.setText("Пароль");

        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(helloLabel, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(loginButton)
                                                                .addContainerGap())
                                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                        .addContainerGap()
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                                .addComponent(loginLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(passLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                                        .addComponent(loginField, GroupLayout.Alignment.TRAILING)
                                                                                        .addComponent(passwordField, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                                                                        )
                                                                        .addContainerGap()
                                                        )
                                        )
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(loginField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(loginLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(passLabel))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(loginButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }


    private void openProductOwner(){

    }
}
