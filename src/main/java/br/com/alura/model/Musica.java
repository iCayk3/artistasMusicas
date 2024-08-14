package br.com.alura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private Genero genero;
    @ManyToOne
    private Artista artista;

    public Musica(){}

    public Musica(String nomeMusica, String genero, Artista artista) {
        this.nome = nomeMusica;
        this.genero = Genero.fromString(genero);
        this.artista = artista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return """
                Artista: %s, musica: %s, genero: %S""".formatted(artista.getNome(), getNome(), getGenero());
    }
}
