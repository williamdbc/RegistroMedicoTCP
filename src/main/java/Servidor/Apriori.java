package Servidor;

import java.util.ArrayList;
import java.util.List;

public class Apriori {
    
    public static List<List<String>> copiarArray(ArrayList<ArrayList<String>> arrayList) {
        List<List<String>> novaLista = new ArrayList<>();
        for (ArrayList<String> listaInterna : arrayList) {
            List<String> copia = new ArrayList<>(listaInterna);
            novaLista.add(copia);
        }
        return novaLista;
    }
    
    //obtem os sintomas concatenados com os diagnosticos
    public static List<List<String>> getSintomasComDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos) {
        List<List<String>> sintomasComDiagnostico = copiarArray(sintomas);
        int cont = 0;
        
        for(String diag: diagnosticos){
            sintomasComDiagnostico.get(cont).add("Doenca" + diag);
            cont++;
        }
        
        return sintomasComDiagnostico;
    }
    
    public static String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos, ArrayList<String> sintomasRelatados) {
        List<List<String>> transacoes = getSintomasComDiagnostico(sintomas, diagnosticos);
        imprimirTransacoes(transacoes);
       // Variáveis para acompanhar a regra com maior confiança
        String maxSintoma = "";
        String maxDoenca = "";
        double maxConfiabilidade = 0.0;

        // Encontre a regra com maior confiança
        for (String sintoma : sintomasRelatados) {
            for (String doenca : getDoencasAssociadas(sintoma, transacoes)) {
                double confianca = calcularConfianca(sintoma, doenca, transacoes);
                if (confianca > maxConfiabilidade) {
                    maxSintoma = sintoma;
                    maxDoenca = doenca;
                    maxConfiabilidade = confianca;
                }
            }
        }
        
        // Verifique se há alguma regra de associação que atenda à confiança mínima
        if (maxConfiabilidade > 0.5) {
            // Imprima a regra com maior confiança
            //System.out.println(maxSintoma + " -> " + maxDoenca.substring("Doenca".length()) + " [Confiança: " + maxConfiabilidade + "]");
            return maxDoenca.substring("Doenca".length());
        }
        
        System.out.println("Nenhum diagnóstico encontrado");
        return "Nenhum diagnóstico encontrado";
    }
    
    public static void imprimirTransacoes(List<List<String>> transacoes) {
        for (int i = 0; i < transacoes.size(); i++) {
            System.out.println("" + i + ": " + transacoes.get(i));
        }
    }

    // Função para obter doenças associadas a um sintoma
    public static List<String> getDoencasAssociadas(String sintoma, List<List<String>> transacoes) {
        List<String> doencasAssociadas = new ArrayList<>();
        for (List<String> transacao : transacoes) {
            if (transacao.contains(sintoma)) {
                for (String item : transacao) {
                    if (item.startsWith("Doenca") && !item.equals(sintoma)) {
                        doencasAssociadas.add(item);
                    }
                }
            }
        }
        return doencasAssociadas;
    }

    //Esta função calcula a confiança de uma regra (sintoma -> regra com base nas transações.
    //Calcula o suporte (quantas vezes o sintoma  e a doença aparecem juntos) e divide pelo suporte do sintoma.  
    //Isso fornece uma medida de quão confiável é a associação entre o sintoma e a doença.
    public static double calcularConfianca(String sintoma, String doenca, List<List<String>> transacoes) {
        int suporteX = 0;
        int suporteXeY = 0;
        for (List<String> transacao : transacoes) {
            if (transacao.contains(sintoma)) {
                suporteX++;
                if (transacao.contains(doenca)) {
                    suporteXeY++;
                }
            }
        }
        return (double) suporteXeY / suporteX;
    }
}
