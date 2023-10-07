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
import java.util.*;
public class Apriori2 {
    private static int nextSintomaId = 1;
    private static double confiancaMinima = 0.75;
    private static Map<String, Integer> sintomaToIntegerMap = new HashMap<>();
    private static List<Set<String>> transactions;

    public static void main(String[] args) {
        transactions = new ArrayList<>();  // Substitua pelo seu conjunto de transações

        // Defina suas transações
        transactions.add(new HashSet<>(Arrays.asList("Leite", "Pão", "Bolacha", "Suco")));
        transactions.add(new HashSet<>(Arrays.asList("Leite", "Pão", "Suco")));
        transactions.add(new HashSet<>(Arrays.asList("Leite", "Bolacha", "Suco")));
        transactions.add(new HashSet<>(Arrays.asList("Pão", "Bolacha", "Suco")));

        double minSupport = 0.5; // Defina o suporte mínimo desejado

        // 1: Encontrar conjuntos de itens frequentes de tamanho 1 (itens frequentes individuais)
        List<Set<String>> frequentItemsets1 = findFrequent1Itemsets(transactions, minSupport);

        int k = 2; // Comece com conjuntos de 2 itens
        List<Set<String>> candidateItemsets = new ArrayList<>(frequentItemsets1);
        List<Set<String>> frequentItemsetsK = null;
        
        while (!candidateItemsets.isEmpty()) {
            frequentItemsetsK = findFrequentKItemsets(transactions, candidateItemsets, minSupport);

            // Agora você pode usar a lista frequentItemsetsK que contém todos os conjuntos frequentes de tamanho k

            candidateItemsets = generateCandidateItemsets(frequentItemsetsK, k);
            k++;
        }

        if(frequentItemsetsK != null){
            // 3: Calcular o suporte (frequência) de cada conjunto de itens frequente
            Map<Set<String>, Integer> itemsetSupport = calculateSupport(frequentItemsetsK, transactions);

            // 4: Gerar regras de associação a partir dos conjuntos frequentes encontrados nas etapas anteriores
            generateAssociationRules(frequentItemsetsK, itemsetSupport, confiancaMinima);
        }
    }

    private static List<Set<String>> findFrequent1Itemsets(List<Set<String>> transactions, double minSupport) {
         Map<String, Integer> itemsetCount = new HashMap<>();
        List<Set<String>> frequentItemsets = new ArrayList<>();

        for (Set<String> transaction : transactions) {
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

    private static List<Set<String>> findFrequentKItemsets(List<Set<String>> transactions, List<Set<String>> candidateItemsets, double minSupport) {
       Map<Set<String>, Integer> itemsetCount = new HashMap<>();
        List<Set<String>> frequentItemsets = new ArrayList<>();

        for (Set<String> transaction : transactions) {
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
    
    private static Set<Set<String>> generateSubsets(Set<String> itemset) {
        Set<Set<String>> subsets = new HashSet<>();
        generateSubsetsRecursive(itemset, new HashSet<>(), subsets);
        return subsets;
    }
    
    private static void generateSubsetsRecursive(Set<String> remaining, Set<String> subset, Set<Set<String>> subsets) {
        if (remaining.isEmpty()) {
            if (!subset.isEmpty()) {
                subsets.add(new HashSet<>(subset));
            }
            return;
        }

        String element = remaining.iterator().next();
        remaining.remove(element);

        // Explore o ramo incluindo o elemento no subconjunto
        subset.add(element);
        generateSubsetsRecursive(remaining, subset, subsets);

        // Explore o ramo sem incluir o elemento no subconjunto
        subset.remove(element);
        generateSubsetsRecursive(remaining, subset, subsets);

        remaining.add(element);
    }

    private static void generateAssociationRules(List<Set<String>> frequentItemsets, Map<Set<String>, Integer> itemsetSupport, double minConfidence) {
        for (Set<String> itemset : frequentItemsets) {
            Set<Set<String>> itemSubsets = generateSubsets(itemset);

            for (Set<String> subset : itemSubsets) {
                Set<String> complement = new HashSet<>(itemset);
                complement.removeAll(subset);

                double confidence = calculateConfidence(subset, complement, itemsetSupport);

                if (confidence >= minConfidence) {
                    System.out.println(subset + " => " + complement + " (Confidence: " + confidence + ")");
                }
            }
        }
    }

    private static double calculateConfidence(Set<String> antecedent, Set<String> consequent, Map<Set<String>, Integer> itemsetSupport) {
        Set<String> combined = new HashSet<>(antecedent);
        combined.addAll(consequent);

        int combinedSupport = itemsetSupport.get(combined);
        int antecedentSupport = itemsetSupport.get(antecedent);

        return (double) combinedSupport / antecedentSupport;
    }
    
    
   private static Map<Set<String>, Integer> calculateSupport(List<Set<String>> frequentItemsetsK, List<Set<String>> transactions) {
        Map<Set<String>, Integer> itemsetSupport = new HashMap<>();

        for (Set<String> itemset : frequentItemsetsK) {
            int support = 0;

            for (Set<String> transaction : transactions) {
                if (transaction.containsAll(itemset)) {
                    support++;
                }
            }

            itemsetSupport.put(itemset, support);
        }

        return itemsetSupport;
    }

}