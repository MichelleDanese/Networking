/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package clienttool;

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
    
    private int clientName;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    String socketAddr;
    private int command;
    String response; 
    
    long tStart;
    long tEnd;
    long duration;
    
    public Client(String ipAddress, int command, int clientName){
        this.command = command;
        this.socketAddr = ipAddress;
        this.clientName = clientName;    
    }

    @Override

    public void run(){
        try {
            //log start time
            tStart = System.nanoTime();

            clientSocket = new Socket(socketAddr, 4444);

            //make connected socket
            clientSocket = new Socket(socketAddr, 5555);

            
            //get socket's output and input stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            
            //send command
            sendCommand();
           
            //listen/wait until something comes out of the buffer

            
            while( (response = in.readLine() ) != null) 
            {
               
               break; //print response later to get accurate time
               
            }
            

            while( (response = in.readLine() ) == null) 
            {
               ; //waiting until response != null
            }
            //log end time
            tEnd = System.nanoTime();

            
            tEnd = System.nanoTime();
            System.out.println("Zeep");
            
            //close streams first
            out.close();
            in.close();
            //close socket
            clientSocket.close();
            
        } catch (IOException ex) {
            System.out.println("client connection error");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("Client " + clientName + ": " + response);
        //calc the timing
        duration = tEnd - tStart;
        
        

    }    
    
    private void sendCommand(){
           out.println(command);
           
    }
}

