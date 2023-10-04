package Servicos;

import Enum.Requisicao;
import java.io.Serializable;
import java.util.ArrayList;

public class Solicitacao implements Serializable{
    
    private ArrayList<String> sintomas;
    private String diagnostico;
    private Requisicao tipoRequisicao;

    public Solicitacao(ArrayList<String> sintomas, String diagnostico, Requisicao tipoRequisicao) {
        this.sintomas = sintomas;
        this.diagnostico = diagnostico;
        this.tipoRequisicao = tipoRequisicao;
    }
    
    public ArrayList<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(ArrayList<String> sintomas) {
        this.sintomas = sintomas;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Requisicao getTipoRequisicao() {
        return tipoRequisicao;
    }

    public void setTipoRequisicao(Requisicao tipoRequisicao) {
        this.tipoRequisicao = tipoRequisicao;
    }
    
}
