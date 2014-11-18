import Components.NotifyPanel;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.io.IOException;

/**
 * Created by 350z6_000 on 16.10.2014.
 */
public class Panel extends JLayeredPane implements SenderInterface {
    private final NotifyPanel notifyPanel = new NotifyPanel();
    private SenderInterface parentSender = null;
    private SenderInterface childSender = null;
    private JComponent panelUP ;

    public Panel(SenderInterface parentSender) {
        super();
        panelUP=this;
        this.parentSender = parentSender;
        setBackground(Color.WHITE);
        setOpaque(true);
    }

    public void setPanelUP(JComponent panelUP) {
        this.panelUP = panelUP;
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

    protected void showSuccess() {
        showNotify("Успешное выполнение операции",Constants.SUCCESS_COLOR);
    }
    protected void showError(String error) {
        showNotify(error,Constants.ERROR_COLOR);
    }
    protected void showNotify(String text,Color color) {
        remove(notifyPanel);
        panelUP.add(notifyPanel, JLayeredPane.POPUP_LAYER);
        notifyPanel.setX(getWidth());
        notifyPanel.setText(text);
        notifyPanel.setBackground(color);
        notifyPanel.open();
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
