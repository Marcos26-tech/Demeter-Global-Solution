package br.com.demeter.to;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class AlimentoReservadoTO {

    private int idEstoqueAlimento;
    private int idReserva;
    private int idAlimento;
    private int idUsuarioReserva;
    private int quantidadeAlimentoReservado;
    private Date dataReservaAlimento;

    public AlimentoReservadoTO() {
    }

    public AlimentoReservadoTO(int idEstoqueAlimento, int idReserva, int idAlimento, int idUsuarioReserva, int quantidadeAlimentoReservado, Date dataReservaAlimento) {
        this.idEstoqueAlimento = idEstoqueAlimento;
        this.idReserva = idReserva;
        this.idAlimento = idAlimento;
        this.idUsuarioReserva = idUsuarioReserva;
        this.quantidadeAlimentoReservado = quantidadeAlimentoReservado;
        this.dataReservaAlimento = dataReservaAlimento;
    }

    public int getIdEstoqueAlimento() {
        return idEstoqueAlimento;
    }

    public void setIdEstoqueAlimento(int idEstoqueAlimento) {
        this.idEstoqueAlimento = idEstoqueAlimento;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getIdAlimento() {
        return idAlimento;
    }

    public void setIdAlimento(int idAlimento) {
        this.idAlimento = idAlimento;
    }

    public int getIdUsuarioReserva() {
        return idUsuarioReserva;
    }

    public void setIdUsuarioReserva(int idUsuarioReserva) {
        this.idUsuarioReserva = idUsuarioReserva;
    }

    public int getQuantidadeAlimentoReservado() {
        return quantidadeAlimentoReservado;
    }

    public void setQuantidadeAlimentoReservado(int quantidadeAlimentoReservado) {
        this.quantidadeAlimentoReservado = quantidadeAlimentoReservado;
    }

    public Date getDataReservaAlimento() {
        return dataReservaAlimento;
    }

    public void setDataReservaAlimento(Date dataReservaAlimento) {
        this.dataReservaAlimento = dataReservaAlimento;
    }

    @Override
    public String toString() {
        return "AlimentoReservadoTO{" +
                "idEstoqueAlimento=" + idEstoqueAlimento +
                ", idReserva=" + idReserva +
                ", idAlimento=" + idAlimento +
                ", idUsuarioReserva=" + idUsuarioReserva +
                ", quantidadeAlimentoReservado=" + quantidadeAlimentoReservado +
                ", dataReservaAlimento=" + dataReservaAlimento +
                '}';
    }
}
