/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Alex Geer
 */

/*
machine1
192.168.100.113

ciswkstn113

machine2
192.168.100.114

ciswkstn114
*/

public class Client extends Thread{
    
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    String socketAddr;
    private int command;
    
    public Client(String ipAddress, int command){
        this.command = command;
        this.socketAddr = ipAddress;
        
    }

    @Override

    public void run(){
        try {
            //make connected socket
            clientSocket = new Socket(socketAddr, 5555);
            
            //get sockets output and input stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            
            //send command
            sendCommand();
            
            //listen for response
            //need code here!
            
            
            
            //close connection
            out.close();
            in.close();
            clientSocket.close();
            
            
        } catch (IOException ex) {
            System.out.println("client connection error");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        

    }    
    
    private void sendCommand(){
           out.print(command);
    }
}

