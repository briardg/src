
package utbm.p13.tx52.connection;

import java.net.Socket;

public abstract class AbstractClientSocket implements Runnable{
    protected Socket socket;
    protected String  adr;
    protected int port;
    protected double torque=0.0;

    public synchronized double getTorque() {
        return torque;
    }

    public synchronized void setTorque(double torque) {
        this.torque = torque;
    }
    
}
