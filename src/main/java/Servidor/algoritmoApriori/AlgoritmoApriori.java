package Servidor.algoritmoApriori;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    public static List<Set<Integer>> generateKItemsets(List<Set<Integer>> frequentItemsets, int k) {
        List<Set<Integer>> candidateItemsets = new ArrayList<>();

        // Combine frequent (k-1)-itemsets to generate candidate k-itemsets
        for (int i = 0; i < frequentItemsets.size(); i++) {
            for (int j = i + 1; j < frequentItemsets.size(); j++) {
                Set<Integer> itemset1 = frequentItemsets.get(i);
                Set<Integer> itemset2 = frequentItemsets.get(j);

                // Check if the first (k-1) items of the two itemsets are the same
                List<Integer> itemList1 = new ArrayList<>(itemset1);
                List<Integer> itemList2 = new ArrayList<>(itemset2);

                if (itemList1.subList(0, k - 2).equals(itemList2.subList(0, k - 2))) {
                    Set<Integer> newCandidate = new HashSet<>(itemset1);
                    newCandidate.addAll(itemset2);

                    // Check if all (k-1)-itemsets of the candidate are frequent
                    if (isAllSubsetsFrequent(frequentItemsets, newCandidate, k - 1)) {
                        candidateItemsets.add(newCandidate);
                    }
                }
            }
        }

        return candidateItemsets;
    }

    private static boolean isAllSubsetsFrequent(List<Set<Integer>> frequentItemsets, Set<Integer> candidate, int k) {
        List<Set<Integer>> subsets = generateSubsets(candidate, k);

        for (Set<Integer> subset : subsets) {
            if (!frequentItemsets.contains(subset)) {
                return false;
            }
        }

        return true;
    }

    private static List<Set<Integer>> generateSubsets(Set<Integer> itemset, int k) {
        List<Set<Integer>> subsets = new ArrayList<>();
        Integer[] itemArray = itemset.toArray(new Integer[0]);
        int n = itemArray.length;

        generateSubsetsRecursive(subsets, itemArray, new HashSet<>(), 0, n, k);

        return subsets;
    }

    private static void generateSubsetsRecursive(List<Set<Integer>> subsets, Integer[] itemArray, Set<Integer> currentSubset, int index, int n, int k) {
        if (currentSubset.size() == k) {
            subsets.add(new HashSet<>(currentSubset));
            return;
        }

        if (index == n) {
            return;
        }

        currentSubset.add(itemArray[index]);
        generateSubsetsRecursive(subsets, itemArray, currentSubset, index + 1, n, k);

        currentSubset.remove(itemArray[index]);
        generateSubsetsRecursive(subsets, itemArray, currentSubset, index + 1, n, k);
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
        List<Set<Integer>> frequentItemsetsKMinus1 = new ArrayList<>(); // Replace with actual (k-1)-itemsets
        int k = 3; // Change to the desired k

        //responsavel pela terceira etapa
        List<Set<Integer>> candidateItemsetsK = generateKItemsets(frequentItemsetsKMinus1, k);

        System.out.println("Candidate " + k + "-itemsets:");
        for (Set<Integer> itemset : candidateItemsetsK) {
            System.out.println(itemset);
        }
    }
}
