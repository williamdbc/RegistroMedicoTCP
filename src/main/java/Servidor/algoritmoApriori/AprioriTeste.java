/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.algoritmoApriori;

/**
 *
 * @author prisc
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AprioriTeste{
    // ... (código Apriori anterior)
    private static int nextSintomaId = 1;
    private List<Integer[]>  itemsets;
    private double minSupport = 0.5;
    private static double confiancaMinima = 0.75;
    private int numTransactions; 
    private static ArrayList<ArrayList<String>> transactions;
    private static Map<String, Integer> sintomaToIntegerMap = new HashMap<>();
    
    public static void main(String[] args) {
        transactions = new ArrayList<ArrayList<String>>();  // Substitua pelo seu conjunto de sintomas
        ArrayList<String> diagnosticos = new ArrayList<>();  // Substitua pelo seu conjunto de diagnósticos

        diagnosticos = new ArrayList<>(Arrays.asList("1", "2", "3", "4"));

        transactions.add(new ArrayList<String>(Arrays.asList("Leite", "Pão", "Bolacha", "Suco")));
        transactions.add(new ArrayList<String>(Arrays.asList("Leite", "Pão", "Suco")));
        transactions.add(new ArrayList<String>(Arrays.asList("Leite", "Bolacha", "Suco")));
        transactions.add(new ArrayList<String>(Arrays.asList("Pão", "Bolacha", "Suco")));
        
        int numTransactions = transactions.size();
        double minSupport = 0.5; // Defina o suporte mínimo desejado
        

        // 1: Encontrar conjuntos de itens frequentes de tamanho 1 (itens frequentes individuais)
        List<Set<String>> frequentItemsets1 = findFrequent1Itemsets(transactions, minSupport);
        
        List<Set<String>> candidateItemsets = null;

        // 2: Interativamente encontrar conjuntos de itens frequentes de tamanhos maiores (k) a partir dos conjuntos frequentes de tamanho (k-1).
        int k = 2; // Comece com conjuntos de 2 itens
        while (!frequentItemsets1.isEmpty()) {
            candidateItemsets = generateCandidateItemsets(frequentItemsets1, k);
            List<Set<String>> frequentItemsetsK = findFrequentKItemsets(transactions, candidateItemsets, minSupport);

            frequentItemsets1 = frequentItemsetsK;  // Atualizar os itens frequentes de tamanho (k-1)
            k++;
        }

        // Agora você pode usar a lista frequentItemsets que contém todos os conjuntos frequentes

        // 3: Calcular o suporte (frequência) de cada conjunto de itens frequente
        Map<Set<String>, Integer> itemsetSupport = new HashMap<>();
        for (Set<String> itemset : candidateItemsets) {
            int support = calculateSupport(itemset, transactions); // Implemente a função calculateSupport
            itemsetSupport.put(itemset, support);
        }

        for (Set<String> itemset : candidateItemsets) {
            System.out.print("{ ");
            for (String item : itemset) {
                System.out.print(item + " ");
            }
            System.out.println("}");
        }
        
        // 4: Gerar regras de associação a partir dos conjuntos frequentes encontrados nas etapas anteriores
        if (candidateItemsets != null && !candidateItemsets.isEmpty()) {
            // Após o loop, chame generateAssociationRules com os candidatos finais e a confiança mínima
            generateAssociationRules(candidateItemsets, itemsetSupport, confiancaMinima);
        }
        
    }
    
     // Etapa 1: Geração de conjuntos frequentes de tamanho 1 (itens individuais)
    private static List<Set<String>> findFrequent1Itemsets(List<ArrayList<String>> transactions, double minSupport) {
        Map<String, Integer> itemsetCount = new HashMap<>();
        List<Set<String>> frequentItemsets = new ArrayList<>();

        for (List<String> transaction : transactions) {
            for (String item : transaction) {
                itemsetCount.put(item, itemsetCount.getOrDefault(item, 0) + 1);
            }
        }

        for (Map.Entry<String, Integer> entry : itemsetCount.entrySet()) {
            if (entry.getValue() >= minSupport * transactions.size()) {
                Set<String> itemset = new HashSet<>();
                itemset.add(entry.getKey());
                frequentItemsets.add(itemset);
            }
        }

        return frequentItemsets;
    }

    // Etapa 2: Geração de candidatos de tamanho k a partir dos frequentes k-1
    private static List<Set<String>> generateCandidateItemsets(List<Set<String>> frequentItemsetsKMinus1, int k) {
        List<Set<String>> candidateItemsets = new ArrayList<>();

        for (int i = 0; i < frequentItemsetsKMinus1.size(); i++) {
            for (int j = i + 1; j < frequentItemsetsKMinus1.size(); j++) {
                Set<String> itemset1 = frequentItemsetsKMinus1.get(i);
                Set<String> itemset2 = frequentItemsetsKMinus1.get(j);

                List<String> itemList1 = new ArrayList<>(itemset1);
                List<String> itemList2 = new ArrayList<>(itemset2);

                if (itemList1.subList(0, k - 2).equals(itemList2.subList(0, k - 2))) {
                    Set<String> newCandidate = new HashSet<>(itemset1);
                    newCandidate.addAll(itemset2);
                    candidateItemsets.add(newCandidate);
                }
            }
        }

        return candidateItemsets;
    }
    
    //3 etapa
    private static List<Set<String>> findFrequentKItemsets(List<ArrayList<String>> transactions, List<Set<String>> candidateItemsets, double minSupport) {
        Map<Set<String>, Integer> itemsetCount = new HashMap<>();
        List<Set<String>> frequentItemsets = new ArrayList<>();

        for (List<String> transaction : transactions) {
            for (Set<String> candidate : candidateItemsets) {
                boolean isFrequent = true;
                for (String item : candidate) {
                    if (!transaction.contains(item)) {
                        isFrequent = false;
                        break;
                    }
                }
                if (isFrequent) {
                    itemsetCount.put(candidate, itemsetCount.getOrDefault(candidate, 0) + 1);
                }
            }
        }

        for (Map.Entry<Set<String>, Integer> entry : itemsetCount.entrySet()) {
            if (entry.getValue() >= minSupport * transactions.size()) {
                frequentItemsets.add(entry.getKey());
            }
        }
        
        return frequentItemsets;
    }
    
    // Etapa 4: Geração de regras de associação a partir de conjuntos frequentes
    private static void generateAssociationRules(List<Set<String>> frequentItemsets, Map<Set<String>, Integer> itemsetSupport, double minConfidence) {
        for (Set<String> itemset : frequentItemsets) {
            if (itemset.size() > 1) {
                List<Set<String>> itemSubsets = generateSubsets(itemset);

                for (Set<String> subset : itemSubsets) {
                    Set<String> complement = new HashSet<>(itemset);
                    complement.removeAll(subset);

                    double confidence = calculateConfidence(subset, complement, itemsetSupport);

                    if (confidence >= minConfidence) {
                        int itemsetFreq = itemsetSupport.get(itemset);
                        System.out.println(subset + " => " + complement + " (Confidence: " + confidence + ", Support: " + itemsetFreq + ")");
                    }
                }
            }
        }
    }   

    // Função auxiliar para gerar todos os subconjuntos de um conjunto
    private static List<Set<String>> generateSubsets(Set<String> itemset) {
        List<Set<String>> subsets = new ArrayList<>();
        List<String> itemsetList = new ArrayList<>(itemset);
        int n = itemsetList.size();

        for (int i = 1; i < (1 << n); i++) {
            Set<String> subset = new HashSet<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    subset.add(itemsetList.get(j));
                }
            }
            subsets.add(subset);
        }

        return subsets;
    }

    // Função auxiliar para calcular a confiança de uma regra
    private static double calculateConfidence(Set<String> antecedent, Set<String> consequent, Map<Set<String>, Integer> itemsetSupport) {
        Set<String> combined = new HashSet<>(antecedent);
        combined.addAll(consequent);

        int combinedSupport = itemsetSupport.get(combined);
        int antecedentSupport = itemsetSupport.get(antecedent);

        return (double) combinedSupport / antecedentSupport;
    }
    
    //3: etapa
    private static int calculateSupport(Set<String> itemset, List<ArrayList<String>> transactions) {
        int support = 0;

        for (ArrayList<String> transaction : transactions) {
            if (transaction.containsAll(itemset)) {
                support++;
            }
        }

        return support;
    }

    private void log(String message) {
    	
        System.err.println(message);
    	
    }

}

