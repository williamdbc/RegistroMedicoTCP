package Servidor.algoritmoApriori;

import java.util.ArrayList;
import java.util.List;


public class AlgoritmoApriori {
    
    private double frequenciaMinima = 0.5;
    private double confiancaMinima = 0.75;

    public AlgoritmoApriori() {
    }
    
    //Primeira etapa:
    //Gerar a Tabela de Candidatos com suas Frequências
    private ArrayList<Candidato> gerarTabelaCandidatos(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos) {
        ArrayList<Candidato> tabelaCandidatos = new ArrayList<Candidato>();

        for (int i = 0; i < sintomas.size(); i++) {
            ArrayList<String> sintomasPaciente = sintomas.get(i);
            
            for(String sintoma: sintomasPaciente){
                boolean candidatoExistente = this.candidatoIsInTabelaCandidatos(tabelaCandidatos, sintomas, sintoma);
                     
                // Verificar se um candidato com o mesmo nome já existe na tabelaCandidatos
                if (!candidatoExistente) {
                    Candidato novoCandidato = criarCandidato(sintomas, sintoma); 
                    tabelaCandidatos.add(novoCandidato);
                }    
            }

        }

        return tabelaCandidatos;
    }

    
    private Candidato criarCandidato(ArrayList<ArrayList<String>> sintomas, String candidato ){
        int tam = sintomas.size();
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
        
        return new Candidato(candidato, frequencia/tam) ;
    }
    
    
    private boolean candidatoIsInTabelaCandidatos(ArrayList<Candidato> tabelaCandidatos, ArrayList<ArrayList<String>> sintomas, String candidato){
        boolean candidatoExistente = false;
        int i = 0;
        for (Candidato candidatoResult : tabelaCandidatos) {
            ArrayList<String> result = sintomas.get(i);
            for(String sintoma: result){
                if (candidatoResult.getNome().equals(candidato)) {
                    candidatoExistente = true;
                    break;
                }
            }
                
        }
        
        return candidatoExistente;
    }
    
    //Segunda etapa:
    //Gerar tabela com itens frequentes
    private ArrayList<Candidato>  gerarTabelaItensFrequentes(ArrayList<Candidato> tabelaCandidatos){
        ArrayList<Candidato> itensFrequentes = new ArrayList<Candidato>();
        for(Candidato candidato : tabelaCandidatos){
            if(candidato.getFrequencia() >= frequenciaMinima){
                itensFrequentes.add(candidato);
            }
        }
        
        return itensFrequentes;
    }
    public  String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas,ArrayList<String> diagnostico){
        
        ArrayList<Candidato> tabelaCandidatos =  gerarTabelaCandidatos(sintomas, diagnostico);
        ArrayList<Candidato> candidatosFrequentes = gerarTabelaItensFrequentes(tabelaCandidatos);
        return "Teste";
    }
    
}
