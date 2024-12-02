package it.giomrc.altrotest.model;
import java.util.HashSet;
import java.util.Set;

public class Piatto {
    protected Long id;
    protected String nome;
    protected Set<Ristorante> ristoranti = new HashSet<>();

    // Costruttori
    public Piatto() {}
    public Piatto(String nome) {
        this.nome = nome;
    }

    // Getter e Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<Ristorante> getRistoranti() { return ristoranti; }
    public void setRistoranti(Set<Ristorante> ristoranti) { this.ristoranti = ristoranti; }
}
