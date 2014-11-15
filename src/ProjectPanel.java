import Components.CButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by 350z6_000 on 16.09.2014.
 */
public class ProjectPanel extends LeftRightPanel {
    private final String projectName;
    private final ArrayList<CButton> leftButtons = new ArrayList<>();
    private final UsersTypes type;

    public ProjectPanel(UsersTypes type, String projectName, SenderInterface parentSender) {
        super(parentSender, 60, Constants.MAIN_COLOR2);
        this.projectName = projectName;
        this.type=type;

        leftPanel.setLayout(new javax.swing.BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        if ((type == UsersTypes.PRODUCT_OWNER)||(type == UsersTypes.ADMIN)) {
            addLeftButton(new ImageIcon("img/Settings.png"), new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            selectButton((CButton) e.getSource());
                            openProjectSettings();
                        }
                    }
            ).doClick();
        }

        addLeftButton(new ImageIcon("img/Tasks.png"), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectButton((CButton) e.getSource());
                    }
                }
        );
        addLeftButton(new ImageIcon("img/Star.png"), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectButton((CButton) e.getSource());
                    }
                }
        );
        addLeftButton(new ImageIcon("img/Stats.png"), new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        selectButton((CButton) e.getSource());
                    }
                }
        );

    }

    private CButton addLeftButton(ImageIcon icon, ActionListener al) {
        CButton button = new CButton();
        button.setIcon(icon);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
        button.addActionListener(al);
        leftPanel.add(button);
        leftButtons.add(button);
        return button;
    }

    private void selectButton(CButton b) {
        for (CButton leftButton : leftButtons) {
            leftButton.setSelected(false);
        }
        b.setSelected(true);
    }

    private void openProjectSettings()
    {
        openPanel(new ProjectSettingsPanel(type,projectName,this));
    }
    /*private void settings() {

        JLabel projectNameLabel = new JLabel();
        CButton jButton1 = new CButton();
        JTextField jTextField1 = new JTextField();
        JLabel jLabel2 = new JLabel();
        JPanel jPanel1 = new JPanel();
        JLabel jLabel3 = new JLabel();
        JPanel jPanel2 = new JPanel();

        projectNameLabel.setText("Название проекта");

        jButton1.setText("jButton1");

        jTextField1.setText(projectName);

        jLabel2.setText("Работники");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 99, Short.MAX_VALUE)
        );

        jLabel3.setText("Задачи");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(projectNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                )
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(projectNameLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(211, Short.MAX_VALUE))
        );
    }*/
}
