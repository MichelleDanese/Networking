/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author miche
 */
public class SystemProtocol {
    public SystemProtocol(){
        
        
    }
    
    public String getDate(){
        DateFormat d = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        
        return d.format(date);
    }
    public String getUptime(Date d){
        Date current = new Date();
        String result = Long.toString(TimeUnit.MILLISECONDS.toSeconds(current.getTime()-d.getTime()));
        return result;
        
    }
    public void getCPUUsage(){
        try{
            String result = "x";
            ;
		     	ProcessBuilder a = new ProcessBuilder("top", "-b", "-n", "1");
			Process start = a.start();
			BufferedReader input = new BufferedReader(new InputStreamReader(start.getInputStream()));
			while((result = input.readLine())!= null){
                                System.out.println("Yo");
				if(result.isEmpty()){
					break;
				}
				System.out.println(result);
				
			}
        }
        catch(IOException e){
            
        }
        
    }
    
}
