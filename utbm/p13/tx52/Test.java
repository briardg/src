package utbm.p13.tx52;

import utbm.p13.tx52.connection.ClientSocketReceiver;
import utbm.p13.tx52.connection.ClientSocketSender;

/**
 *
 * @author Gaut
 */
public class Test {
    
    
      public static void main(String[] args) {
        Thread t1= new Thread (new ClientSocketReceiver(13001));
        Thread t2= new Thread (new ClientSocketReceiver(13003));
        Thread t3= new Thread (new ClientSocketReceiver(13005));
        Thread t4= new Thread (new ClientSocketReceiver(13007));
        
        Thread t5= new Thread (new ClientSocketSender(13002));
        Thread t6= new Thread (new ClientSocketSender(13004));
        Thread t7= new Thread (new ClientSocketSender(13006));
        Thread t8= new Thread (new ClientSocketSender(13008));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        
    }
}
