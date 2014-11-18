package Components;

import javax.swing.*;

/**
 * Created by 350z6233 on 18.11.2014.
 */
public class SelectableListItemComboBox extends SelectableListItem{
    private JComboBox<String> combo=new JComboBox<>();
    public SelectableListItemComboBox(String text,String[] model,boolean showCheckBox) {
        super(text,showCheckBox);
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap(0, Short.MAX_VALUE)
                                .addComponent(combo, GroupLayout.PREFERRED_SIZE, 100, 100)
                                        .addContainerGap()
                        )
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(0, Short.MAX_VALUE)
                                .addComponent(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(0, Short.MAX_VALUE))
        );
        setModel(model);
    }

    public void setModel(String[] model) {
        combo.setModel(new DefaultComboBoxModel<String>(model));
    }

}
