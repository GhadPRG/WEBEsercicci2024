package it.giomrc.altrotest.model;
import java.util.HashSet;
import java.util.Set;

public class Ristorante {
    protected Long id;
    protected String nome;
    protected Set<Piatto> piatti = new HashSet<>();

    // Costruttori
    public Ristorante() {}
    public Ristorante(String nome) {
        this.nome = nome;
    }

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<Piatto> getPiatti() { return piatti; }
    public void setPiatti(Set<Piatto> piatti) { this.piatti = piatti; }
}
