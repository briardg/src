package utbm.p13.tx52.connection;

import java.util.ArrayList;
import java.util.List;
import utbm.p13.tx52.vehicle.AbstractHybridVehicle;

/**
 *
 * @author Gaut
 */
public class Servers {
    
    private List<ClientSocketReceiver> receivers= new ArrayList<>();
    private List<ClientSocketSender> senders= new ArrayList<>();
    
    public Servers(List<AbstractHybridVehicle> vehicles){
        this("127.0.0.1",vehicles);
    }
    
    public Servers(String adr, List<AbstractHybridVehicle> vehicles){
        int i=13000;
        for(AbstractHybridVehicle v : vehicles){
            v.setReceiver(new ClientSocketReceiver(adr,++i));
            v.setSender(new ClientSocketSender(adr,++i));
            receivers.add(v.getReceiver());
            senders.add(v.getSender());
        }
    }
    
    public void start(){
        for(ClientSocketReceiver r :receivers){
            new Thread (r).start();
        }
        for(ClientSocketSender s : senders){
            new Thread (s).start();
        }
    }
}
