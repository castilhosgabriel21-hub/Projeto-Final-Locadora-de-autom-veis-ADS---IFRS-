package br.com.ProjetoFinal.app.dialogs;

import br.com.ProjetoFinal.model.Veiculo;
import br.com.ProjetoFinal.dao.VeiculoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VeiculoDialog extends JDialog {
    private final VeiculoService service = new VeiculoService();
    private Veiculo veiculo;
    private boolean saved = false;

    private final JTextField txtPlaca = new JTextField(10);
    private final JTextField txtMarca = new JTextField(15);
    private final JTextField txtModelo = new JTextField(15);
    private final JTextField txtAno = new JTextField(4);
    private final JTextField txtCor = new JTextField(10);
    private final JPanel painelCor = new JPanel(); // painel mostrando a cor

    public VeiculoDialog(Window owner, Veiculo v){
        super(owner, v == null ? "Novo Veículo" : "Editar Veículo", ModalityType.APPLICATION_MODAL);
        this.veiculo = v;
        setSize(420, 250);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        init();
    }

    private void init(){
        if(veiculo != null){
            txtPlaca.setText(veiculo.getPlaca());
            txtMarca.setText(veiculo.getMarca());
            txtModelo.setText(veiculo.getModelo());
            txtAno.setText(String.valueOf(veiculo.getAno()));
            txtCor.setText(veiculo.getCor());
        }

        JPanel p = new JPanel(new GridLayout(6,2,6,6));
        p.add(new JLabel("Placa")); p.add(txtPlaca);
        p.add(new JLabel("Marca")); p.add(txtMarca);
        p.add(new JLabel("Modelo")); p.add(txtModelo);
        p.add(new JLabel("Ano")); p.add(txtAno);
        p.add(new JLabel("Cor"));
        JPanel painelCorInput = new JPanel(new BorderLayout());
        painelCor.setPreferredSize(new Dimension(50, 20));
        painelCorInput.add(txtCor, BorderLayout.CENTER);
        painelCorInput.add(painelCor, BorderLayout.EAST);
        p.add(painelCorInput);

        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancelar");
        btnOk.addActionListener(e->onSave());
        btnCancel.addActionListener(e->dispose());
        p.add(btnOk); p.add(btnCancel);

        add(p);

        // Atualiza o painel de cor enquanto digita
        txtCor.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                atualizarPainelCor();
            }
        });

        atualizarPainelCor(); // inicializa cor ao abrir
    }

    private void atualizarPainelCor(){
        String corDigitada = txtCor.getText().trim();
        Color cor = Color.GRAY; // cor padrão

        try {
            if(corDigitada.startsWith("#")) {
                cor = Color.decode(corDigitada); // para #RRGGBB
            } else {
                switch(corDigitada.toLowerCase()) {
                    case "vermelho": cor = Color.RED; break;
                    case "azul": cor = Color.BLUE; break;
                    case "verde": cor = Color.GREEN; break;
                    case "preto": cor = Color.BLACK; break;
                    case "branco": cor = Color.WHITE; break;
                    case "amarelo": cor = Color.YELLOW; break;
                    case "cinza": cor = Color.GRAY; break;
                }
            }
        } catch(NumberFormatException ex) {
            cor = Color.GRAY; // valor padrão se inválido
        }

        painelCor.setBackground(cor);
        painelCor.repaint();
    }

    private void onSave(){
        if(veiculo == null) veiculo = new Veiculo();
        veiculo.setPlaca(txtPlaca.getText().trim());
        veiculo.setMarca(txtMarca.getText().trim());
        veiculo.setModelo(txtModelo.getText().trim());
        try {
            veiculo.setAno(Integer.parseInt(txtAno.getText().trim()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,"Ano inválido.");
            return;
        }

        veiculo.setCor(txtCor.getText().trim()); // salva o texto mesmo

        service.salvar(veiculo);
        saved = true;
        dispose();
    }

    public boolean isSaved(){ return saved; }
    public boolean isConfirmado(){ return saved; }
    public Veiculo getVeiculo(){ return veiculo; }
}
