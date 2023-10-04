package Cliente;

import Servicos.Solicitacao;
import Enum.Requisicao;
import Servicos.Resposta;
import java.util.ArrayList;

public class ClienteTest {
    public static void main(String[] args) {
        ArrayList<String> sintomasPaciente = new ArrayList<>();
        sintomasPaciente.add(("S1"));
        sintomasPaciente.add(("S2"));
        sintomasPaciente.add(("S3"));
   
        String diagnostico = "qualquer coisa";

        Cliente cliente = new Cliente();
        
        Solicitacao solicitacaoCliente = new Solicitacao(sintomasPaciente, diagnostico, Requisicao.ENVIAR_CONSULTA);
        
        Resposta respostaServidor = cliente.enviarConsulta(solicitacaoCliente);
        
        System.out.println("Mensagem retornada do servidor: " + respostaServidor.getMensagem());
    
        
    }
    
}
