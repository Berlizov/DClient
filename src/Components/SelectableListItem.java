package Components;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

/**
 * Created by 350z6233 on 17.11.2014.
 */
public class SelectableListItem extends JLabel implements EventListener {
    private Boolean selected=false;
    private Color selectColor =Color.BLUE;
    private final int padding =20;
    public SelectableListItem(String text) {
        super(text);
        Border paddingBorder = BorderFactory.createEmptyBorder(0,padding,0,0);
        setBorder(paddingBorder);
        p();
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                selected=!selected;
                p();
            }
        });
    }

    public Boolean isSelected() {
        return selected;
    }

    public void isSelected(Boolean selected) {
        this.selected = selected;
        p();
    }

    private void p(){
        if(selected)
            setForeground(Color.WHITE);
        else
            setForeground(Color.BLACK);
    }

    public Color getSelectColor() {
        return selectColor;
    }

    public void setSelectColor(Color selectColor) {
        this.selectColor = selectColor;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(getBackground());
        g.fillRect(0,0,getWidth(),getHeight());
        if(selected)
        {
            g.setColor(selectColor);
            g.fillRect(0,0,getWidth(),getHeight());
        }
        g.setColor(Color.LIGHT_GRAY);
        g.drawLine(padding, getHeight() - 1, getWidth(), getHeight() - 1);
        super.paint(g);
    }
}
