/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lastV;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import taskk.Client;

/**
 *
 * @author Al5al-PC
 */
public class Clientauthentication {
    public static InetAddress host ; 
    public static final int port = 12345;
    //static  Scanner readFromFile= null;
    static FileControl fc =new FileControl();
    public Clientauthentication() {
        try {
            host=InetAddress.getLocalHost();
            System.out.println("Client ready ");
            
        } catch (Exception e) {
            System.out.println("Connection not Secc");
            System.exit(1);
        }
        accessServer();
    }

    public static void accessServer() {
        Socket serverSocket=null;
        try {
            boolean chack =true;
            serverSocket= new Socket(host,port);
            DataInputStream inputStream =new DataInputStream(serverSocket.getInputStream());
            DataOutputStream outputStream =new DataOutputStream(serverSocket.getOutputStream());
            Scanner input= new Scanner(System.in);
            String user ,pass ,msg ,resp ;
            while (chack) {                
                System.out.println("Enter Username please ");
                user=input.nextLine();
                System.out.println("Enter Password please ");
                pass=input.nextLine();
                outputStream.writeUTF(user);
                outputStream.writeUTF(pass);
                user =inputStream.readUTF();
                if (user.equalsIgnoreCase("Logged Successful")) {
                    chack = false;
                    //file=new File("data.txt");
                    //pw =new PrintWriter(file);
                    //readFromFile =new Scanner(file);
                    
                    do {
                        System.out.print("please enter your message:  ");
                        msg = input.nextLine();
                        fc.setDataOnFile(msg);
                        outputStream.writeUTF(msg);
                        resp = inputStream.readUTF();
                        System.out.println(resp);
                        
                    } while (!msg.equals("**CLOSE**"));
                } else {
                    System.out.println("username or password not true try againe");
                    chack = true;
                }
            }
        } catch (Exception e) {
            System.exit(1);
        }
        finally{
            System.out.println("connection end sucessfully ");
            try {
                fc.closeAll();
                serverSocket.close();
                
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("connection closed");
                System.exit(1);
            }
        }
    }
    public static void main(String[] args) {
        Clientauthentication clientauthentication =new Clientauthentication();
    }
    
}
