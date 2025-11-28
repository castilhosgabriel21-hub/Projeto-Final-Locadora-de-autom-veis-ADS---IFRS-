package br.com.ProjetoFinal.app.dialogs;

import br.com.ProjetoFinal.dao.AluguelService;
import br.com.ProjetoFinal.dao.UsuarioService;
import br.com.ProjetoFinal.dao.VeiculoService;
import br.com.ProjetoFinal.model.Aluguel;
import br.com.ProjetoFinal.model.Usuario;
import br.com.ProjetoFinal.model.Veiculo;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class AluguelDialog extends JDialog {

    private final JComboBox<Usuario> cbUsuario = new JComboBox<>();
    private final JComboBox<Veiculo> cbVeiculo = new JComboBox<>();
    private final JTextField txtDataInicio = new JTextField(10);
    private final JTextField txtDataFim = new JTextField(10);
    private final JTextField txtKmInicial = new JTextField(8);
    private final JComboBox<String> cbStatus = new JComboBox<>(new String[]{"ABERTO","FECHADO"});
    private final JButton btnSalvar = new JButton("Abrir Aluguel");
    private final JButton btnCancelar = new JButton("Cancelar");

    private final AluguelService aluguelService = new AluguelService();
    private Aluguel aluguel;
    private boolean saved = false;

    // Construtor para criar um novo aluguel
    public AluguelDialog(Window owner, boolean modal) {
        super(owner);
        setModal(modal);
        setTitle("Gerenciar Aluguéis");
        setSize(600, 400);
        setLocationRelativeTo(owner);
        init();
    }

    // Novo construtor para editar um aluguel existente
    public AluguelDialog(Window owner, Aluguel aluguel) {
        this(owner, true); // chama o construtor existente
        this.aluguel = aluguel;

        if (aluguel != null) {
            cbUsuario.setSelectedItem(aluguel.getUsuario());
            cbVeiculo.setSelectedItem(aluguel.getVeiculo());
            txtDataInicio.setText(aluguel.getDataInicio().toString());
            txtDataFim.setText(aluguel.getDataFim().toString());
            txtKmInicial.setText(String.valueOf(aluguel.getQuilometragemInicial()));
            cbStatus.setSelectedItem(aluguel.getStatus());
            btnSalvar.setText("Salvar Alterações");
        }
    }

    private void init() {
        UsuarioService us = new UsuarioService();
        VeiculoService vs = new VeiculoService();

        List<Usuario> usuarios = us.listarTodos();
        List<Veiculo> veiculos = vs.listarTodos();

        usuarios.forEach(cbUsuario::addItem);
        veiculos.forEach(cbVeiculo::addItem);

        if (aluguel == null) { // Somente para novo aluguel
            txtDataInicio.setText(LocalDate.now().toString());
            txtDataFim.setText(LocalDate.now().toString());
            cbStatus.setSelectedItem("ABERTO");
        }

        JPanel p = new JPanel(new GridLayout(7,2,6,6));
        p.add(new JLabel("Cliente")); p.add(cbUsuario);
        p.add(new JLabel("Veículo")); p.add(cbVeiculo);
        p.add(new JLabel("Data Início")); p.add(txtDataInicio);
        p.add(new JLabel("Data Fim")); p.add(txtDataFim);
        p.add(new JLabel("Km Inicial")); p.add(txtKmInicial);
        p.add(new JLabel("Status")); p.add(cbStatus);

        btnSalvar.addActionListener(e -> onSave());
        btnCancelar.addActionListener(e -> dispose());

        p.add(btnSalvar);
        p.add(btnCancelar);
        add(p);
    }

    private void onSave() {
        try {
            Usuario u = (Usuario) cbUsuario.getSelectedItem();
            Veiculo v = (Veiculo) cbVeiculo.getSelectedItem();

            LocalDate di = LocalDate.parse(txtDataInicio.getText().trim());
            LocalDate df = LocalDate.parse(txtDataFim.getText().trim());
            int kmIni = Integer.parseInt(txtKmInicial.getText().trim());
            String status = (String) cbStatus.getSelectedItem();

            if (aluguel == null) {
                aluguel = new Aluguel();
            }

            aluguel.setUsuario(u);
            aluguel.setVeiculo(v);
            aluguel.setDataInicio(di);
            aluguel.setDataFim(df);
            aluguel.setQuilometragemInicial(kmIni);
            aluguel.setStatus(status);

            aluguelService.salvar(aluguel);
            saved = true;
            dispose();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this,"Erro: "+ex.getMessage());
        }
    }

    public boolean isSaved() { return saved; }
    public Aluguel getAluguel() { return aluguel; }
}
