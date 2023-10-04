package Servidor;

import java.util.ArrayList;

public class Database {
    private ArrayList<String> diagnosticos = new ArrayList<String>();
    private ArrayList<ArrayList<String>> sintomas = new ArrayList<>();

    public Database() {
    }
    
    
    public String armazenarConsulta(ArrayList<String> sintomas, String diagnostico){
        this.diagnosticos.add(diagnostico);
        this.sintomas.add(sintomas);
        return "Consulta inserida no banco de dados.";
    }
    
    public String diagnosticoAutomatico(){
        //String teste = AlgoritmoApriori.recomendacaoDiagnostico();
        //fazer o algortimo apriori e retornar o diagnostico fornecido
        return "Diagnositco";
    }
    
    public String retornarCasos(){
        //fazer código para retornar os casos
        return "Caso retornado";
    }
    
}
