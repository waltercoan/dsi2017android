package br.univille.dsi2017android.model;

/**
 * Created by walte on 18/10/2017.
 */

public class Cliente {
    private long id;
    private String nome;
    private int sexo;


    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getSexo() {
        return sexo;
    }
    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String toString(){
        return String.format("%s (%s)",this.nome,((this.sexo == 1?"Masculino":"Femino")));
    }
}