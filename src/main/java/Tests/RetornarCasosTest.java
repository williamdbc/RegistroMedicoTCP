/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tests;

import Servidor.Database;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author 2021122760291
 */
public class RetornarCasosTest {
    public static void main(String[] args) {
        Database database = new Database();
        
        ArrayList<String> sintomasPaciente1 = new ArrayList<>();
        sintomasPaciente1.add("Dor");
        sintomasPaciente1.add("Febre");
        sintomasPaciente1.add("Tosse");
        sintomasPaciente1.add("Fraqueza");
        String diagnostico1 = "Gripe";
        
        ArrayList<String> sintomasPaciente2 = new ArrayList<>();
        sintomasPaciente2.add("Tosse");
        sintomasPaciente2.add("Fraqueza");
        String diagnostico2 = "Asma";
        
        database.armazenarConsulta(sintomasPaciente1, diagnostico1);
        database.armazenarConsulta(sintomasPaciente2, diagnostico2);
        
        System.out.println(database.retornarCasos());
        JOptionPane.showMessageDialog(null, database.retornarCasos(), "Resposta do servidor", JOptionPane.INFORMATION_MESSAGE);
        
    }
    
}
