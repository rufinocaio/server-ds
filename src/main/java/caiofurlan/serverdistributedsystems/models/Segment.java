package caiofurlan.serverdistributedsystems.models;

import caiofurlan.serverdistributedsystems.system.utilities.JacksonViews;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;

public class Segment {
    @JsonView(JacksonViews.Public.class)
    @JsonProperty("ponto_origem")
    private Point pontoOrigem;
    @JsonView(JacksonViews.Public.class)
    @JsonProperty("ponto_destino")
    private Point pontoDestino;
    @JsonView(JacksonViews.Public.class)
    @JsonProperty("direcao")
    private String direcao;
    @JsonView(JacksonViews.Public.class)
    @JsonProperty("distancia")
    private int distancia;
    @JsonView(JacksonViews.Private.class)
    @JsonProperty("bloqueado")
    private boolean bloqueado;
    @JsonView(JacksonViews.Public.class)
    @JsonProperty("obs")
    private String obs;
    @JsonView(JacksonViews.Private.class)
    @JsonProperty("id")
    private int id;

    public Segment() {
    }

    public Segment(Point ponto_origem, Point ponto_destino, String direcao, int distancia, boolean bloqueado, String obs) {
        this.pontoOrigem = ponto_origem;
        this.pontoDestino = ponto_destino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
        this.bloqueado = bloqueado;
    }

    public Segment(Point pontoOrigem, Point pontoDestino, String direcao, int distancia, boolean bloqueado, String obs, int id) {
        this.pontoOrigem = pontoOrigem;
        this.pontoDestino = pontoDestino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.bloqueado = bloqueado;
        this.obs = obs == null || obs.isEmpty() ? null : obs;
        this.id = id;
    }

    public Point getPontoOrigem() {
        return pontoOrigem;
    }

    public void setPontoOrigem(Point ponto_origem) {
        this.pontoOrigem = ponto_origem;
    }

    public Point getPontoDestino() {
        return pontoDestino;
    }

    public void setPontoDestino(Point ponto_destino) {
        this.pontoDestino = ponto_destino;
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

    public boolean getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
