package Servidor;

import java.util.ArrayList;

public class Database {
    private ArrayList<ArrayList<String>> listaSintomas = new ArrayList<>();
    private ArrayList<String> listaDiagnosticos = new ArrayList<String>();
    
    public Database() {
    }
    
    
    public String armazenarConsulta(ArrayList<String> sintomas, String diagnostico){
        listaSintomas.add(sintomas);
        listaDiagnosticos.add(diagnostico);
        
        System.out.println("Sintomas inseridos: " + sintomas);
        System.out.println("Diagnostico inserido: " + diagnostico);
        System.out.println("");
        
        return "Consulta inserida no banco de dados.";
    }
    
    public String diagnosticoAutomatico(ArrayList<String> sintomasRelatados){
        return new Apriori().realizarDiagnostico(listaSintomas, listaDiagnosticos, sintomasRelatados);
    }
    
    public String retornarCasos(){
        String casos = "";
        
        for(int i = 0; i < listaSintomas.size();  i++){
            casos = casos + "Sintomas: " + listaSintomas.get(i) + "\n";
            casos = casos + "Diagnóstico: " + listaDiagnosticos.get(i) + "\n\n";
        }
        
        return casos;
    }
    
}
