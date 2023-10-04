package Cliente;

import Cliente.Cliente;
import Enum.Requisicao;
import Servicos.Resposta;
import Servicos.Solicitacao;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class ClienteMain extends javax.swing.JFrame {

    Cliente cliente = null;
    
    public ClienteMain() {
        cliente = new Cliente();
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        efetuarDigAutoButton = new javax.swing.JToggleButton();
        enviarCasosArmButton = new javax.swing.JToggleButton();
        txtDiagnostico = new javax.swing.JTextField();
        enviarButton = new javax.swing.JToggleButton();
        diarreia = new javax.swing.JCheckBox();
        doresMusculares = new javax.swing.JCheckBox();
        dorCabeca = new javax.swing.JCheckBox();
        vomitos = new javax.swing.JCheckBox();
        febre = new javax.swing.JCheckBox();
        tosses = new javax.swing.JCheckBox();
        dificuldadeRespirar = new javax.swing.JCheckBox();
        fadiga = new javax.swing.JCheckBox();
        fraqueza = new javax.swing.JCheckBox();
        perdaApetite = new javax.swing.JCheckBox();
        enjoo = new javax.swing.JCheckBox();
        chiadoPeito = new javax.swing.JCheckBox();

        jRadioButton1.setText("jRadioButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Registro Médico");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel2.setText("Sintomas: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel3.setText("Diagnóstico: ");

        efetuarDigAutoButton.setBackground(new java.awt.Color(255, 153, 0));
        efetuarDigAutoButton.setText("Realizar diagnóstico automático");
        efetuarDigAutoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                efetuarDigAutoButtonActionPerformed(evt);
            }
        });

        enviarCasosArmButton.setBackground(new java.awt.Color(0, 102, 255));
        enviarCasosArmButton.setText("Solicitar casos armazenados ");
        enviarCasosArmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarCasosArmButtonActionPerformed(evt);
            }
        });

        txtDiagnostico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiagnosticoActionPerformed(evt);
            }
        });

        enviarButton.setBackground(new java.awt.Color(51, 255, 51));
        enviarButton.setText("Enviar");
        enviarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarButtonActionPerformed(evt);
            }
        });

        diarreia.setText("Diarreia");

        doresMusculares.setText("Dores musculares");

        dorCabeca.setText("Dor de cabeça ");
        dorCabeca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dorCabecaActionPerformed(evt);
            }
        });

        vomitos.setText("Vômitos");

        febre.setText("Febre");

        tosses.setText("Tosses");

        dificuldadeRespirar.setText("Dificuldade para respirar");
        dificuldadeRespirar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dificuldadeRespirarActionPerformed(evt);
            }
        });

        fadiga.setText("Fadiga");

        fraqueza.setText("Fraqueza ");

        perdaApetite.setText("Perda de apetite");

        enjoo.setText("Enjoo");

        chiadoPeito.setText("Chiado no peito ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(efetuarDigAutoButton, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(enviarCasosArmButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(diarreia)
                                .addGap(18, 18, 18)
                                .addComponent(doresMusculares)
                                .addGap(18, 18, 18)
                                .addComponent(dorCabeca)
                                .addGap(18, 18, 18)
                                .addComponent(dificuldadeRespirar)
                                .addGap(26, 26, 26)
                                .addComponent(fadiga))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vomitos)
                                .addGap(18, 18, 18)
                                .addComponent(febre)
                                .addGap(18, 18, 18)
                                .addComponent(tosses)
                                .addGap(18, 18, 18)
                                .addComponent(fraqueza)
                                .addGap(18, 18, 18)
                                .addComponent(perdaApetite)
                                .addGap(18, 18, 18)
                                .addComponent(enjoo)
                                .addGap(18, 18, 18)
                                .addComponent(chiadoPeito)))
                        .addContainerGap(17, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(354, 354, 354))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(enviarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(326, 326, 326))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(diarreia)
                    .addComponent(doresMusculares)
                    .addComponent(dorCabeca)
                    .addComponent(dificuldadeRespirar)
                    .addComponent(fadiga))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vomitos)
                    .addComponent(febre)
                    .addComponent(tosses)
                    .addComponent(fraqueza)
                    .addComponent(perdaApetite)
                    .addComponent(enjoo)
                    .addComponent(chiadoPeito))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(28, 28, 28)
                .addComponent(enviarButton)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(enviarCasosArmButton)
                    .addComponent(efetuarDigAutoButton))
                .addGap(49, 49, 49))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiagnosticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnosticoActionPerformed

    private void sintomasSelecionados(ArrayList<String> sintomasPaciente){
        JCheckBox[] listaSintomas = new JCheckBox[]{
                    diarreia, doresMusculares, dorCabeca, dificuldadeRespirar, fadiga, 
                    vomitos, febre, tosses, fraqueza, perdaApetite, enjoo, chiadoPeito};
        
        for(JCheckBox sintoma : listaSintomas){
            if(sintoma.isSelected()){
                sintomasPaciente.add(sintoma.getText());
            }
        }
    }
    
    private void enviarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarButtonActionPerformed
        ArrayList<String> sintomasPaciente = new ArrayList<>();
        String diagnostico = txtDiagnostico.getText();
        
        sintomasSelecionados(sintomasPaciente);
        
        Solicitacao solicitacaoCliente = new Solicitacao(sintomasPaciente, diagnostico, Requisicao.ENVIAR_CONSULTA);
        
        Resposta respostaServidor = cliente.enviarConsulta(solicitacaoCliente);
        
        String mensagemRetornada = respostaServidor.getMensagem();
       
        JOptionPane.showMessageDialog(this, mensagemRetornada, "Resposta do servidor", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_enviarButtonActionPerformed

    private void enviarCasosArmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarCasosArmButtonActionPerformed
        ArrayList<String> sintomasPaciente = new ArrayList<>();
        String diagnostico = txtDiagnostico.getText();
        
        sintomasSelecionados(sintomasPaciente);
        
        Solicitacao solicitacaoCliente = new Solicitacao(sintomasPaciente, diagnostico, Requisicao.SOLICITAR_CASOS);
        
        Resposta respostaServidor = cliente.enviarConsulta(solicitacaoCliente);
        
        String mensagemRetornada = respostaServidor.getMensagem();
       
        JOptionPane.showMessageDialog(this, mensagemRetornada, "Resposta do servidor", JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_enviarCasosArmButtonActionPerformed

    private void efetuarDigAutoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_efetuarDigAutoButtonActionPerformed
        ArrayList<String> sintomasPaciente = new ArrayList<>();
        String diagnostico = txtDiagnostico.getText();
        
        sintomasSelecionados(sintomasPaciente);
        
        Solicitacao solicitacaoCliente = new Solicitacao(sintomasPaciente, diagnostico, Requisicao.SOLICITAR_DIAGNOSTICO);
        
        Resposta respostaServidor = cliente.enviarConsulta(solicitacaoCliente);
        
        String mensagemRetornada = respostaServidor.getMensagem();
       
        JOptionPane.showMessageDialog(this, mensagemRetornada, "Resposta do servidor", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_efetuarDigAutoButtonActionPerformed

    private void dificuldadeRespirarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dificuldadeRespirarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dificuldadeRespirarActionPerformed

    private void dorCabecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dorCabecaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dorCabecaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClienteMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteMain().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chiadoPeito;
    private javax.swing.JCheckBox diarreia;
    private javax.swing.JCheckBox dificuldadeRespirar;
    private javax.swing.JCheckBox dorCabeca;
    private javax.swing.JCheckBox doresMusculares;
    private javax.swing.JToggleButton efetuarDigAutoButton;
    private javax.swing.JCheckBox enjoo;
    private javax.swing.JToggleButton enviarButton;
    private javax.swing.JToggleButton enviarCasosArmButton;
    private javax.swing.JCheckBox fadiga;
    private javax.swing.JCheckBox febre;
    private javax.swing.JCheckBox fraqueza;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JCheckBox perdaApetite;
    private javax.swing.JCheckBox tosses;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JCheckBox vomitos;
    // End of variables declaration//GEN-END:variables
}
