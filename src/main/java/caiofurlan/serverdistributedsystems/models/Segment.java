package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Segment {
    @JsonProperty("ponto_origem")
    private Point pontoOrigem;
    @JsonProperty("ponto_destino")
    private Point pontoDestino;
    String direcao;
    int distancia;
    String obs;
    int id;

    public Segment() {
    }

    public Segment(Point pontoOrigem, Point pontoDestino, String direcao, int distancia, String obs) {
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs == null || obs.isEmpty() ? null : obs;
    }

    public Segment(Point pontoOrigem, Point pontoDestino, String direcao, int distancia, String obs, int id) {
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs == null || obs.isEmpty() ? null : obs;
        this.id = id;
    }

    public Point getPontoOrigem() {
        return pontoOrigem;
    }

    public void setPontoOrigem(Point pontoOrigem) {
        this.pontoOrigem = pontoOrigem;
    }

    public Point getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(Point pontoDestino) {
        this.pontoDestino = pontoDestino;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public int getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
