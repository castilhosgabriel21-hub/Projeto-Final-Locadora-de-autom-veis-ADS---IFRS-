package br.com.ProjetoFinal.view;

import br.com.ProjetoFinal.model.Veiculo;
import br.com.ProjetoFinal.dao.VeiculoService;
import javax.swing.JOptionPane;

public class TelaCadastroVeiculo extends javax.swing.JFrame {

    public TelaCadastroVeiculo() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        lblPlaca = new javax.swing.JLabel();
        lblMarca = new javax.swing.JLabel();
        lblModelo = new javax.swing.JLabel();
        lblAno = new javax.swing.JLabel();
        lblCor = new javax.swing.JLabel();

        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();

        btnSalvar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cadastro de Veículos");

        lblPlaca.setText("Placa:");
        lblMarca.setText("Marca:");
        lblModelo.setText("Modelo:");
        lblAno.setText("Ano:");
        lblCor.setText("Cor:");

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(evt -> salvarVeiculo());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblPlaca)
                                .addComponent(lblMarca)
                                .addComponent(lblModelo)
                                .addComponent(lblAno)
                                .addComponent(lblCor))
                        .addGap(10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtPlaca, 200, 200, 200)
                                .addComponent(txtMarca)
                                .addComponent(txtModelo)
                                .addComponent(txtAno)
                                .addComponent(txtCor)
                                .addComponent(btnSalvar))
                        .addGap(20)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblPlaca)
                                .addComponent(txtPlaca, 30, 30, 30))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblMarca)
                                .addComponent(txtMarca, 30, 30, 30))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblModelo)
                                .addComponent(txtModelo, 30, 30, 30))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblAno)
                                .addComponent(txtAno, 30, 30, 30))
                        .addGroup(layout.createParallelGroup()
                                .addComponent(lblCor)
                                .addComponent(txtCor, 30, 30, 30))
                        .addGap(20)
                        .addComponent(btnSalvar)
                        .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void salvarVeiculo() {
        try {
            Veiculo v = new Veiculo();
            v.setPlaca(txtPlaca.getText());
            v.setMarca(txtMarca.getText());
            v.setModelo(txtModelo.getText());
            v.setAno(Integer.parseInt(txtAno.getText()));
            v.setCor(txtCor.getText());

            VeiculoService service = new VeiculoService();
            service.salvar(v);

            JOptionPane.showMessageDialog(this, "Veículo salvo com sucesso!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar: " + e.getMessage());
        }
    }

    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel lblPlaca, lblMarca, lblModelo, lblAno, lblCor;
    private javax.swing.JTextField txtPlaca, txtMarca, txtModelo, txtAno, txtCor;
}
