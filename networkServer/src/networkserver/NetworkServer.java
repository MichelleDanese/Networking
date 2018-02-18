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
import java.util.Scanner;

/**
 *
 * @author miche, Sam
 */
public class NetworkServer {

    /**
     * @param args the command line arguments
     *get/send current date and time
     *get/send uptime
     *get/send memory use
     *get/send Netstat
     *
     */
    public static void main(String[] args)throws IOException{
      int input = 0;
      
      ServerSocket serv = new ServerSocket(4444);
      Socket client = serv.accept();
        
      Scanner scan = new Scanner(client.getInputStream());
      
      input = scan.nextInt();
      
      
      PrintStream p = new PrintStream(client.getOutputStream());
      p.println(input);
    }
    
    
}
