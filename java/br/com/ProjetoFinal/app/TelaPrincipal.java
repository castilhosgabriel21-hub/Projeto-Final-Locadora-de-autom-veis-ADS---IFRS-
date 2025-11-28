package br.com.ProjetoFinal.app;

import br.com.ProjetoFinal.app.dialogs.*;
import br.com.ProjetoFinal.dao.UsuarioService;
import br.com.ProjetoFinal.dao.VeiculoService;
import br.com.ProjetoFinal.dao.AluguelService;
import br.com.ProjetoFinal.model.Usuario;
import br.com.ProjetoFinal.model.Veiculo;
import br.com.ProjetoFinal.model.Aluguel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class TelaPrincipal extends JFrame {

    private JTable tabelaUsuarios = new JTable();
    private JTable tabelaVeiculos = new JTable();
    private JTable tabelaAlugueis = new JTable();

    private final UsuarioService usuarioService = new UsuarioService();
    private final VeiculoService veiculoService = new VeiculoService();
    private final AluguelService aluguelService = new AluguelService();

    private JTextField txtPesquisaUsuarios = new JTextField(15);
    private JTextField txtPesquisaVeiculos = new JTextField(15);
    private JTextField txtPesquisaAlugueis = new JTextField(15);

    public TelaPrincipal() {
        super("Sistema Locadora de Automóveis");
        setSize(950, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JTabbedPane abas = new JTabbedPane();
        abas.add("Usuários", criarPainelUsuarios());
        abas.add("Veículos", criarPainelVeiculos());
        abas.add("Aluguéis", criarPainelAlugueis());

        add(abas, BorderLayout.CENTER);

        refreshUsuarios();
        refreshVeiculos();
        refreshAlugueis();
    }

    private JPanel criarPainelUsuarios() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton add = new JButton("Adicionar");
        JButton edit = new JButton("Editar");
        JButton del = new JButton("Excluir");
        JButton atualizar = new JButton("Atualizar");

        add.addActionListener(e -> {
            UsuarioDialog dlg = new UsuarioDialog(this, null);
            dlg.setVisible(true);
            refreshUsuarios();
        });

        edit.addActionListener(e -> {
            int row = tabelaUsuarios.getSelectedRow();
            if (row < 0) return;
            Long id = ((Number) tabelaUsuarios.getValueAt(row, 0)).longValue();
            Usuario u = usuarioService.buscarPorId(id);
            UsuarioDialog dlg = new UsuarioDialog(this, u);
            dlg.setVisible(true);
            refreshUsuarios();
        });

        del.addActionListener(e -> {
            int row = tabelaUsuarios.getSelectedRow();
            if (row < 0) return;
            Long id = ((Number) tabelaUsuarios.getValueAt(row, 0)).longValue();
            if (JOptionPane.showConfirmDialog(this, "Excluir usuário?", "Confirma",
                    JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    usuarioService.excluir(id);
                    JOptionPane.showMessageDialog(this, "Usuário excluído com sucesso.");
                    refreshUsuarios();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao excluir usuário. Ele pode estar vinculado a algum aluguel.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        atualizar.addActionListener(e -> {
            txtPesquisaUsuarios.setText("");
            refreshUsuarios();
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Pesquisar:"));
        top.add(txtPesquisaUsuarios);
        top.add(add);
        top.add(edit);
        top.add(del);
        top.add(atualizar);

        txtPesquisaUsuarios.addActionListener(e -> pesquisarUsuarios());

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabelaUsuarios), BorderLayout.CENTER);

        return panel;
    }

    private void pesquisarUsuarios() {
        String termo = txtPesquisaUsuarios.getText().toLowerCase();
        List<Usuario> lista = usuarioService.listarTodos()
                .stream()
                .filter(u -> u.getNome().toLowerCase().contains(termo) ||
                        u.getEmail().toLowerCase().contains(termo) ||
                        u.getTelefone().toLowerCase().contains(termo))
                .collect(Collectors.toList());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Nome", "Email", "Telefone"}, 0);
        for (Usuario u : lista) {
            model.addRow(new Object[]{u.getId(), u.getNome(), u.getEmail(), u.getTelefone()});
        }
        tabelaUsuarios.setModel(model);
    }

    private JPanel criarPainelVeiculos() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton add = new JButton("Adicionar");
        JButton edit = new JButton("Editar");
        JButton del = new JButton("Excluir");
        JButton atualizar = new JButton("Atualizar");

        add.addActionListener(e -> {
            VeiculoDialog dlg = new VeiculoDialog(this, null);
            dlg.setVisible(true);
            refreshVeiculos();
        });

        edit.addActionListener(e -> {
            int row = tabelaVeiculos.getSelectedRow();
            if (row < 0) return;
            Long id = ((Number) tabelaVeiculos.getValueAt(row, 0)).longValue();
            Veiculo v = veiculoService.buscarPorId(id);
            VeiculoDialog dlg = new VeiculoDialog(this, v);
            dlg.setVisible(true);
            refreshVeiculos();
        });

        del.addActionListener(e -> {
            int row = tabelaVeiculos.getSelectedRow();
            if (row < 0) return;
            Long id = ((Number) tabelaVeiculos.getValueAt(row, 0)).longValue();
            if (JOptionPane.showConfirmDialog(this, "Excluir veículo?", "Confirma",
                    JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    veiculoService.excluir(id);
                    JOptionPane.showMessageDialog(this, "Veículo excluído com sucesso.");
                    refreshVeiculos();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao excluir veículo. Ele pode estar vinculado a algum aluguel.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        atualizar.addActionListener(e -> {
            txtPesquisaVeiculos.setText("");
            refreshVeiculos();
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Pesquisar:"));
        top.add(txtPesquisaVeiculos);
        top.add(add);
        top.add(edit);
        top.add(del);
        top.add(atualizar);

        txtPesquisaVeiculos.addActionListener(e -> pesquisarVeiculos());

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabelaVeiculos), BorderLayout.CENTER);

        return panel;
    }

    private void pesquisarVeiculos() {
        String termo = txtPesquisaVeiculos.getText().toLowerCase();
        List<Veiculo> lista = veiculoService.listarTodos()
                .stream()
                .filter(v -> v.getPlaca().toLowerCase().contains(termo) ||
                        v.getMarca().toLowerCase().contains(termo) ||
                        v.getModelo().toLowerCase().contains(termo) ||
                        v.getCor().toLowerCase().contains(termo))
                .collect(Collectors.toList());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Placa", "Marca", "Modelo", "Ano", "Cor"}, 0);
        for (Veiculo v : lista) {
            model.addRow(new Object[]{v.getId(), v.getPlaca(), v.getMarca(), v.getModelo(), v.getAno(), v.getCor()});
        }
        tabelaVeiculos.setModel(model);
    }

    private JPanel criarPainelAlugueis() {
        JPanel panel = new JPanel(new BorderLayout());

        JButton abrir = new JButton("Abrir Aluguel");
        JButton fechar = new JButton("Fechar Aluguel");
        JButton del = new JButton("Excluir");
        JButton atualizar = new JButton("Atualizar");

        abrir.addActionListener(e -> {
            AluguelDialog dlg = new AluguelDialog(this, true);
            dlg.setVisible(true);
            refreshAlugueis();
        });

        fechar.addActionListener(e -> {
            int row = tabelaAlugueis.getSelectedRow();
            if (row < 0) return;
            int id = ((Number) tabelaAlugueis.getValueAt(row, 0)).intValue();
            Aluguel a = aluguelService.buscarPorId(id);
            FecharAluguelDialog dlg = new FecharAluguelDialog(this, a);
            dlg.setVisible(true);
            refreshAlugueis();
        });

        del.addActionListener(e -> {
            int row = tabelaAlugueis.getSelectedRow();
            if (row < 0) return;
            int id = ((Number) tabelaAlugueis.getValueAt(row, 0)).intValue();
            if (JOptionPane.showConfirmDialog(this, "Excluir aluguel?", "Confirma",
                    JOptionPane.YES_NO_OPTION) == 0) {
                try {
                    aluguelService.excluir(id);
                    JOptionPane.showMessageDialog(this, "Aluguel excluído com sucesso.");
                    refreshAlugueis();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this,
                            "Erro ao excluir aluguel.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        atualizar.addActionListener(e -> {
            txtPesquisaAlugueis.setText("");
            refreshAlugueis();
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Pesquisar:"));
        top.add(txtPesquisaAlugueis);
        top.add(abrir);
        top.add(fechar);
        top.add(del);
        top.add(atualizar);

        txtPesquisaAlugueis.addActionListener(e -> pesquisarAlugueis());

        panel.add(top, BorderLayout.NORTH);
        panel.add(new JScrollPane(tabelaAlugueis), BorderLayout.CENTER);

        return panel;
    }

    private void pesquisarAlugueis() {
        String termo = txtPesquisaAlugueis.getText().toLowerCase();
        List<Aluguel> lista = aluguelService.listarTodos()
                .stream()
                .filter(a -> (a.getUsuario() != null && a.getUsuario().getNome().toLowerCase().contains(termo)) ||
                        (a.getVeiculo() != null && a.getVeiculo().getPlaca().toLowerCase().contains(termo)) ||
                        a.getStatus().toLowerCase().contains(termo))
                .collect(Collectors.toList());

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID", "Cliente", "Veículo", "Início", "Fim", "KmIni", "KmFim", "Status"}, 0);
        for (Aluguel a : lista) {
            model.addRow(new Object[]{
                    a.getId(),
                    a.getUsuario() != null ? a.getUsuario().getNome() : "",
                    a.getVeiculo() != null ? a.getVeiculo().getPlaca() : "",
                    a.getDataInicio(),
                    a.getDataFim(),
                    a.getQuilometragemInicial(),
                    a.getQuilometragemFinal(),
                    a.getStatus()
            });
        }
        tabelaAlugueis.setModel(model);
    }

    public void refreshUsuarios() { pesquisarUsuarios(); }
    public void refreshVeiculos() { pesquisarVeiculos(); }
    public void refreshAlugueis() { pesquisarAlugueis(); }

    // --------------------- MAIN ---------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }
}
