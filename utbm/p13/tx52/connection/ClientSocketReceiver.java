package utbm.p13.tx52.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Gaut
 */
public class ClientSocketReceiver extends AbstractClientSocket{
    
    public ClientSocketReceiver(int port) {
        this.port=port;
    }
    
    @Override
    public void run() {
        while(true){
            try{
                socket = new Socket("127.0.0.1", port);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String s = in.readLine();
                if(s!=null){
                   System.out.println("port:"+port+" torque:"+s);
                }
                socket.close();

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
