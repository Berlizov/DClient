import Components.SelectableListItemComboBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 350z6233 on 19.11.2014.
 */
public class ProjectTasksPanel extends Panel {
    private final String project;
    private final UsersTypes type;
    private final JPanel tasksPanel;
    public ProjectTasksPanel(UsersTypes type, String project, TaskPanelDelegate parentSender) {
        super(parentSender);
        this.project = project;
        this.type = type;

        tasksPanel = new JPanel();
        JScrollPane menuS = new JScrollPane(tasksPanel);
        menuS.getVerticalScrollBar().setUnitIncrement(16);
        menuS.setBorder(null);
        menuS.setOpaque(false);
        menuS.getViewport().setOpaque(false);
        tasksPanel.setBackground(Constants.FOREGROUND_COLOR);
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.PAGE_AXIS));
        menuS.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        updateTasks();
        setLayout(new java.awt.GridLayout());
        add(menuS);
    }
    private void updateTasks() {
        tasksPanel.removeAll();
        try {
            Task[] tasks = sendMessage(new Packet(API.GET_PROJECT_TASKS, project)).getArrayOfArgs(Task[].class);
            if (tasks != null) {
                for (Task task : tasks) {
                    final SelectableListItemComboBox l = new SelectableListItemComboBox(task.getName(), PokerCardDeck.getModel(),task.getComplexity().toString(), false);
                    l.setMaximumSize(new Dimension(Integer.MAX_VALUE, 60));
                    l.setMinimumSize(new Dimension(Integer.MIN_VALUE, 60));
                    l.setSelectColor(Constants.MAIN_COLOR2);
                    l.setBackground(Constants.FOREGROUND_COLOR);
                    l.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            openTaskPanel(l.getText());
                        }
                    });
                    l.addComboboxActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                          //  setComplexityTask(new Task(l.getText(), project, PokerCardDeck.valueOf(l.getSelectedItem()), null));
                        }
                    });
                    tasksPanel.add(l);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            showConnectionError();
        }
        this.revalidate();
        this.repaint();
    }
    private void openTaskPanel(String taskName){
        ((TaskPanelDelegate)getParentSender()).openTaskPanel(taskName);
    }

    @Override
    public synchronized void update() {
        super.update();
        updateTasks();
    }
}
