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
import java.net.ConnectException;
import java.net.Socket;
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
    
    //Client IO tools
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    //server response
    String response; 
    
    //Client IO config fields
    String socketAddr;
    
    //user input 
    private int command;
    
    //response time metrics
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
            //start time
            tStart = System.nanoTime();
            
            //new connected socket
            clientSocket = new Socket(socketAddr, 5555);
            
            //get sockets output and input stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            
            //send command
            sendCommand();
           
            //listen/wait until something comes out of the buffer
            while( (response = in.readLine() ) == null) 
            {
                //print response later to get accurate time    
            }
            
            
            //end time
            tEnd = System.nanoTime();
            
            //close streams first
            out.close();
            in.close();
            //close socket
            clientSocket.close();
            
        }
        catch(ConnectException ce){
                System.out.println("client" + clientName + " : *conn refused*");
            }
        catch (NullPointerException np){
                System.out.println("Socket not connected, cannot get streams");
            }
        catch (IOException ex) {
            System.out.println("client connection error");
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
            System.out.println("Unknown exception");
        }
        
        
        System.out.println("Client " + clientName + ": " + response);
        //calc the timing
        duration = tEnd - tStart;
        
        

    }    
    
    private void sendCommand(){
           out.println(command);
           
    }
}

