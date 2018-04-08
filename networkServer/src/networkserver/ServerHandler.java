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
public class ServerHandler extends Thread {
    Socket client;
    Date start;
    public ServerHandler(Socket incomingClient, Date date){
        client = incomingClient;
        start = date;
    }
    public void run(){
        String result = "x";
      String s = "";
      int input = -1;
      
        try{
        System.out.println("Starting new sever thread");
        BufferedReader scan = new BufferedReader(new InputStreamReader(client.getInputStream()));
        
        s = scan.readLine();
        //System.out.println(s);

        //System.out.println(s);
        input = Integer.parseInt(s);
            
        try{
            SystemProtocol sp = new SystemProtocol();
            if(input==1){
		System.out.println("Sending date and time to client");
                result = sp.getDate();
            }
            if(input == 2){
		System.out.println("Sending uptime to client");

                result = sp.getUptime(start);
            }
            if(input == 3){ //this should be memory usage
		System.out.println("Sending memory usage to client");

                result = sp.getMemoryUsage();
            }
            if(input == 4){
		System.out.println("Sending Nestat to client");

                result = sp.getNetstat();
            }
            if(input == 5){
		System.out.println("Sending current users to client");

                result = sp.getCurrentUsers();
            }
            if (input == 6){
		System.out.println("Sending running process to client");

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
            System.out.print(result);
            p.print(result);
            p.println();             
            System.out.println("Closing Server thread");
            System.out.println("\nDisconnecting");
            client.close();
        }
        catch(Exception e){
        
        
        }
    }
    
}
