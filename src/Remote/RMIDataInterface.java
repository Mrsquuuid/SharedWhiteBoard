
package Remote;

import java.awt.Color;
import java.awt.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Yuzhe You (No.1159774)
 */

public interface RMIDataInterface extends Remote {
	String getPaintStatus() throws RemoteException;
	String getUserName() throws RemoteException;
	String getSchema() throws RemoteException;

	String text() throws RemoteException;
	Color color() throws RemoteException;
	Point point() throws RemoteException;
}
