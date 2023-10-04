package Servidor;

import Servidor.algoritmoApriori.AlgoritmoApriori;
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
        return new AlgoritmoApriori().realizarDiagnostico(sintomas, diagnosticos);
    }
    
    public String retornarCasos(){
        //fazer código para retornar os casos
        return "Caso retornado";
    }
    
}
