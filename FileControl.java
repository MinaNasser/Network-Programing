/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastV;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Al5al-PC
 */
public  class  FileControl {

    private static PrintWriter pw;
    
    private static FileWriter fr=null;
    private  static  File file=null;
    private  static Scanner fromeFile=null;

    public FileControl() {
        try {
            file=new File("data.txt");
            fr=new FileWriter(file,true);
            pw =new PrintWriter(fr);
            fromeFile =new Scanner(file);
        } catch (IOException ex) {
            Logger.getLogger(FileControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void setDataOnFile(String data){
            pw.println(data);
    }
     
    public  String getDataFromFile() {
        String data ="";
        try {
            
            while (fromeFile. hasNextLine()) {                
                data=fromeFile.nextLine()+"\n";
            }
        } catch (Exception ex) {
            Logger.getLogger(Serverauthentication.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return data;
    }
    
     public  static  void closeAll(){
        try {
            fr.close();
            pw.close();
            fromeFile.close();
        } catch (IOException ex) {
            Logger.getLogger(FileControl.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    
     
     

     
}
