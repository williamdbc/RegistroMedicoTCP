package Servidor.algoritmoApriori;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;


public class AlgoritmoApriori {
    
    private double frequenciaMinima = 0.5;
    private double confiancaMinima = 0.75;

    public AlgoritmoApriori() {
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
        
        if(frequencia/tam >= frequenciaMinima){
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
    //Gerar candidato 
    private ArrayList<ArrayList<String>> calculateFrequentItemsets(ArrayList<ArrayList<String>> transactions, double minSup, ArrayList<ArrayList<String>> candidates) {
        ArrayList<ArrayList<String>> frequentCandidates = new ArrayList<ArrayList<String>>(); // Os candidatos frequentes para o itemset atual
        int numTransactions = transactions.size(); // O número de transações
        int numItems = transactions.get(0).size(); // O número de itens em cada transação
        boolean match; // Se a transação possui todos os itens em um itemset
        boolean[] trans = new boolean[numItems]; // Um array para representar uma transação e verificar se os itens estão presentes
        int[] count = new int[candidates.size()]; // O número de correspondências bem-sucedidas

        for (int i = 0; i < numTransactions; i++) {
            ArrayList<String> transaction = transactions.get(i);

            // Coloque o conteúdo da transação no array 'trans'
            for (int j = 0; j < numItems; j++) {
                trans[j] = transaction.equals(candidates.get(0).get(j)); // Verifique se o item está presente na transação
            }

            for (int c = 0; c < candidates.size(); c++) {
                match = true; // Redefina a correspondência como verdadeira
                ArrayList<String> cand = candidates.get(c);

                // Verifique se cada item no itemset está presente na transação
                for (String item : cand) {
                    if (!trans[transaction.indexOf(item)]) { // Se o item não estiver presente na transação, defina a correspondência como falsa
                        match = false;
                        break;
                    }
                }

                if (match) {
                    count[c]++; // Se houver uma correspondência, aumente a contagem
                }
            }
        }

        for (int i = 0; i < candidates.size(); i++) {
            // Se a porcentagem de suporte for maior ou igual ao minSup, adicione o candidato aos candidatos frequentes
            if ((count[i] / (double) numTransactions) >= minSup) {
                frequentCandidates.add(candidates.get(i));
            }
        }

        // Limpe a lista de candidatos antigos
        candidates.clear();
        // Os novos candidatos são os candidatos frequentes antigos
        candidates.addAll(frequentCandidates);
        frequentCandidates.clear();
        return candidates;
    }
    
    //Quarta etapa
    private ArrayList<Candidato> gerarItensFrequentes(ArrayList<Candidato> tabelaCandidatos){
        return (ArrayList<Candidato>) tabelaCandidatos.stream().filter(x -> x.getFrequencia() >= this.frequenciaMinima);
    }
    
    
  public String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnostico) {
        // 1  etapa e 2 etapa
        ArrayList<String> tabelaCandidatos = calculateFrequentItemsets(sintomas, diagnostico);

        // 3 etapa
        //ArrayList<ArrayList<String>> result = calculateFrequentItemsets(tabelaCandidatos);

        // Faça as outras etapas do Apriori, se necessário

        // Retorna algum resultado
        return "Teste";
    }
    
    public static void main(String[] args) {
        // Exemplo de como usar a classe AlgoritmoApriori
        AlgoritmoApriori apriori = new AlgoritmoApriori();
        ArrayList<ArrayList<String>> sintomas = new ArrayList<ArrayList<String>>();
        ArrayList<String> diagnostico = new ArrayList<String>();
        String resultado = apriori.realizarDiagnostico(sintomas, diagnostico);
        System.out.println(resultado);
    }
    
}
