import sun.font.TrueTypeFont;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;


/**
 * Created by 350z6_000 on 17.10.2014.
 */
public class ClientConnector extends Thread implements UpdateInterface {
    final ArrayList<Packet> packetArrayList = new ArrayList<>();
    private final int maxTries = 3;
    private String login = "";
    private String pass = "";
    private PrintWriter out;
    private UpdateProxy c;
    private SenderInterface sender;
    private String ip = "";
    private int port = 0;

    public Packet setup(String ip, int port, String login, String password, SenderInterface sender) throws IOException, JAXBException {
        this.ip = ip;
        this.port = port;
        this.login = login;
        this.pass = password;
        this.sender = sender;
        return connect();
    }


    Packet connect() throws IOException, JAXBException {
        Socket kkSocket = new Socket(ip, port);
        out = new PrintWriter(new OutputStreamWriter(kkSocket.getOutputStream()));
        c = new UpdateProxy(this, new BufferedReader(new InputStreamReader(kkSocket.getInputStream())));
        c.start();
        return sendMessage(new Packet(API.LOGIN, login, pass));
    }

    public Packet sendMessage(Packet packet) throws JAXBException, IOException {
        int tries = 0;
        while (tries < maxTries) {
            try {
                System.out.println(packet.xmlGenerate());
                out.println(packet.xmlGenerate());
                out.flush();
                packet = readMessage(packet.func);
                return packet;
            } catch (IOException e) {
                if(packet.func!=API.LOGIN)
                    connect();
                tries++;
            }
        }
        throw new IOException();
    }


    private Packet readMessage(API func) throws IOException {
        Packet pack=new Packet(func);
        if (!c.isAlive())
            throw new IOException();
        try{
        synchronized (packetArrayList) {
                packetArrayList.wait();
                boolean f=true;
                for (int i = 0; i < packetArrayList.size(); i++) {
                    pack=packetArrayList.get(i);
                    if(pack.func == func){
                        packetArrayList.remove(pack);
                        f= false;
                        break;
                    }
                }
            if(f)
                throw new IOException();
        }
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return pack;
    }

    public void update() {
        if (sender != null) {
            sender.update();
        }
    }

    public void close() {
        if (c != null)
            c.interrupt();
    }

}
