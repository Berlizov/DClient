import java.io.BufferedReader;

/**
 * Created by 350z6_000 on 08.11.2014.
 */
public class UpdateProxy extends Thread {
    final ClientConnector cc;
    private final BufferedReader in;

    public UpdateProxy(ClientConnector cc, BufferedReader in) {
        this.cc = cc;
        this.in = in;

    }

    @Override
    public void run() {
        Packet pack;
        super.run();
        try {
            do {
                pack = Packet.xmlParse(in.readLine());
                if (pack.func == API.UPDATE) {
                    System.err.println("UPDATE");
                    if (cc != null) {
                        UpdateThread t = new UpdateThread(cc);
                        t.start();
                    }
                } else {
                    if (cc != null) {
                        cc.packetArrayList.add(pack);
                    }
                }
            } while (true);
        } catch (Exception e) {
            return;
        }
    }

}
