package br.com.ProjetoFinal.ui.table;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;
public abstract class ObservableTableModel<T> extends AbstractTableModel{
  protected final List<T> items=new ArrayList<>();
  public void setItems(List<T> list){
    items.clear();
    if(list!=null)items.addAll(list);
    fireTableDataChanged();
  }
  public void addItem(T t){
    items.add(t);
    int row=items.size()-1;
    fireTableRowsInserted(row,row);
  }
  public void updateItem(int index,T t){
    items.set(index,t);
    fireTableRowsUpdated(index,index);
  }
  public void removeItem(int index){
    items.remove(index);
    fireTableRowsDeleted(index,index);
  }
  public T getItem(int row){return items.get(row);}
  @Override
  public int getRowCount(){return items.size();}
}
