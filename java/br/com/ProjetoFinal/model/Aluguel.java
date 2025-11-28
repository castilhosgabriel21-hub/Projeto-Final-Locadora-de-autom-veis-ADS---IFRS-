package br.com.ProjetoFinal.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "aluguel")
public class Aluguel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String status;
    private int quilometragemInicial;
    private int quilometragemFinal;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;

    // getters e setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public int getQuilometragemInicial() { return quilometragemInicial; }
    public void setQuilometragemInicial(int quilometragemInicial) { this.quilometragemInicial = quilometragemInicial; }
    public int getQuilometragemFinal() { return quilometragemFinal; }
    public void setQuilometragemFinal(int quilometragemFinal) { this.quilometragemFinal = quilometragemFinal; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Veiculo getVeiculo() { return veiculo; }
    public void setVeiculo(Veiculo veiculo) { this.veiculo = veiculo; }
}
