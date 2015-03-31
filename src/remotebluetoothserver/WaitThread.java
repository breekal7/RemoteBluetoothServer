
package remotebluetoothserver;

/**
 *
 * @author saifur
 */
import java.io.IOException;
import javax.microedition.io.*;
import javax.bluetooth.*;
 
 
public class WaitThread implements Runnable {
 
 public WaitThread(){}
 
 @Override
 public void run() {  
  waitForConnection();
 }
 
 private void waitForConnection(){
  LocalDevice localdev;
  StreamConnectionNotifier notifier=null;
  StreamConnection conn=null;
  String myServiceName="RemoteNotifier";
  UUID uuid=new UUID("0f2b61c18be240e6ab90e735818da0a7", false);
  System.out.println(uuid.toString());
  String url="btspp://localhost:"+uuid.toString()+";"+"name="+myServiceName ;
  try {
   localdev=LocalDevice.getLocalDevice();  
   localdev.setDiscoverable(DiscoveryAgent.GIAC);  
   notifier=(StreamConnectionNotifier)Connector.open(url);
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
   return;
  }    
  while(true){  
   try {
    System.out.println("Waiting for the Connection1");
    conn=notifier.acceptAndOpen();    
    Thread processorThread=new Thread(new ProcessConnectionThread(conn));
    processorThread.start();
   } catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
   }
   
  }
 }
 
}