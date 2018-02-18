/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package networkserver;

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
    
    
}
