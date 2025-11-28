package br.com.ProjetoFinal.view;

import br.com.ProjetoFinal.dao.VeiculoService;
import br.com.ProjetoFinal.model.Veiculo;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class TelaListarVeiculo extends javax.swing.JFrame {

    public TelaListarVeiculo() {
        initComponents();
        carregarTabela();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        tabela = new javax.swing.JTable();
        scroll = new javax.swing.JScrollPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Lista de Veículos");

        tabela.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {},
                new String[] { "ID", "Placa", "Marca", "Modelo", "Ano", "Cor" }
        ));
        scroll.setViewportView(tabela);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(scroll, 500, 500, 500)
                        .addGap(10)
        );

        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGap(10)
                        .addComponent(scroll, 300, 300, 300)
                        .addGap(10)
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void carregarTabela() {
        VeiculoService service = new VeiculoService();
        List<Veiculo> lista = service.listarTodos();

        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);

        for (Veiculo v : lista) {
            model.addRow(new Object[]{
                    v.getId(),
                    v.getPlaca(),
                    v.getMarca(),
                    v.getModelo(),
                    v.getAno(),
                    v.getCor()
            });
        }
    }

    private javax.swing.JTable tabela;
    private javax.swing.JScrollPane scroll;
}
