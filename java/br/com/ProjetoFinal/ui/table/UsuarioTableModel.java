package br.com.ProjetoFinal.ui.table;

import br.com.ProjetoFinal.model.Usuario;

public class UsuarioTableModel extends ObservableTableModel<Usuario> {
    private final String[] cols = new String[] {"ID","Nome","Email","Telefone"};
    @Override public int getColumnCount(){return cols.length;}
    @Override public Object getValueAt(int rowIndex,int columnIndex){
        Usuario u = getItem(rowIndex);
        return switch(columnIndex){
            case 0->u.getId();
            case 1->u.getNome();
            case 2->u.getEmail();
            case 3->u.getTelefone();
            default->"";
        };
    }
    @Override public String getColumnName(int column){return cols[column];}
}
