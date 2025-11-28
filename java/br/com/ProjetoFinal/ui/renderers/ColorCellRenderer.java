package br.com.ProjetoFinal.ui.renderers;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import javax.swing.JTable;

public class ColorCellRenderer extends DefaultTableCellRenderer {
    private final Map<String,Color> palette;

    public ColorCellRenderer(Map<String,Color> palette){
        this.palette = palette;
        setOpaque(true);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        Color bg = table.getBackground();
        String name = "";

        if (value instanceof Integer iARGB) {
            bg = new Color(iARGB, true);
            name = String.format("#%06X", (iARGB & 0xFFFFFF));
        } else if (value instanceof String s) {
            name = s;
            Color p = palette.getOrDefault(s.toLowerCase(), null);
            if (p != null) bg = p;
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(bg);
            // escolhe cor do texto legível
            setForeground(getBrightness(bg) < 130 ? Color.WHITE : Color.BLACK);
        }

        setText(name != null ? name : "");
        setHorizontalAlignment(LEFT);
        return this;
    }

    private int getBrightness(Color c){
        return (int)((c.getRed()*0.299) + (c.getGreen()*0.587) + (c.getBlue()*0.114));
    }
}
