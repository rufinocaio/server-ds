package caiofurlan.serverdistributedsystems.models;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Segment {

    Point ponto_origem;
    Point ponto_destino;
    String direcao;
    int distancia;
    String obs;
    int id;

    public Segment() {
    }

    public Segment(Point ponto_origem, Point ponto_destino, String direcao, int distancia, String obs) {
        this.ponto_origem = ponto_origem;
        this.ponto_destino = ponto_destino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
    }

    public Segment(Point ponto_origem, Point ponto_destino, String direcao, int distancia, String obs, int id) {
        this.ponto_origem = ponto_origem;
        this.ponto_destino = ponto_destino;
        this.direcao = direcao;
        this.distancia = distancia;
        this.obs = obs;
        this.id = id;
    }

    public Point getPontoOrigem() {
        return ponto_origem;
    }

    public Point getPontoDestino() {
        return ponto_destino;
    }

    public String getDirecao() {
        return direcao;
    }

    public int getDistancia() {
        return distancia;
    }

    public String getObs() {
        return obs;
    }

    public int getID() {
        return id;
    }

}
