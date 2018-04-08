/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
        ServerSocket serv = new ServerSocket(3435);
        while(true){
        System.out.println("Press Ctr+C to terminate...");
        Socket client = serv.accept();
        System.out.println("Connecting");
        ServerHandler handler = new ServerHandler(client, start);
        handler.start();
        }
        
      }
      catch (IOException e) {
      System.out.println(e);
      }
      
      
    }
    
    
}
