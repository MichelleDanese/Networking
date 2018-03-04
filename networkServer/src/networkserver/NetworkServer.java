/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author miche
 */
public class NetworkServer {

    /**
     * @param args the command line arguments
     * get/send current date and time
     * get/send uptime
     * get/send memory use
     * get/send Netstat
     * get/send current users
     * get/send host running processes
     * quit
     */
     public static void main(String[] args)throws IOException{
      String result = "x";
      String s = "";
      int input = -1;
      Date start = new Date();
      
      try {
        ServerSocket serv = new ServerSocket(4444);
        while(true){
        Socket client = serv.accept();
        System.out.println("Connecting");
        
        BufferedReader scan = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        s = scan.readLine();
        System.out.println(s);

        System.out.println(s);
        input = Integer.parseInt(s);
            
        try{
            SystemProtocol sp = new SystemProtocol();
            if(input==1){
                result = sp.getDate();
            }
            if(input == 2){
                result = sp.getUptime(start);
            }
            if(input == 3){ //this should be memory usage
                result = sp.getMemoryUsage();
            }
            if(input == 4){
                result = sp.getNetstat();
            }
            if(input == 5){
                result = sp.getCurrentUsers();
            }
            if (input == 6){
                result = sp.getRunningProcesses();
            }
            
        }
        catch (InterruptedException ie){
            result = "\nerror\n";
        }
        catch (IOException IE){
            result = "error";
        }
        

            PrintWriter p = new PrintWriter(client.getOutputStream(), true);
            
            p.println("Yo");
                
            System.out.println("Disconnecting");
        }
        
      }
      catch (IOException e) {
      System.out.println(e);
      }
      
      
    }
    
    
}
