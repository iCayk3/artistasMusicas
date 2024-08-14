package br.com.alura.repository;

import br.com.alura.model.Artista;
import br.com.alura.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RepositoryArtista extends JpaRepository<Artista, Long> {
    Optional<Artista> findByNomeContainingIgnoreCase(String artistaMusica);

    @Query("SELECT m FROM Artista a JOIN a.musicas m")
    List<Musica> allMusicas();
    @Query("SELECT m FROM Artista a JOIN a.musicas m WHERE a = :artista")
    List<Musica> encontrarMusicaPorArtista(Artista artista);
}
