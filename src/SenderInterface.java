import javax.xml.bind.JAXBException;
import java.io.IOException;

/**
 * Created by 350z6_000 on 16.10.2014.
 */
public interface SenderInterface extends UpdateInterface {
    Packet sendMessage(Packet pack) throws IOException, JAXBException;
}
