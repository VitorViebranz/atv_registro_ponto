package entity;

public class Funcionario {
    private long id;
    private String matricula;
    private String nome;
    private Departamento departamento;

    public Funcionario(long id, String matricula, String nome, Departamento departamento) {
        this.id = id;
        this.matricula = matricula;
        this.nome = nome;
        this.departamento = departamento;
    }

    public String getNome() {
        return nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }
}