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
 * @author miche
 */
public class NetworkServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)throws IOException{
        int number = 0;
      int temp = 0;
      ServerSocket serv = new ServerSocket(4444);
      Socket client = serv.accept();
        System.out.println("rEADING");
      Scanner scan = new Scanner(client.getInputStream());
      
      number = scan.nextInt();
      System.out.println(number);
      temp = number * 2;
      
      PrintStream p = new PrintStream(client.getOutputStream());
      p.println(temp);
    }
    
}