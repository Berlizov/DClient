import Components.CButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by 350z6_000 on 18.10.2014.
 */
public class MainMenuPanel extends LeftRightPanel implements ProjectCreateDelegate, SettingsDelegate {

    private final int h = 60;
    private final UsersTypes type;
    private final JPanel menu;
    private final CButton settingsButton;
    private final CButton usersButton;
    private final ArrayList<CButton> buttonsList;
    private String selectedProject = null;
    private Boolean openProject = false;
    private final PanelSwitcher panelSwitcher;

    public MainMenuPanel(String login, UsersTypes type, PanelSwitcher parentSender) {
        super(parentSender, 200, Constants.MAIN_COLOR);
        this.panelSwitcher=parentSender;
        this.type = type;
        buttonsList = new ArrayList<>();

        GroupLayout layout = new GroupLayout(leftPanel);
        leftPanel.setLayout(layout);
        settingsButton = new CButton();
        usersButton = new CButton();
        JLabel userNameLabel = new JLabel();

        menu = new JPanel();
        menu.setOpaque(false);
        JScrollPane menuS = new JScrollPane(menu);
        menuS.setBorder(null);
        menuS.setOpaque(false);
        menuS.getViewport().setOpaque(false);
        menuS.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        settingsButton.setIcon(new ImageIcon("img/Settings.png"));
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSettingsPanel();
                selectButton((CButton) e.getSource());
            }
        });

        usersButton.setText("Пользователи");
        usersButton.setVisible(type == UsersTypes.ADMIN);
        usersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openUsersPanel();
                selectButton((CButton) e.getSource());
            }
        });

        userNameLabel.setText(login);
        userNameLabel.setIcon(new ImageIcon("img/userIcon.png"));
        userNameLabel.setForeground(Constants.FOREGROUND_COLOR);
        userNameLabel.setIconTextGap(9);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(settingsButton, GroupLayout.PREFERRED_SIZE, h, Short.MAX_VALUE)
                                .addComponent(usersButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(menuS)
                        .addComponent(userNameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(userNameLabel, GroupLayout.PREFERRED_SIZE, h, GroupLayout.PREFERRED_SIZE)
                                .addComponent(menuS, GroupLayout.DEFAULT_SIZE, h, Short.MAX_VALUE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(usersButton, GroupLayout.DEFAULT_SIZE, h, Short.MAX_VALUE)
                                        .addComponent(settingsButton, GroupLayout.DEFAULT_SIZE, h, Short.MAX_VALUE)))
        );
        /**********/
        menu.setLayout(new BoxLayout(menu, BoxLayout.PAGE_AXIS));
        updateProjects();

    }

    private synchronized void updateProjects() {
            menu.removeAll();
            buttonsList.clear();
            try {
                String[] project = sendMessage(new Packet(API.GET_PROJECTS)).getArrayOfArgs(String[].class);
                if (project != null)
                    for (String aProject : project) {
                        addProject(aProject);
                    }
            } catch (Exception e) {
                e.printStackTrace();
                showConnectionError();
            }
            if (type == UsersTypes.ADMIN) {
                CButton addButton = new CButton();
                addButton.setText("Добавить проект");
                addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                        h));
                addButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        openProjectCreatePanel();
                        selectButton((CButton) e.getSource());
                    }
                });
                buttonsList.add(addButton);
                buttonsList.add(settingsButton);
                buttonsList.add(usersButton);
                menu.add(addButton);
            }
            if (selectedProject != null) {
                for (CButton button : buttonsList) {
                    if (button.getText().equals(selectedProject)) {
                        if (openProject)
                            button.doClick();
                        else
                            selectButton(button);
                        openProject = false;
                    }
                }
            }
            leftPanel.revalidate();
    }

    private void addProject(String name) {
        final CButton addButton = new CButton();
        addButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
        addButton.setText(name);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openProjectPanel(((JButton) e.getSource()).getText());
                selectButton((CButton) e.getSource());
            }
        });
        menu.add(addButton);
        buttonsList.add(addButton);
    }

    private void selectButton(CButton b) {
        for (CButton aButtonsList : buttonsList) {
            aButtonsList.setSelected(false);
        }
        b.setSelected(true);
    }

    @Override
    public void openPanel(Panel p) {
        super.openPanel(p);
        selectedProject=null;
    }

    private void openProjectPanel(String projectName) {
        openPanel(new ProjectPanel(type, projectName, this));
        selectedProject = projectName;
    }

    private void openUsersPanel() {
        UsersPanel up = new UsersPanel(this);
        openPanel(up);
        up.openUsersMain();
    }

    private void openSettingsPanel() {
        openPanel(new SettingsPanel(this));
    }

    private void openProjectCreatePanel() {
        openPanel(new ProjectCreatePanel(this));
    }

    @Override
    public void projectCreated(String projectName) {
        selectedProject = projectName;
        openProject = true;

    }

    @Override
    public synchronized void update() {
        super.update();
        updateProjects();
    }

    @Override
    public void exit() {
        panelSwitcher.openLoginPanel();
    }
}


