package Servidor;

import static Servidor.Apriori.calcularSuporte;
import static Servidor.Apriori.calcularSuporteDiagnosticos;
import java.util.ArrayList;
import java.util.List;

public class Teste {
    
    public static List<List<String>> copyArrayListToNestedList(ArrayList<ArrayList<String>> arrayList) {
        List<List<String>> newList = new ArrayList<>();
        for (ArrayList<String> innerList : arrayList) {
            List<String> copy = new ArrayList<>(innerList);
            newList.add(copy);
        }
        return newList;
    }
    
    public static List<List<String>> getSintomasWithDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos) {
        List<List<String>> sintomasComDiagnostico = copyArrayListToNestedList(sintomas);
     
        
        for(String diag: diagnosticos){
            for (List<String> itens : sintomasComDiagnostico) {
                itens.add("Doenca" + diag);

            }
        }
        
        return sintomasComDiagnostico;
    }
    
    public static String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos, ArrayList<String> sintomasRelatados) {
        List<List<String>> transactions = getSintomasWithDiagnostico(sintomas, diagnosticos);
        printTransactions(transactions);
       // Variáveis para acompanhar a regra com maior confiança
        String maxSymptom = "";
        String maxDisease = "";
        double maxConfidence = 0.0;

        // Encontre a regra com maior confiança
        for (String symptom : sintomasRelatados) {
            for (String disease : getAssociatedDiseases(symptom, transactions)) {
                double confidence = calculateConfidence(symptom, disease, transactions);
                if (confidence > maxConfidence) {
                    maxSymptom = symptom;
                    maxDisease = disease;
                    maxConfidence = confidence;
                }
            }
        }
        
        // Verifique se há alguma regra de associação que atenda à confiança mínima
        if (maxConfidence > 0.0) {
            // Imprima a regra com maior confiança
            System.out.println(maxSymptom + " -> " + maxDisease + " [Confidence: " + maxConfidence + "]");
             return maxDisease;
        }
        
        System.out.println("Nenhum diagnóstico encontrado");
        return "Nenhum diagnóstico encontrado";
    }
    
    public static void printTransactions(List<List<String>> transactions) {
        for (int i = 0; i < transactions.size(); i++) {
            System.out.println("Paciente " + i + ": " + transactions.get(i));
        }
    }

    
    public static void main(String[] args) {
        // Dados de exemplo (conjunto de transações)
        ArrayList<ArrayList<String>> transactions = new ArrayList<>();
        transactions.add(new ArrayList<>(List.of("S1", "S2")));
        transactions.add(new ArrayList<>(List.of("S1", "S2", "S5")));
        transactions.add(new ArrayList<>(List.of("S1", "S3")));
        transactions.add(new ArrayList<>(List.of("S1", "S2", "S3")));
        transactions.add(new ArrayList<>(List.of("S1", "S3", "S4")));


        // Sintomas de interesse
        ArrayList<String> symptomsOfInterest = new ArrayList<>(List.of("S1",  "S3"));
        ArrayList<String> diagnosticos = new ArrayList<>(List.of("D1", "D2", "D3"));
        realizarDiagnostico(transactions, diagnosticos , symptomsOfInterest);
        
       
    }

    // Função para obter doenças associadas a um sintoma
    public static List<String> getAssociatedDiseases(String symptom, List<List<String>> transactions) {
        List<String> associatedDiseases = new ArrayList<>();
        for (List<String> transaction : transactions) {
            if (transaction.contains(symptom)) {
                for (String item : transaction) {
                    if (item.startsWith("Doenca") && !item.equals(symptom)) {
                        associatedDiseases.add(item);
                    }
                }
            }
        }
        return associatedDiseases;
    }

    // Função para calcular a confiança de uma regra (sintoma -> doença)
    public static double calculateConfidence(String symptom, String disease, List<List<String>> transactions) {
        int supportX = 0;
        int supportXandY = 0;
        for (List<String> transaction : transactions) {
            if (transaction.contains(symptom)) {
                supportX++;
                if (transaction.contains(disease)) {
                    supportXandY++;
                }
            }
        }
        return (double) supportXandY / supportX;
    }
}

