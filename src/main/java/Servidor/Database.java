package Servidor;

import java.util.ArrayList;

public class Database {
    
    private ArrayList<ArrayList<String>> listaConsultas = new ArrayList<>();

    public Database() {
    }
    
    
    public String armazenarConsulta(ArrayList<String> consulta){
        listaConsultas.add(consulta);
        System.out.println("Array atual: " + listaConsultas);
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
