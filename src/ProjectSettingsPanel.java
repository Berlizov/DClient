import Components.CButton;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by 350z6233 on 15.11.2014.
 */
public class ProjectSettingsPanel extends Panel {
    private final JComboBox<String> projectOwnerCombobox = new JComboBox<>();
    String project;
    UsersTypes type;
    String nameOfSelectedPO = null;

    public ProjectSettingsPanel(UsersTypes type, String project, SenderInterface parentSender) {
        super(parentSender);
        this.project = project;
        this.type = type;
        showPanelByType();
    }

    private synchronized void showPanelByType() {
        removeAll();
        try {
            if (type == UsersTypes.ADMIN)
                openAdmin();
            if (type == UsersTypes.PRODUCT_OWNER)
                openProductOwner();
        } catch (Exception e) {
            e.printStackTrace();
            showConnectionError();
        }
    }

    private void changeProject() {
        nameOfSelectedPO = null;
        try {
            if ((Boolean) sendMessage(new Packet(API.CHANGE_PROJECT_PRODUCT_OWNER, project, projectOwnerCombobox.getSelectedItem())).arguments[0]) {
                showSuccess();
            } else {
                showError("Неудаеться создать проект с такими параметрами.");
            }
        } catch (Exception e) {
            showConnectionError();
        }
    }

    private void openAdmin() throws IOException, JAXBException {
        JLabel viewName = new JLabel();

        CButton createButton = new CButton();
        JLabel productOwnerLabel = new JLabel();


        viewName.setFont(new java.awt.Font("Tahoma", 0, 24));
        viewName.setHorizontalAlignment(SwingConstants.CENTER);
        viewName.setText(project);

        createButton.setText("Изменить");
        createButton.setBackgroundColor(Constants.MAIN_COLOR);
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                changeProject();
            }
        });
        setBackground(Constants.FOREGROUND_COLOR);


        productOwnerLabel.setText("Product owner");
        User[] productOwners = sendMessage(new Packet(API.GET_USERS_BY_TYPES, UsersTypes.PRODUCT_OWNER)).getArrayOfArgs(User[].class);
        String productOwner = (String) sendMessage(new Packet(API.GET_PROJECT_PRODUCT_OWNER, project)).arguments[0];
        ArrayList<String> po = new ArrayList<>();
        if (productOwners != null) {
            for (User aProductOwner : productOwners) {
                po.add(aProductOwner.getLogin());
            }
        }
        po.add(0, "-");
        projectOwnerCombobox.setModel(new DefaultComboBoxModel<>(po.toArray(new String[po.size()])));
        if (nameOfSelectedPO != null) {
            projectOwnerCombobox.setSelectedItem(nameOfSelectedPO);
        } else {
            projectOwnerCombobox.setSelectedItem(productOwner);
        }
        projectOwnerCombobox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameOfSelectedPO = (String) projectOwnerCombobox.getSelectedItem();
            }
        });
        JPanel panel = new JPanel();
        panel.setBackground(getBackground());
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(viewName, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(createButton))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(productOwnerLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(projectOwnerCombobox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(viewName, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(productOwnerLabel)
                                        .addComponent(projectOwnerCombobox, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(createButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    private JPanel workersPanel;
    private JPanel tasksPanel;
    private void openProductOwner() {
        JLabel label = new JLabel();
        JLabel workersLabel = new JLabel();
        workersPanel = new JPanel();
        JLabel tasksLabel = new JLabel();
        tasksPanel = new JPanel();

        label.setFont(new java.awt.Font("Tahoma", 0, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(project);


        workersLabel.setText("Работники");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(workersPanel);
        workersPanel.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 99, Short.MAX_VALUE)
        );

        tasksLabel.setText("Задачи");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(tasksPanel);
        tasksPanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, Short.MAX_VALUE)
        );

        JPanel p = new JPanel();
        JScrollPane menuS = new JScrollPane(p);
        menuS.getVerticalScrollBar().setUnitIncrement(16);
        menuS.setBorder(null);
        menuS.setOpaque(false);
        menuS.getViewport().setOpaque(false);
        p.setBackground(Constants.FOREGROUND_COLOR);
        menuS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        this.setLayout(new java.awt.GridLayout());
        this.add(menuS);
        JScrollPane tasksPanelS = new JScrollPane(tasksPanel);
        tasksPanelS.getVerticalScrollBar().setUnitIncrement(16);
        tasksPanelS.setBorder(null);
        tasksPanelS.setOpaque(false);
        tasksPanelS.getViewport().setOpaque(false);
        tasksPanelS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.PAGE_AXIS));
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(p);
        p.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(workersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(workersPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tasksLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(tasksPanelS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(workersLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(workersPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tasksLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tasksPanelS, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        updateTasks();
        updateUsers();
    }
    private void updateUsers(){
        tasksPanel.removeAll();
        try {
            User[] a = sendMessage(new Packet(API.GET_ALL_USERS_AND_TYPES)).getArrayOfArgs(User[].class);
            for (User anA : a) {
                final UserListItem l = new UserListItem(anA.getLogin(), anA.getType());
                l.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                l.setMinimumSize(new Dimension(Integer.MIN_VALUE, 60));
                tasksPanel.add(l);
            }
        } catch (Exception e) {
            showConnectionError();
        }
        updatePanels();
    }
    private void updateTasks(){
        tasksPanel.removeAll();
            try {
                User[] a = sendMessage(new Packet(API.GET_ALL_USERS_AND_TYPES)).getArrayOfArgs(User[].class);
                for (User anA : a) {
                    final UserListItem l = new UserListItem(anA.getLogin(), anA.getType());
                    l.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                    l.setMinimumSize(new Dimension(Integer.MIN_VALUE, 60));
                    tasksPanel.add(l);
                }
            } catch (Exception e) {
                showConnectionError();
            }
            updatePanels();
    }
    private void updatePanels() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public synchronized void update() {
        super.update();
        showPanelByType();
    }
}
