package br.com.nicolas.soundMusic.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "musica")
@Data
@Entity
@NoArgsConstructor
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String titulo;
    private int duracao;
    @ManyToOne
    private Artista artista;

    public Musica(String titulo, int duracao){
        this.titulo = titulo;
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Música='" + titulo + '\'' +
                ", artista=" + artista.getNome();
    }
}
