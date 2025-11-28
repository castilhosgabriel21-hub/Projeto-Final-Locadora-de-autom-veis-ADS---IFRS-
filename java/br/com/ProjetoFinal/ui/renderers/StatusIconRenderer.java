package br.com.ProjetoFinal.ui.renderers;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.Component;
import javax.swing.JTable;
public class StatusIconRenderer extends DefaultTableCellRenderer{
  private final Icon iconAberto=new ImageIcon(getClass().getResource("/icons/open.png"));
  private final Icon iconFechado=new ImageIcon(getClass().getResource("/icons/closed.png"));
  private final Icon iconAtraso=new ImageIcon(getClass().getResource("/icons/late.png"));
  @Override
  public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected,boolean hasFocus,int row,int column){
    super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
    setText(null);
    setHorizontalAlignment(CENTER);
    setIcon(switch(String.valueOf(value).toUpperCase()){
      case "ABERTO"->iconAberto;
      case "FECHADO"->iconFechado;
      case "ATRASADO"->iconAtraso;
      default->null;
    });
    return this;
  }
}
