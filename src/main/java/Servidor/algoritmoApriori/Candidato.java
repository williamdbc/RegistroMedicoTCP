/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor.algoritmoApriori;

import java.util.ArrayList;

/**
 *
 * @author 2021122760232
 */
public class Candidato {
    private String nome;
    private double frequencia;

    public Candidato(String nome, double frequencia) {
        this.nome = nome;
        this.frequencia = frequencia;
    }

    Candidato(ArrayList<ArrayList<String>> sintomas, ArrayList<String> diagnosticos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(double frequencia) {
        this.frequencia = frequencia;
    }
    
    
    
}
