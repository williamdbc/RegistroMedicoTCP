package Servicos;

import java.io.Serializable;

public class Resposta implements Serializable{
    
    private String mensagem;

    public Resposta() {
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}
