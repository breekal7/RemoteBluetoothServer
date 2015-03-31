/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package remotebluetoothserver;

/**
 *
 * @author saifur
 */
public class RemoteBluetoothServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("here 1");
        Thread waitthread= new Thread(new WaitThread());
        waitthread.start();
    }
}
