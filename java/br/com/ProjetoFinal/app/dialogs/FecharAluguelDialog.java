package br.com.ProjetoFinal.app.dialogs;

import br.com.ProjetoFinal.dao.AluguelService;
import br.com.ProjetoFinal.model.Aluguel;

import javax.swing.*;
import java.awt.*;

public class FecharAluguelDialog extends JDialog {
    private final Aluguel aluguel;
    private final AluguelService service = new AluguelService();
    private boolean confirmado = false;
    private final JTextField txtKmFinal = new JTextField(8);

    public FecharAluguelDialog(Window owner, Aluguel aluguel) {
        super(owner, "Fechar Aluguel", ModalityType.APPLICATION_MODAL);
        this.aluguel = aluguel;
        setSize(300,150);
        setLocationRelativeTo(owner);
        setLayout(new GridLayout(3,2,5,5));

        add(new JLabel("Km Inicial:"));
        add(new JLabel(String.valueOf(aluguel.getQuilometragemInicial())));
        add(new JLabel("Km Final:"));
        add(txtKmFinal);
        JButton btnConfirm = new JButton("Confirmar");
        JButton btnCancel = new JButton("Cancelar");
        add(btnConfirm);
        add(btnCancel);

        btnConfirm.addActionListener(e -> onConfirm());
        btnCancel.addActionListener(e -> dispose());
    }

    private void onConfirm() {
        try {
            int kmFinal = Integer.parseInt(txtKmFinal.getText().trim());
            if(kmFinal < aluguel.getQuilometragemInicial()){
                JOptionPane.showMessageDialog(this, "Km Final não pode ser menor que Km Inicial.");
                return;
            }
            aluguel.setQuilometragemFinal(kmFinal);
            aluguel.setStatus("FECHADO");
            service.atualizar(aluguel);

            // Mostrar resumo do aluguel
            String resumo = "Resumo do Aluguel:\n"
                    + "Cliente: " + aluguel.getUsuario().getNome() + "\n"
                    + "Veículo: " + aluguel.getVeiculo().getModelo() + "\n"
                    + "KM Inicial: " + aluguel.getQuilometragemInicial() + "\n"
                    + "KM Final: " + aluguel.getQuilometragemFinal() + "\n"
                    + "Data Início: " + aluguel.getDataInicio() + "\n"
                    + "Data Fim: " + aluguel.getDataFim() + "\n"
                    + "Status: " + aluguel.getStatus();

            JOptionPane.showMessageDialog(this, resumo, "Resumo do Aluguel", JOptionPane.INFORMATION_MESSAGE);

            confirmado = true;
            dispose();
        } catch(NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Informe um valor numérico válido para Km Final.");
        }
    }

    public boolean isConfirmado() { return confirmado; }
}
