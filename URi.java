/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Al5al-PC
 */
public class URi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("please enter the url");
        String url = in.nextLine();
        
        getConnection(url);
    }
    public static void getConnection(String url)
    {
        BufferedReader br = null ; 
        
        try
        {URL myurl = new URL (url);
        URLConnection  myconnect = myurl.openConnection();
            InputStreamReader isr = new InputStreamReader(myconnect.getInputStream());
            br = new BufferedReader(isr) ;
            String line ; 
           PrintWriter out = new PrintWriter("name.html");
         while((line = br.readLine()) != null)
         {
             out.println(line);
         }
        }
        catch(Exception e )
        {
            System.out.println("\n cann't access url ");
            try {
                br.close();
            } catch (IOException ex) {
               ex.printStackTrace();
            }
            System.exit(0);
        }
        finally{
            
        }
    }
}
