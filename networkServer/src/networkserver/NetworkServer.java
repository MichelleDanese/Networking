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
        /*
        System.out.println(s);
        input = Integer.parseInt(s);
            
            SystemProtocol sp = new SystemProtocol();
            if(input==1){
                result = sp.getDate();
            }
            if(input == 2){
                result = sp.getUptime(start);
            }
            if(input == 3){
                sp.getCPUUsage();
                result = "2";
            }
*/
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
