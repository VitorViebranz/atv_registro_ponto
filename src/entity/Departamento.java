package entity;

public class Departamento {
    private long id;
    private String nome;

    public Departamento(long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public String getNome() { return nome; }
}