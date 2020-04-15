/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirrorservera;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import mirrorservera.models.Token;

/**
 *
 * @author CHRISTIAN
 */
public class MirrorServerB {

    private final int SERVER_PORT = 9091;
    private final int CLIENT_PORT = 9090; // Other server's port
    
    private Thread clientThread;
    private Thread serverThread;
    
    private ObjectInputStream serverOIS, clientOIS;
    private ObjectOutputStream serverOOS, clientOOS;
    
    private  ServerSocket server;
    private  Socket client;
    
    private List<Token> tokens; // Token to be held on both servers
    
    public MirrorServerB() {
        
        this.tokens = new ArrayList();
        
        try{
            server = new ServerSocket(SERVER_PORT);
            
        }catch(IOException exc){
            exc.printStackTrace();
        }
        
    }

    public Token generateToken(String sellerCode){
        
        int[] numbs = new int[6];
        for(int i=0; i<6; i++){
            numbs[i] = 1 + (int)(40*Math.random());
        }
        
        return new Token(sellerCode, numbs);
    }
    
    
    
    
    public void go(){
        
                
        Scanner sc = new Scanner(System.in);
        
        this.clientThread = new Thread(() -> {

            try {
                client = new Socket("127.0.0.1", CLIENT_PORT);
                
                clientOIS = new ObjectInputStream( client.getInputStream() );
                clientOOS = new ObjectOutputStream( client.getOutputStream() );
                
                while (true) {

                    System.out.println("Enter seller code: ");
                    String sellerCode = sc.nextLine();
                    
                    Token newToken = generateToken(sellerCode);
                    tokens.add(newToken);
                    
                    // PRINTING TO STATE TO SEE LIST OF TOKENS CONTAINED
                    System.out.println(tokens.toString());
                    ///////////////////////////////////////
                                       
                    clientOOS.writeObject(newToken);
                    clientOOS.flush();
                    
                    System.out.println("Successfully sent Token: " + newToken+ " to Server A ");
                }
            } catch (IOException exc) {
                exc.printStackTrace();
            }

        });
        
        this.clientThread.start(); // Alternatively we could call the start method in the test or customer class
        
        serverThread = new Thread(()->{
        
            
            try{
                Socket serverSocket = server.accept();
                serverOIS = new ObjectInputStream( serverSocket.getInputStream() );
                
                Token newToken = (Token)serverOIS.readObject();
                
                // Add received token to tokens buffer in this server
                tokens.add(newToken);
                
            }catch(IOException | ClassNotFoundException exc){
                exc.printStackTrace();
            }
            
        });
        
        serverThread.start();
    }
    
    
    
    
    
    

    public Thread getClientThread() {
        return clientThread;
    }

    public void setClientThread(Thread clientThread) {
        this.clientThread = clientThread;
    }

    public Thread getServerThread() {
        return serverThread;
    }

    public void setServerThread(Thread serverThread) {
        this.serverThread = serverThread;
    }

    public ObjectInputStream getServerOIS() {
        return serverOIS;
    }

    public void setServerOIS(ObjectInputStream serverDIS) {
        this.serverOIS = serverDIS;
    }

    public ObjectInputStream getClientOIS() {
        return clientOIS;
    }

    public void setClientOIS(ObjectInputStream clientDIS) {
        this.clientOIS = clientDIS;
    }

    public ObjectOutputStream getServerOOS() {
        return serverOOS;
    }

    public void setServerOOS(ObjectOutputStream serverDOS) {
        this.serverOOS = serverDOS;
    }

    public ObjectOutputStream getClientOOS() {
        return clientOOS;
    }

    public void setClientOOS(ObjectOutputStream clientDOS) {
        this.clientOOS = clientDOS;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }
    
    
    
}
