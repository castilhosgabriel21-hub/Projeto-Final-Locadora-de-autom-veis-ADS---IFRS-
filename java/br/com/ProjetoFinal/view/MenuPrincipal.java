package br.com.ProjetoFinal.view;

public class MenuPrincipal extends javax.swing.JFrame {

    public MenuPrincipal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        btnCadastrar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema Locadora");

        btnCadastrar.setText("Cadastrar Veículo");
        btnCadastrar.addActionListener(evt -> new TelaCadastroVeiculo().setVisible(true));

        btnListar.setText("Listar Veículos");
        btnListar.addActionListener(evt -> new TelaListarVeiculo().setVisible(true));

        titulo.setFont(new java.awt.Font("Segoe UI", 1, 20));
        titulo.setText("Menu Principal");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                        .addComponent(titulo)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                        .addComponent(btnCadastrar, 200, 200, 200)
                                        .addComponent(btnListar, 200, 200, 200))
                                .addGap(20))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(20)
                        .addComponent(titulo)
                        .addGap(30)
                        .addComponent(btnCadastrar)
                        .addGap(20)
                        .addComponent(btnListar)
                        .addGap(20)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnListar;
    private javax.swing.JLabel titulo;
}
