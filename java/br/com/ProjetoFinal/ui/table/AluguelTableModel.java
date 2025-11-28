package br.com.ProjetoFinal.ui.table;

import br.com.ProjetoFinal.model.Aluguel;
import java.time.format.DateTimeFormatter;

public class AluguelTableModel extends ObservableTableModel<Aluguel> {

    private final String[] cols = {
            "ID","Cliente","Veículo","Início","Fim","Km Inicial","Km Final","Status"
    };

    private final DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public int getColumnCount() {
        return cols.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Aluguel a = getItem(rowIndex);

        return switch (columnIndex) {
            case 0 -> a.getId();
            case 1 -> a.getUsuario()  != null ? a.getUsuario().getNome()      : "";
            case 2 -> a.getVeiculo()  != null ? a.getVeiculo().getPlaca()     : "";
            case 3 -> a.getDataInicio()!= null ? a.getDataInicio().format(fmt): "";
            case 4 -> a.getDataFim()  != null ? a.getDataFim().format(fmt)    : "";

            // 🔥 Como são INT, não pode comparar com null
            // Exibirá vazio se o valor for 0 (significa não preenchido ainda)
            case 5 -> a.getQuilometragemInicial() > 0 ? a.getQuilometragemInicial() : "";
            case 6 -> a.getQuilometragemFinal()   > 0 ? a.getQuilometragemFinal()   : "";

            case 7 -> a.getStatus() != null ? a.getStatus() : "";
            default -> "";
        };
    }

    @Override
    public String getColumnName(int column) {
        return cols[column];
    }
}
