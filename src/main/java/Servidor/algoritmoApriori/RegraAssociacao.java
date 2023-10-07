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
public class RegraAssociacao {
    private String antecedente;
    private String consequente;
    private double suporte;
    private double confianca;

    public RegraAssociacao(String antecedente, String consequente, double suporte, double confianca) {
        this.antecedente = antecedente;
        this.consequente = consequente;
        this.suporte = suporte;
        this.confianca = confianca;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public String getConsequente() {
        return consequente;
    }

    public double getSuporte() {
        return suporte;
    }

    public double getConfianca() {
        return confianca;
    }

    @Override
    public String toString() {
        return antecedente + " => " + consequente + " [Suporte: " + suporte + ", Confiança: " + confianca + "]";
    }
}

