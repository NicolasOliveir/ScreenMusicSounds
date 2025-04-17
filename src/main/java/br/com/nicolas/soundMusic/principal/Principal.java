package br.com.nicolas.soundMusic.principal;

import br.com.nicolas.soundMusic.models.Artista;
import br.com.nicolas.soundMusic.models.Musica;
import br.com.nicolas.soundMusic.repository.ArtistaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ArtistaRepository repository;

    public Principal(ArtistaRepository repository) { this.repository = repository; }

    public void exibeMenu() {
        var opcao = -1;
        while(opcao != 9){
            var menu = """
                1- Cadastrar artistas
                2- Cadastrar músicas
                3- Listar músicas
                4- Buscar músicas por artistas
                5- Pesquisar dados sobre um artista
                
                9- Sair
                """;
            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarSobreOArtista();
                    break;
            }
        }

    }

    private void pesquisarSobreOArtista() {
    }

    private void buscarMusicasPorArtista() {
        System.out.println("Escolha o artista: ");
        String resposta = leitura.nextLine();
        List<Musica> musicas = repository.musicasPorArtista(resposta);
        musicas.forEach(System.out::println);
        voltarMenu();
    }

    private void listarMusicas() {
        List<Artista> artistas = repository.findAll();
        artistas.forEach(a ->{
            String musicas = a.getMusicas().stream()
                            .map(Musica::getTitulo)
                            .collect(Collectors.joining(", "));
            System.out.printf("Artista: %s - Musicas : %s \n",a.getNome(),musicas);
            }
        );
        voltarMenu();
    }

    private void cadastrarMusicas() {
        repository.findAll().forEach(a -> System.out.printf("%s\n",a.getNome()) );
        System.out.println();

        System.out.println("Escolha o artista: ");
        String artista = leitura.nextLine();
        Optional<Artista> selecionado = repository.findByNome(artista);
        if (selecionado.isPresent()){
            System.out.println("Digite o título da música: ");
            String titulo = leitura.nextLine();
            System.out.println("Digite a duração em minutos da musica: ");
            int duracao = leitura.nextInt();
            Musica music =  new Musica(titulo,duracao);
            music.setArtista(selecionado.get());
            selecionado.get().getMusicas().add(music);
            repository.save(selecionado.get());
        }else {
            System.out.println("Artista não cadastrado!!!");
        }
        voltarMenu();
    }

    private void cadastrarArtista() {
        System.out.println("Digite o nome do artista: ");
        String nomeArtista = leitura.nextLine();
        Optional<Artista> resposta = repository.findByNomeContainingIgnoreCase(nomeArtista);
        if (resposta.isPresent()) {
            System.out.println("Artista já cadastrado!!!");
        } else {
            System.out.println("Informe o tipo desse artista: ");
            String tipoArtista = leitura.nextLine();
            Artista artista = new Artista(nomeArtista,tipoArtista);
            repository.save(artista);
            System.out.println("Artista cadastrada com sucesso!");
        }
    }

    private void voltarMenu(){
        var op = -1;
        while(op != 0){
            System.out.println("Press 0 para retornar pro menu");
            op = leitura.nextInt();
        }
    }
}
