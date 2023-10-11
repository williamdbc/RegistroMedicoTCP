/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.algoritmoApriori;

import static Servidor.algoritmoApriori.AlgoritmoApriori.calculateConfidences;
import static Servidor.algoritmoApriori.AlgoritmoApriori.realizarDiagnostico;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Apriori {
    private static double minSupport = 1;
    
    public static void main(String[] args) {
        // Defina suas transações
        ArrayList<ArrayList<String>> transactions = new ArrayList<>();
        transactions.add(new ArrayList<>(Arrays.asList("S1", "S2", "S3"))); // Diagnóstico 1
        transactions.add(new ArrayList<>(Arrays.asList("S1", "S3", "S4"))); // Diagnóstico 2
        transactions.add(new ArrayList<>(Arrays.asList("S1", "S2", "S3"))); // Diagnóstico 1
        transactions.add(new ArrayList<>(Arrays.asList("S1", "S4"))); // Diagnóstico 3
        transactions.add(new ArrayList<>(Arrays.asList("S1", "S3", "S4", "S5"))); // Diagnóstico 2

        
        ArrayList<String>  diagnosticos = new ArrayList<>();
        diagnosticos.add("D1");
        diagnosticos.add("D2");
        diagnosticos.add("D1");
        diagnosticos.add("D2");
        diagnosticos.add("D2");
        
        ArrayList<String> sintomasRelatados = new ArrayList<>();
        sintomasRelatados.add("S1");
        sintomasRelatados.add("S4");

        String dignostico = realizarDiagnostico(transactions, diagnosticos, sintomasRelatados);
        System.out.println("Diagnostico: " + dignostico);
    }
        
    public static int calculateFrequency(ArrayList<String> itemSet, ArrayList<String> frequentItemSets ) {
        int count = 0;
        for (String itemX : itemSet) {
            for (String itemY : frequentItemSets) {
                if (itemX.equals(itemY)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
    
    public static List<Integer> calculateSupport(ArrayList<String> frequentItemSets, ArrayList<ArrayList<String>> transactions, double minSupport) {
        List<Integer> indexsDiagnosticos = new ArrayList<>();
        int index = 0;
        for (ArrayList<String> itemSet : transactions) {
            int frequency = calculateFrequency(itemSet, frequentItemSets);

            if (frequency / frequentItemSets.size() >= minSupport) {
                indexsDiagnosticos.add(index);
            }

            index++;
        }

        return indexsDiagnosticos;
    }
    
    public static String calculateSupportDiagnosticos(List<Integer> indexsDiagnosticos, ArrayList<String> diagnosticos) {
       // Crie um mapa para contar a frequência de cada diagnóstico
       Map<String, Integer> frequencyMap = new HashMap<>();

       // Preencha o mapa com a frequência de diagnósticos com base nos índices
       for (int index : indexsDiagnosticos) {
           String diagnóstico = diagnosticos.get(index);
           frequencyMap.put(diagnóstico, frequencyMap.getOrDefault(diagnóstico, 0) + 1);
       }

       // Encontre o diagnóstico mais frequente
       String diagnósticoMaisFrequente = null;
       int frequênciaMaisAlta = 0;

       for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
           if (entry.getValue() > frequênciaMaisAlta) {
               frequênciaMaisAlta = entry.getValue();
               diagnósticoMaisFrequente = entry.getKey();
           }
       }

       return diagnósticoMaisFrequente;
    }
    
    public static String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos, ArrayList<String> sintomasRelatados) {       
        List<Integer> frequentItemsetsK = new ArrayList<>();
        frequentItemsetsK = calculateSupport(sintomasRelatados, sintomas, minSupport);
        if(frequentItemsetsK.size() > 0) return calculateSupportDiagnosticos(frequentItemsetsK, diagnosticos);
        return "Nenhum diagnóstico encontrado";
    }

         
}