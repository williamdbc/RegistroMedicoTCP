package Cliente;

import Servicos.Resposta;
import Servicos.Solicitacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    public Cliente() {
    }
    
    public Resposta enviarConsulta(Solicitacao solicitacao){   
        try{
           final String enderecoIP = InetAddress.getLocalHost().getHostAddress();
            
            Socket socket = new Socket(InetAddress.getLocalHost().getHostAddress(), 12345);
            
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            
            objectOutput.writeObject(solicitacao);

            Resposta respostaServidor = (Resposta) objectInput.readObject();

            socket.close();
            
            return respostaServidor;   
        } catch (ClassNotFoundException | IOException mensagem){
            System.out.println("Mensagem de erro ao enviar consulta: " + mensagem);
            return null;
        }
    }
    
}
