package br.com.ProjetoFinal.ui.table;
import br.com.ProjetoFinal.model.Veiculo;
public class VeiculoTableModel extends ObservableTableModel<Veiculo>{
  private final String[] cols=new String[]{"ID","Placa","Marca","Modelo","Cor","Ano"};
  @Override
  public int getColumnCount(){return cols.length;}
  @Override
  public Object getValueAt(int rowIndex,int columnIndex){
    Veiculo v=getItem(rowIndex);
    return switch(columnIndex){
      case 0->v.getId();
      case 1->v.getPlaca();
      case 2->v.getMarca();
      case 3->v.getModelo();
      case 4 -> v.getCor().toString();
      case 5->v.getAno();
      default->"";
    };
  }
  @Override
  public String getColumnName(int column){return cols[column];}
}
