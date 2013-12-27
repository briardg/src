package utbm.p13.tx52.connection;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Gaut
 */
public class ClientSocketSender extends AbstractClientSocket{

    public ClientSocketSender(String adr, int port) {
        this.adr=adr;
        this.port=port;
    }
    public ClientSocketSender(int port) {
        this("127.0.0.1",port);
    }
    
    @Override
    public void run() {
        while(true){
            try{
                socket = new Socket(this.adr, port);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), false);
                if(this.torque==Double.NaN){
                    System.out.println("$$$$$$$$$$$$$$$$$NAN");
                    this.torque=0.0;
                }
                    out.print("@"+this.torque+"#");
                out.flush();
                socket.close();
                System.out.println(Double.toString(this.torque*100)+"sender("+this.port+"): @"+this.torque+"#");

            } catch (UnknownHostException e) {
                System.out.println(e);
            } catch  (IOException e) {}
            finally{
                try{
                    socket.close();
                }
                catch(Exception e){
                    System.out.println("finally e:"+e);
                }  
            }
        }
    }    
}
