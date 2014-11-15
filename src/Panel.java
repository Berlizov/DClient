import Components.ErrorPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 350z6_000 on 16.10.2014.
 */
public class Panel extends JLayeredPane implements SenderInterface {
    private final ErrorPanel errorPanel = new ErrorPanel();
    private SenderInterface parentSender = null;
    private SenderInterface childSender = null;

    public Panel(SenderInterface parentSender) {
        super();
        this.parentSender = parentSender;
        setBackground(Color.WHITE);
        setOpaque(true);
    }


    protected SenderInterface getParentSender() {
        return parentSender;
    }

    public void setParentSender(SenderInterface parentSender) {
        this.parentSender = parentSender;
    }

    public void setChildSender(SenderInterface childSender) {
        this.childSender = childSender;
    }

    protected void showConnectionError() {
        showError("Не удается подключиться к серверу! Проверьте настройки.");
    }

    protected void showError(String error) {
        remove(errorPanel);
        add(errorPanel, JLayeredPane.POPUP_LAYER);
        errorPanel.setX(getWidth());
        errorPanel.setText(error);
        errorPanel.open();
    }

    @Override
    public Packet sendMessage(Packet pack) throws IOException, JAXBException {
        return parentSender.sendMessage(pack);
    }

    @Override
    public synchronized void update() {
        if (childSender != null) {
            childSender.update();
        }
    }
}
