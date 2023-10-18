/*
 * Para alterar este cabeçalho de licença, escolha Cabeçalhos de Licença em Propriedades do Projeto.
 * Para alterar este arquivo de modelo, escolha Ferramentas | Modelos
 * e abra o modelo no editor.
 */
package Tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AprioriTeste {
    private static double suporteMinimo = 1;

    public static int calcularFrequencia(ArrayList<String> conjuntoDeItens, ArrayList<String> itensFrequentes) {
        int contagem = 0;
        for (String itemX : conjuntoDeItens) {
            for (String itemY : itensFrequentes) {
                if (itemX.equals(itemY)) {
                    contagem++;
                    break;
                }
            }
        }

        return contagem;
    }

    public static List<Integer> calcularSuporte(ArrayList<String> itensFrequentes, ArrayList<ArrayList<String>> transacoes, double minSuporte) {
        List<Integer> indicesDiagnosticos = new ArrayList<>();
        int indice = 0;
        for (ArrayList<String> conjuntoDeItens : transacoes) {
            int frequencia = calcularFrequencia(conjuntoDeItens, itensFrequentes);

            if (frequencia / itensFrequentes.size() >= minSuporte) {
                indicesDiagnosticos.add(indice);
            }

            indice++;
        }

        return indicesDiagnosticos;
    }

    public static String calcularSuporteDiagnosticos(List<Integer> indicesDiagnosticos, ArrayList<String> diagnosticos) {
       // Crie um mapa para contar a frequência de cada diagnóstico
       Map<String, Integer> mapaFrequencia = new HashMap<>();

       // Preencha o mapa com a frequência de diagnósticos com base nos índices
       for (int indice : indicesDiagnosticos) {
           String diagnostico = diagnosticos.get(indice);
           mapaFrequencia.put(diagnostico, mapaFrequencia.getOrDefault(diagnostico, 0) + 1);
       }

       // Encontre o diagnóstico mais frequente
       String diagnosticoMaisFrequente = null;
       int frequenciaMaisAlta = 0;

       for (Map.Entry<String, Integer> entrada : mapaFrequencia.entrySet()) {
           if (entrada.getValue() > frequenciaMaisAlta) {
               frequenciaMaisAlta = entrada.getValue();
               diagnosticoMaisFrequente = entrada.getKey();
           }
       }

       return diagnosticoMaisFrequente;
    }

    public static String realizarDiagnostico(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos, ArrayList<String> sintomasRelatados) {
        List<Integer> itensFrequentesK = new ArrayList<>();
        itensFrequentesK = calcularSuporte(sintomasRelatados, sintomas, suporteMinimo);

        if (itensFrequentesK.size() > 0) {
            return calcularSuporteDiagnosticos(itensFrequentesK, diagnosticos);
        }
        return "Nenhum diagnóstico encontrado";
    }
}
