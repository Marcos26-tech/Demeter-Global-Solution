package br.com.demeter.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AlimentoReservadoTO {

    private int quantidadeAlimentoReservado;

    public AlimentoReservadoTO(int quantidadeAlimentoReservado) {
        this.quantidadeAlimentoReservado = quantidadeAlimentoReservado;
    }

    public AlimentoReservadoTO() {
    }

    public int getQuantidadeAlimentoReservado() {
        return quantidadeAlimentoReservado;
    }

    public void setQuantidadeAlimentoReservado(int quantidadeAlimentoReservado) {
        this.quantidadeAlimentoReservado = quantidadeAlimentoReservado;
    }

    @Override
    public String toString() {
        return "AlimentoReservado{" +
                "quantidadeAlimentoReservado=" + quantidadeAlimentoReservado +
                '}';
    }
}
