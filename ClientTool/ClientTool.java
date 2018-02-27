/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex Geer
 */
public class ClientTool {
    private final String ipAddress;
    private final BufferedReader stdIn;
    
    private int nClients;
    
    private int option;
    
    private long avgTime;
   
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClientTool ct = new ClientTool(args[0]);
        ct.execute();
        
    }
    
    public ClientTool(String ipAddress){
        this.ipAddress = ipAddress;
        this.stdIn = new BufferedReader(new InputStreamReader(System.in));
        this.nClients = 0;
    }
    
    private void execute(){
        Client[] clients;
        boolean running = true;
        
        while(running){
            //user requests clients
            nClients = getNumClients();
            //display command opitons
            displayMenu();
            //user selects and option
            option = getInput();
            
            //check if user wants to quit
            if (option == 7)
                running = false;
            
            
            //client loops
            else{
                //reset array
                clients = new Client[nClients];
                
                //make clients
                for(int i = 0;i < nClients; i++){
                    clients[i] = new Client(ipAddress, option, i);
                }
                
                //start clientss
                for(Client c : clients){
                    c.start();
                }
                
                //calculate average time
                avgTime = 0;
                for(Client c : clients){
                    avgTime += c.duration;
                }
                avgTime = avgTime/nClients;
                System.out.println("Average server response time: " + avgTime);
                
            }//else   
        }//menu while loop
    }
    
    private void displayMenu(){
        System.out.println( "1.	Host current Date and Time\n" +
                            "2.	Host uptime\n" +
                            "3.	Host memory use\n" +
                            "4.	Host Netstat\n" +
                            "5.	Host current users\n" +
                            "6.	Host running processes\n" +
                            "7.	Quit");
    }
    
    private int getNumClients(){
        String userInput = "0";
        
        System.out.println("Enter number of clients: ");
        try {
            //read in
            userInput = stdIn.readLine();
            
            //check for numeric format
            while(!isNumeric(userInput)) {
                System.out.println("Only integer values < 100: ");
                userInput = stdIn.readLine();
            }
            
            //dont let user make more than 100 clients
            while(Integer.parseInt(userInput) > 100){
                System.out.println("Only integer values < 100: ");
                userInput = stdIn.readLine();
            }
              
        } catch (IOException ex) {
            Logger.getLogger(ClientTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //if checks out, then return number of clients request
            return Integer.parseInt(userInput);
    }
    
    
    
    private int getInput(){
        String userInput = "-1";
        try {
            userInput = stdIn.readLine();
            
            //check for numeric format
            while(!isValidCommand(userInput) ) {
                System.out.println("Only integer values [1 - 7]: ");
                userInput = stdIn.readLine();
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(userInput);
    }
    
    private boolean isValidCommand(String command){
        //check if is int
        if(isNumeric(command)){
            //check if valid menu option, 1-7
            int i = Integer.parseInt(command);
            if( i >= 1 && i <= 7 )
                return true;
        }
       
        return false;
    }
    
    private boolean isNumeric(String str)  {  
        try {  
            int i = Integer.parseInt(str);  
            }  
        catch(NumberFormatException nfe){  
            return false;  
            }  
    return true;  
    }
}
