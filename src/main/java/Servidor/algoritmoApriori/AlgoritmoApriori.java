package Servidor.algoritmoApriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;


public class AlgoritmoApriori {
    
    private double frequenciaMinima = 0.5;
    private double confiancaMinima = 0.75;
    private int numTransactions; 
    private ArrayList<ArrayList<String>> sintomas;
    private ArrayList<String> diagnosticos;

    public AlgoritmoApriori() {
        sintomas = new ArrayList<ArrayList<String>>();
       // diagnosticos = new Arrays.asList("1", "2", "3", "4"));

        sintomas.add(new ArrayList<String>(Arrays.asList("Leite", "Pão", "Bolacha", "Suco")));
        sintomas.add(new ArrayList<String>(Arrays.asList("Leite", "Pão", "Suco")));
        sintomas.add(new ArrayList<String>(Arrays.asList("Leite", "Bolacha", "Suco")));
        sintomas.add(new ArrayList<String>(Arrays.asList("Pão", "Bolacha", "Suco")));
        
        numTransactions = sintomas.size();
        String resultado = this.realizarDiagnostico(sintomas, diagnosticos);
        
    }
    
    //Primeira etapa:
    //Gerar a Tabela de Candidatos com suas Frequências
    ///Segunda etapa:
    //Gerar tabela com itens frequentes
    private ArrayList<String> calculateFrequentItemsets(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos) {
        ArrayList<String> tabelaCandidatos = new ArrayList<String>();

        for (int i = 0; i < sintomas.size(); i++) {
            ArrayList<String> sintomasPaciente = sintomas.get(i);
            
            for(String sintoma: sintomasPaciente){
                boolean candidatoExistente = this.candidatoIsInTabelaCandidatos(tabelaCandidatos, sintomas, sintoma);
                     
                // Verificar se um candidato com o mesmo nome já existe na tabelaCandidatos
                if (!candidatoExistente) {
                    String novoCandidato = criarCandidato(sintomas, sintoma); 
                    
                    //insere, se o candidato tiver a frequência >= 0.5
                    if(!novoCandidato.isEmpty()) tabelaCandidatos.add(novoCandidato);
                    
                }    
            }

        }

        return tabelaCandidatos;
    }

    
    private String criarCandidato(ArrayList<ArrayList<String>> sintomas, String candidato ){
        int frequencia = 0;
        for (int i = 0; i < sintomas.size(); i++) {
                ArrayList<String> sintomasPaciente = sintomas.get(i);
                for(String sintoma: sintomasPaciente){
                    if (candidato.equals(sintoma)) {
                        frequencia += 1; 
                        break;
                    }
                }
                
        }
        
        if(frequencia/numTransactions >= frequenciaMinima){
            return candidato;
        }
        
        return "";
    }
    
    
    private boolean candidatoIsInTabelaCandidatos(ArrayList<String> tabelaCandidatos, ArrayList<ArrayList<String>> sintomas, String candidato){
        boolean candidatoExistente = false;
        int i = 0;
        for (String candidatoResult : tabelaCandidatos) {
            ArrayList<String> result = sintomas.get(i);
            for(String sintoma: result){
                if (candidatoResult.equals(candidato)) {
                    candidatoExistente = true;
                    break;
                }
            }
                
        }
        
        return candidatoExistente;
    }
    
    //Terceira etapa
    //Quarta etapa
    private ArrayList<String> gerarGrupoDeCandidatos(ArrayList<String> tabelaCandidatos, ArrayList<ArrayList<String>> sintomas, ArrayList<String> itensFrequentes){
        int tam = sintomas.size();

        HashSet<String> candidatosUnicos = new HashSet<>(); // Usamos um HashSet para manter candidatos únicos
        ArrayList<String> candidatos = new ArrayList<String>();

        for(String candidatoX: tabelaCandidatos){
            for(String candidatoY: tabelaCandidatos){
                
                if(!candidatoX.equals(candidatoY)){
                    double frequencia = 0;

                    for (int i = 0; i < sintomas.size(); i++) {
                        ArrayList<String> sintomasPaciente = sintomas.get(i);
                        int cont = 0;
                        for(String sintoma: sintomasPaciente){

                            if(sintoma.equals(candidatoX) || sintoma.equals(candidatoY)){
                                cont++;
                            }

                            if(cont == 2){
                                frequencia += 1;
                                break;
                            }
                        }
                    }

                    String candidatoCombination = candidatoX + ";" + candidatoY;
                    // Verifica se já adicionou a combinação ou a combinação inversa
                    if (!candidatosUnicos.contains(candidatoCombination) && !candidatosUnicos.contains(candidatoY+ ";" + candidatoX)) {
                        if(frequencia/numTransactions >= frequenciaMinima){
                            candidatosUnicos.add(candidatoCombination);  
                        }
                        
                    }
                }
            }
        }

        return candidatos;
    }
    
    //Quinta etapa
    private ArrayList<Candidato> calcularConbiabilidade(ArrayList<Candidato> tabelaCandidatos){
        return (ArrayList<Candidato>) tabelaCandidatos.stream().filter(x -> x.getFrequencia() >= this.frequenciaMinima);
    }
    

    
  public String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnostico) {
        // 1  etapa e 2 etapa
        ArrayList<String> tabelaCandidatos = calculateFrequentItemsets(sintomas, diagnostico);

        return "Teste";
    }
    
    public static void main(String[] args) {
        
    }
    
}
