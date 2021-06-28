
package Remote;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.IOException;

/**
 * @author Yuzhe You (No.1159774)
 */

public interface RMIServerInterface extends Remote {
	void register(RMIClientInterface client) throws RemoteException;
    void synClientList() throws RemoteException;
    byte[] forwardRealTimePic() throws IOException;
    void upload(RMIDataInterface msg) throws RemoteException;
    void forwardMsg(String msg) throws RemoteException;

    void DeleteMyself(String name) throws RemoteException;

    void forwardPicOpened(byte[] rawImage) throws IOException;
	void removeClient(String selectName) throws IOException;
	void DeleteTogether() throws IOException;
}
