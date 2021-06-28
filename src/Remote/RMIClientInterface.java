
package Remote;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Yuzhe You (No.1159774)
 */

public interface RMIClientInterface extends Remote {
    void clearBoard() throws RemoteException;

    void kickOut(String name) throws RemoteException;
    byte[] forwardPic() throws IOException;
    void drawOpenedImage(byte[] rawImage) throws IOException;
	void notifyKicked() throws IOException;
	boolean isKickedOut() throws RemoteException;
    boolean isAdmin() throws RemoteException;
    double response() throws RemoteException;
    void addUser(String name) throws RemoteException;

    void drawBoard(RMIServerInterface server) throws RemoteException;

    void modifySymbol(String name) throws RemoteException;

    void forwardMsg(String message) throws RemoteException;
    boolean synchronize(RMIDataInterface msg) throws RemoteException;
    void assignManager() throws RemoteException;
    void shutDownAllClients() throws IOException;
    String getClientName() throws RemoteException;
    void setClientName(String name) throws RemoteException;
    void extra() throws RemoteException;
}