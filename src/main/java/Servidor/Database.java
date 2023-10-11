package Servidor;

import Servidor.algoritmoApriori.AlgoritmoApriori;
import Servidor.algoritmoApriori.Apriori;
import java.util.ArrayList;

public class Database {
    private ArrayList<ArrayList<String>> listaSintomas = new ArrayList<>();
    private ArrayList<String> listaDiagnosticos = new ArrayList<String>();
    
    public Database() {
    }
    
    
    public String armazenarConsulta(ArrayList<String> sintomas, String diagnostico){
        listaSintomas.add(sintomas);
        listaDiagnosticos.add(diagnostico);
        return "Consulta inserida no banco de dados.";
    }
    
    public String diagnosticoAutomatico(ArrayList<String> sintomasRelatados){
        return new Apriori().realizarDiagnostico(listaSintomas, listaDiagnosticos, sintomasRelatados);
    }
    
    public String retornarCasos(){
        String casos = "";
        
        ArrayList<String> nomeSintomas = new ArrayList<>();
        
        for(ArrayList<String> sintomasPaciente : listaSintomas){
            for(String sintoma : sintomasPaciente){
                if(!nomeSintomas.contains(sintoma)){
                    nomeSintomas.add(sintoma);
                }
            }
        }
        
        ArrayList<String> diagnosticosDoSintoma = new ArrayList<>();
        
        for(String sintoma : nomeSintomas){
            diagnosticosDoSintoma.clear();
            for(int i = 0 ; i < listaSintomas.size() ; i++){
                if(listaSintomas.get(i).contains(sintoma)){
                    diagnosticosDoSintoma.add(listaDiagnosticos.get(i));
                }
            }
            casos = casos + sintoma + "\n" + diagnosticosDoSintoma + "\n\n";
        }
        
     
        return casos;
    }
    
}
