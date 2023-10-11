package Servidor;

import Enum.Requisicao;
import Servicos.Resposta;
import Servicos.Solicitacao;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Servidor extends Thread{
    private Socket socket;
    private Database database;

    public Servidor(Socket socket, Database database) {
        this.socket = socket;
        this.database = database;
    }

    @Override
    public void run(){
        try{
            ObjectOutputStream objectOutput = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
            
            Solicitacao solicitacaoRecebida = (Solicitacao) objectInput.readObject();
            
            Resposta respostaServidor = new Resposta();
            
            String respostaDatabase = tipoRequisicao(solicitacaoRecebida);
            
            respostaServidor.setMensagem(respostaDatabase);
         
            objectOutput.writeObject(respostaServidor);
            
        } catch(IOException | ClassNotFoundException mensagem){
            System.out.println("Mensagem de erro ao enviar ou receber objeto no servidor: " + mensagem);
        }
    }
    
    private String tipoRequisicao(Solicitacao solicitacaoRecebida){
        Requisicao tipoRequisicao = solicitacaoRecebida.getTipoRequisicao();
        String resposta = "";
        
        switch(tipoRequisicao){
            case ENVIAR_CONSULTA:
                resposta = database.armazenarConsulta(solicitacaoRecebida.getSintomas(), solicitacaoRecebida.getDiagnostico());
                break;
            case SOLICITAR_CASOS:
                resposta = database.retornarCasos();
                break;
            case SOLICITAR_DIAGNOSTICO:
                resposta = database.diagnosticoAutomatico(solicitacaoRecebida.getSintomas());
                break;
        }
        
        return resposta;
    }
    
}
