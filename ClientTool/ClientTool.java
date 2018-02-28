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
    //cmd line arg, ip addr of server machine
    private final String ipAddress;
    //user input reader
    private final BufferedReader stdIn;
    
    //number of clients req by user
    private int nClients;
    //menu option selected by user
    private int option;
    //avg response time for request
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
    
    /**
     * display menu, create clients, request and response protocol
     */
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
                
                //alternate way to wait for threads to finish is : c.join()
                
                //wait for threads to finish
                try{
                    Thread.sleep(5000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ClientTool.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                //calculate average time, print responses
                avgTime = 0;
                for(Client c : clients){
                    System.out.println("Client " + c.getClientName() + ": " + c.getResponse());
                    avgTime += c.getDuration();
                }
                
                //catch div by 0
                try{
                avgTime = avgTime/nClients;
                }
                catch(ArithmeticException ae){
                }
                
                System.out.println("Average server response time: " + avgTime);
                
            }//else   
        }//menu while loop
    }
    
    /**
     * displays a menu on the console
     */
    private void displayMenu(){
        System.out.println( "1.	Host current Date and Time\n" +
                            "2.	Host uptime\n" +
                            "3.	Host memory use\n" +
                            "4.	Host Netstat\n" +
                            "5.	Host current users\n" +
                            "6.	Host running processes\n" +
                            "7.	Quit");
    }
    
    /**
     * gets user input for number of clients 
     * @return the user requested number of clients
     */
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
            while(Integer.parseInt(userInput) > 100 || Integer.parseInt(userInput) < 1){
                System.out.println("Only integer values [1,100]: ");
                userInput = stdIn.readLine();
            }
              
        } catch (IOException ex) {
            Logger.getLogger(ClientTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //if checks out, then return number of clients request
            return Integer.parseInt(userInput);
    }
    
    
    /**
     * user inputs desired menu option
     * @return an int corresponding to the menu option the user wants
     */
    private int getInput(){
        String userInput = "-1";
        try {
            userInput = stdIn.readLine();
            
            //check for numeric format
            while(!isValidOption(userInput) ) {
                System.out.println("Only integer values [1 - 7]: ");
                userInput = stdIn.readLine();
            }
            
            
        } catch (IOException ex) {
            Logger.getLogger(ClientTool.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Integer.parseInt(userInput);
    }
    
    /**
     * 
     * @param option the user input option to be evaluated
     * @return true if valid, false o.w.
     */
    private boolean isValidOption(String option){
        //check if is int
        if(isNumeric(option)){
            //check if valid menu option, 1-7
            int i = Integer.parseInt(option);
            if( i >= 1 && i <= 7 )
                return true;
        }
       
        return false;
    }
    
    /**
     * 
     * @param str
     * @return true if arg string is integer
     */
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
