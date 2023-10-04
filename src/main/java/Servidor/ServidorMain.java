package Servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMain {
    
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        
        Database database = new Database();
        
        try{
            serverSocket = new ServerSocket(12345);
            System.out.println("Servidor iniciado.");
            
            while(true){
                socket = serverSocket.accept();
                
                Thread threadServidor = new Servidor(socket, database);
                threadServidor.start();
            }
        } catch(IOException mensagem){
            System.out.println("Mensagem de erro ao criar socket no servidor: " + mensagem);
        }
    }
    
}
