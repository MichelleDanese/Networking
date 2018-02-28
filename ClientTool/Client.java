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
    private String response; 

    
    
    //Client IO config fields
    private String socketAddr;
    
    //user input 
    private int command;
    
    //response time metrics
   private long tStart;
   private long tEnd;
   
    
    public Client(String ipAddress, int command, int clientName){
        this.command = command;
        this.socketAddr = ipAddress;
        this.clientName = clientName;    
    }
    
    /**
     *Thread method
     * connect socket, send request, get response, measure time
     */
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
                //wait while buffer is empty  
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
    }
    /**
     * send a command to the server
     */
    private void sendCommand(){
           out.println(command);
           
    }
    
    /**
     * 
     * @return the servers response
     */
    public String getResponse() {
        return response;
    }

    /**
     * 
     * @return the name assigned to the client, a number 0 to nClients-1
     */
    public int getClientName() {
        return clientName;
    }
    
    /**
     * calculates server response time
     * @return the difference of tEnd and tStart
     */
    public long getDuration() {
        return tEnd - tStart;
    }
}

