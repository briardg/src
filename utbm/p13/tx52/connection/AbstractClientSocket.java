
package utbm.p13.tx52.connection;

import java.net.Socket;
import java.net.SocketAddress;

public abstract class AbstractClientSocket implements Runnable{
    protected Socket socket;
    protected SocketAddress  sa;
    protected int port;
}
