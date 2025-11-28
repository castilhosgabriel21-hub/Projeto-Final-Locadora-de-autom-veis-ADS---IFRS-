package br.com.ProjetoFinal.app.dialogs;

import br.com.ProjetoFinal.model.Usuario;
import br.com.ProjetoFinal.dao.UsuarioService;
import br.com.ProjetoFinal.ui.validators.Validators;

import javax.swing.*;
import java.awt.*;

public class UsuarioDialog extends JDialog {
    private final JTextField txtNome = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JTextField txtTelefone = new JTextField(14);
    private final JButton btnSalvar = new JButton("Salvar");
    private final JButton btnCancelar = new JButton("Cancelar");
    private boolean saved = false;
    private Usuario usuario;
    private final UsuarioService service = new UsuarioService();

    public UsuarioDialog(Window owner, Usuario u){
        super(owner, "Cadastro de Cliente", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(420,220);
        setLocationRelativeTo(owner);
        init(u);
    }

    private void init(Usuario u){
        if(u!=null){ this.usuario = u; txtNome.setText(u.getNome()); txtEmail.setText(u.getEmail()); txtTelefone.setText(u.getTelefone());}
        JPanel p = new JPanel(new GridLayout(4,2,6,6));
        p.add(new JLabel("Nome")); p.add(txtNome);
        p.add(new JLabel("Email")); p.add(txtEmail);
        p.add(new JLabel("Telefone")); p.add(txtTelefone);

        btnSalvar.addActionListener(e->onSave());
        btnCancelar.addActionListener(e->dispose());

        p.add(btnSalvar); p.add(btnCancelar);
        add(p);
    }

    private void onSave(){
        String nome = txtNome.getText().trim();
        String email = txtEmail.getText().trim();
        String tel = txtTelefone.getText().trim();

        if(nome.isEmpty()){
            JOptionPane.showMessageDialog(this,"Nome obrigatório","Validação",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!Validators.isEmail(email)){
            JOptionPane.showMessageDialog(this,"Email inválido","Validação",JOptionPane.WARNING_MESSAGE);
            return;
        }
        if(!Validators.isTelefone(tel)){
            JOptionPane.showMessageDialog(this,"Telefone inválido. Use apenas números com DDI opcional.","Validação",JOptionPane.WARNING_MESSAGE);
            return;
        }

        if(usuario==null) usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuario.setTelefone(tel);

        service.salvar(usuario);
        saved = true;
        dispose();
    }

    public boolean isSaved(){ return saved; }
    public boolean isConfirmado(){ return saved; } // para compatibilidade com chamadas antigas
    public Usuario getUsuario(){ return usuario; }
}
