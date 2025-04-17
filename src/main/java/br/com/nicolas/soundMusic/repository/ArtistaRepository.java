package br.com.nicolas.soundMusic.repository;

import br.com.nicolas.soundMusic.models.Artista;
import br.com.nicolas.soundMusic.models.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {
    Optional<Artista> findByNome(String nome);

    @Query("select m from Artista a join a.musicas m where a.nome = :artista ")
    List<Musica> musicasPorArtista(String artista);

    Optional<Artista> findByNomeContainingIgnoreCase(String nome);
}
