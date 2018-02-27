/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

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
        Socket client = serv.accept();
        
        Scanner scan = new Scanner(client.getInputStream());
        s = scan.next();
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
            PrintStream p = new PrintStream(client.getOutputStream());
            
            p.println(result);
        
      }
      catch (IOException e) {
      System.out.println(e);
      }
      
      
    }
    
    
}
