import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.EventListener;

/**
 * Created by 350z6_000 on 02.10.2014.
 */
public class UserListItem extends JComponent implements EventListener {
    private JCheckBox cb;
    private JLabel login;
    private JComboBox<UsersTypes> type;

    public UserListItem(String login, UsersTypes type) {
        super();
        setDef();
        setLogin(login);
        setType(type);
    }

    private void setDef() {
        cb = new JCheckBox();
        cb.setOpaque(false);
        login = new JLabel();
        login.setOpaque(false);
        type = new JComboBox<>();
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE)
                                .addGap(10)
                                .addComponent(login, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 50, 150)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(cb, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(login, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(type, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(0, Short.MAX_VALUE)
                                .addComponent(type, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(0, Short.MAX_VALUE))
        );
        type.setModel(new DefaultComboBoxModel<>(UsersTypes.values()));
    }

    public String getLogin() {
        return this.login.getText();
    }

    public void setLogin(String login) {
        this.login.setText(login);
    }

    public UsersTypes getType() {
        return (UsersTypes) this.type.getSelectedItem();
    }

    public void setType(UsersTypes type) {
        this.type.setSelectedItem(type);
    }

    public boolean isSelected() {
        return cb.isSelected();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(37, getHeight() - 1, getWidth(), getHeight() - 1);
    }

    public void addComboboxActionListener(ActionListener l) {
        type.addActionListener(l);
    }

    public void addCheckboxActionListener(ActionListener l) {
        cb.addActionListener(l);
    }


}
