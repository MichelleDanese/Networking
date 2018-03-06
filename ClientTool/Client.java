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
        response = "";
        duration = 0;
    }

    @Override

    public void run(){
        try {
            //start time
            tStart = System.nanoTime();
            
            //new connected socket
            clientSocket = new Socket(socketAddr, 3435);
            
            //get sockets output and input stream
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader( clientSocket.getInputStream()));
            
            //send command
            sendCommand();
           
            String r;
            
            //listen/wait until something comes out of the buffer
            while( (r = in.readLine()) != null ){
                response += r + "\n";
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
       System.out.println("Client " + clientName + " disconnecting");
    }    
    
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
    public double getDuration() {
        return (tEnd - tStart)/1000000.0;
    }
}

