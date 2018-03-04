/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    public void getCPUUsage()  throws java.io.IOException, java.lang.InterruptedException{
        
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
    
    public String getRunningProcesses()throws java.io.IOException, java.lang.InterruptedException {
        // Get runtime
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command 
        java.lang.Process p = rt.exec("ps -A");
        //wait for the process to complete
        p.waitFor();
        
        // Get process' output from its InputStream
        java.io.InputStream is = p.getInputStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
        // print each line
        String processOutput = "";
      String s = null;
        while ((s = reader.readLine()) != null) {
            //loop until buffer is empty
            processOutput += s + "\n";
        }
        is.close(); 
       		
        //return proc output
        return processOutput;
    }
    
    public String getNetstat()throws java.io.IOException, java.lang.InterruptedException {
        // Get runtime
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command 
        java.lang.Process p = rt.exec("netstat");
        //wait for the process to complete
        p.waitFor();
        
        // Get process' output from its InputStream
        java.io.InputStream is = p.getInputStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
        // print each line
        String processOutput = "";
      String s = null;
        while ((s = reader.readLine()) != null) {
            //loop until buffer is empty
            processOutput += s + "\n";
        }
        is.close(); 
       
        //return proc output
        return processOutput;
    }
    
    public String getMemoryUsage()throws java.io.IOException, java.lang.InterruptedException {
        // Get runtime
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command 
        java.lang.Process p = rt.exec("free -m");
        //wait for the process to complete
        p.waitFor();
        
        // Get process' output from its InputStream
        java.io.InputStream is = p.getInputStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
        // print each line
        String processOutput = "";
      String s = null;
        while ((s = reader.readLine()) != null) {
            //loop until buffer is empty
            processOutput += s + "\n";
        }
        is.close(); 
       
        //return proc output
        return processOutput;
    }
    
    public String getCurrentUsers()throws java.io.IOException, java.lang.InterruptedException {
        // Get runtime
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command 
        java.lang.Process p = rt.exec("who");
        //wait for the process to complete
        p.waitFor();
        
        // Get process' output from its InputStream
        java.io.InputStream is = p.getInputStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
        // print each line
        String processOutput = "";
      String s = null;
        while ((s = reader.readLine()) != null) {
            //loop until buffer is empty
            processOutput += s + "\n";
        }
        is.close(); 
       
        //return proc output
        return processOutput;
    }
    
    public String get() throws java.io.IOException, java.lang.InterruptedException {
        // Get runtime
        java.lang.Runtime rt = java.lang.Runtime.getRuntime();
        // Start a new process: UNIX command 
        java.lang.Process p = rt.exec("who");
        //wait for the process to complete
        p.waitFor();
        
        // Get process' output from its InputStream
        java.io.InputStream is = p.getInputStream();
        java.io.BufferedReader reader = new java.io.BufferedReader(new InputStreamReader(is));
        // print each line
        String processOutput = "";
      String s = null;
        while ((s = reader.readLine()) != null) {
            //loop until buffer is empty
            processOutput += s + "\n";
        }
        is.close(); 
       
        //return proc output
        return processOutput;
    }
    
            
    
}
