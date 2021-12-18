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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import taskk.Server;

/**
 *
 * @author Al5al-PC
 */
public class Serverauthentication {

    private static final int PORT = 12345;
    private static ServerSocket serverSocket = null;
    private static Socket client;
    static FileControl fc =new FileControl();
//    static  Scanner readFromFile= null;
//    static File fi =null;
//    static FileWriter  file=null;
//    static PrintWriter pw =null;
    public Serverauthentication() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("\nConnection Ready ");

        } catch (Exception e) {
            System.out.println("don't finde the port");
            System.exit(1);
        }
        while (true) {
            handleConnection();
        }
    }

    public static void handleConnection() {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());

            boolean chack = true;
            
            while (chack) {
                String username = inputStream.readUTF();
                String password = inputStream.readUTF();
                int num = 0;
                if (username.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
                    outputStream.writeUTF("Logged Successful");
                    String msg = inputStream.readUTF();
                    // msg =fc.getDataFromFile();
                    chack = false;
                    while (!msg.equals("**CLOSE**")) {
                        System.out.println("message received");
                        num++;
                        
                        outputStream.writeUTF("the message is: " + msg + "  message Number: " + num);
                        fc.setDataOnFile("the message is: " + msg + "  message Number: " + num);
                        msg = inputStream.readUTF();
                    }
                }
                else{
                     outputStream.writeUTF("username or password is not correct please try againe");
                    chack = true;
                }
            }

        } catch (Exception e) {
            System.exit(1);
        }
        finally{
            try {
                fc.closeAll();
                clientSocket.close();
                
            } catch (IOException ex) {
                ex.getStackTrace();
                System.exit(1);
            }
        }
    }
    
    public static void main(String[] args) {
        Serverauthentication serverauthentication =new Serverauthentication();
    }

}
    
    
    
    
   