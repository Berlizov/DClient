import Components.CButton;
import Components.SelectableListItem;
import Components.Selector;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by 350z6233 on 19.11.2014.
 */
interface TaskPanelDelegate extends SenderInterface {
    public void openTaskPanel(String task);
}

public class TaskPanel extends Panel {
    String project;
    String task;
    Selector workersPanel;

    public TaskPanel(String project, String task, TaskPanelDelegate parentSender) {
        super(parentSender);
        this.project = project;
        this.task = task;
        JLabel label = new JLabel();
        label.setFont(new java.awt.Font("Tahoma", 0, 24));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText(project + "." + task);
        JLabel workersLabel = new JLabel();
        workersPanel = new Selector();
        CButton saveButton = new CButton();
        JLabel tasksLabel = new JLabel();
        JPanel tasksPanel = new JPanel();
        JTextField taskField = new JTextField();
        CButton addTaskButton = new CButton();

        Border border = BorderFactory.createLineBorder(Constants.MINOR_TEXT_COLOR);
        workersLabel.setText("Работники");
        workersPanel.setSelectColor(Constants.MAIN_COLOR);
        workersPanel.setBackground(Constants.FOREGROUND_COLOR);
        workersPanel.setBordersOfView(border);
        saveButton.setText("Сохранить");
        saveButton.setBackgroundColor(Constants.MAIN_COLOR);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        tasksLabel.setText("Подзадачи");
        tasksPanel.setBorder(border);
        tasksPanel.setBackground(Constants.FOREGROUND_COLOR);


        JPanel p = new JPanel();
        JScrollPane menuS = new JScrollPane(p);
        menuS.getVerticalScrollBar().setUnitIncrement(16);
        menuS.setBorder(null);
        menuS.setOpaque(false);
        menuS.getViewport().setOpaque(false);
        p.setBackground(Constants.FOREGROUND_COLOR);
        menuS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);


        JScrollPane tasksPanelS = new JScrollPane(tasksPanel);
        tasksPanelS.getVerticalScrollBar().setUnitIncrement(16);
        tasksPanelS.setBorder(null);
        tasksPanelS.setOpaque(false);
        tasksPanelS.getViewport().setOpaque(false);
        tasksPanelS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.PAGE_AXIS));

        addTaskButton.setText("Добавить");
        addTaskButton.setBackgroundColor(Constants.MAIN_COLOR);
        addTaskButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(menuS, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout = new GroupLayout(p);
        p.setLayout(layout);


        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(tasksLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(label, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(taskField, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(addTaskButton))
                                        .addComponent(tasksPanelS, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(workersLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(workersPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(saveButton)))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tasksLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                        .addComponent(taskField, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                                        .addComponent(addTaskButton, GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(tasksPanelS, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(workersLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(workersPanel, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(saveButton, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap()));
        updateTaskUsers();
    }
    private void updateTaskUsers(){
        try {
            User[] a = sendMessage(new Packet(API.GET_PROJECT_USERS_BY_TYPE, project,UsersTypes.DEVELOPER)).getArrayOfArgs(User[].class);
            ArrayList<SelectableListItem> l = new ArrayList<>();
            if (a != null) {
                for (User anA : a) {
                    SelectableListItem sli = new SelectableListItem(anA.getLogin(), false);
                    sli.setBackground(Constants.FOREGROUND_COLOR);
                    l.add(sli);
                }
            }

            workersPanel.setSelectableElements(l);
            l = new ArrayList<>();
            a=null;
            if (a != null) {
                for (User anA : a) {
                    SelectableListItem sli = new SelectableListItem(anA.getLogin(), false);
                    sli.setBackground(Constants.FOREGROUND_COLOR);
                    l.add(sli);
                }
            }
            workersPanel.setSelectedElements(l);
        } catch (Exception e) {
            e.printStackTrace();
            showConnectionError();
        }
    }
}
