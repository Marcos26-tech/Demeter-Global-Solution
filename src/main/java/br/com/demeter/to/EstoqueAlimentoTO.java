package br.com.demeter.to;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class EstoqueAlimentoTO {

    private int quantidadeAlimento;
    private int idAlimento;
    private String nomeAlimento;
    private Date dataValidadeAlimento;
    private int idEstoque;

    public EstoqueAlimentoTO() {
    }

    public EstoqueAlimentoTO(int quantidadeAlimento, int idAlimento, String nomeAlimento, Date dataValidadeAlimento) {
        this.quantidadeAlimento = quantidadeAlimento;
        this.idAlimento = idAlimento;
        this.nomeAlimento = nomeAlimento;
        this.dataValidadeAlimento = dataValidadeAlimento;
    }

    public EstoqueAlimentoTO(int quantidadeAlimento, int idAlimento, String nomeAlimento, Date dataValidadeAlimento, int idEstoque) {
        this.quantidadeAlimento = quantidadeAlimento;
        this.idAlimento = idAlimento;
        this.nomeAlimento = nomeAlimento;
        this.dataValidadeAlimento = dataValidadeAlimento;
        this.idEstoque = idEstoque;
    }

    public EstoqueAlimentoTO(int quantidadeAlimento) {
        this.quantidadeAlimento = quantidadeAlimento;
    }

    public int getQuantidadeAlimento() {
        return quantidadeAlimento;
    }

    public void setQuantidadeAlimento(int quantidadeAlimento) {
        this.quantidadeAlimento = quantidadeAlimento;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public String getNomeAlimento() {
        return nomeAlimento;
    }

    public void setNomeAlimento(String nomeAlimento) {
        this.nomeAlimento = nomeAlimento;
    }

    public Date getDataValidadeAlimento() {
        return dataValidadeAlimento;
    }

    public void setDataValidadeAlimento(Date dataValidadeAlimento) {
        this.dataValidadeAlimento = dataValidadeAlimento;
    }

    public int getIdEstoque() {
        return idEstoque;
    }

    public void setIdEstoque(int idEstoque) {
        this.idEstoque = idEstoque;
    }

    @Override
    public String toString() {
        return "EstoqueAlimento{" +
                ", quantidadeAlimento=" + quantidadeAlimento +
                ", idAlimento=" + idAlimento +
                ", nomeAlimento='" + nomeAlimento + '\'' +
                ", dataValidadeAlimento=" + dataValidadeAlimento +
                '}';
    }
}
