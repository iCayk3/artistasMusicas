package br.com.alura.principal;

import br.com.alura.model.Artista;
import br.com.alura.model.Musica;
import br.com.alura.repository.RepositoryArtista;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner sc = new Scanner(System.in);
    private int opcao = -1;
    RepositoryArtista repository;
    private Artista artista;

    public Principal(RepositoryArtista repository) {
        this.repository = repository;
    }

    public void exibirMenu(){

        while(opcao != 9) {
            System.out.println("""
                    
                    Scree Music
                    Informa a opcao que deseja.
                    
                    1- Cadastrar artistas
                                    
                    2- Cadastrar músicas
                                    
                    3- Listar músicas
                                    
                    4- Buscar músicas por artistas
                                    
                    5- Pesquisar dados sobre um artista
                                    
                    9- Sair
                    """);
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    cadastrarArtista();
                    break;
                case 2:
                    cadastrarMusica();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    listarMusicasPorArtista();
                    break;
                case 5:
                    break;
                case 9:
                    break;
                default:
                    System.out.println("Opção invalida!");

            }
        }
    }

    private void cadastrarArtista(){
        var opcaoCadastroArtista = "";
        while (!opcaoCadastroArtista.equals("n")) {
            System.out.println("Informe o nome desse artista: ");
            var nomeArtista = sc.nextLine();
            System.out.println("Informe o tipo desse artista: (solo, dupla, banda)");
            var tipoArtista = sc.nextLine();

            Artista artista = new Artista(nomeArtista, tipoArtista);
            System.out.println(artista);
            repository.save(artista);

            System.out.println("Cadastrar outro artista? (s/n)");
            opcaoCadastroArtista = sc.nextLine().toLowerCase();

        }
    }
    private void cadastrarMusica() {
        var opcaoMusica = "";

        System.out.println("Qual o artista dessa musica?");
        var artistaMusica = sc.nextLine();
        Optional<Artista> artistaEncontrado = repository.findByNomeContainingIgnoreCase(artistaMusica);
        if (artistaEncontrado.isPresent()) {
            Artista artista = artistaEncontrado.get();
            List<Musica> musicas = new ArrayList<>();
            while (!opcaoMusica.equals("n")) {
                System.out.println("Qual o nome da musica?");
                var nomeMusica = sc.nextLine();
                System.out.println("Qual o genero? (Samba, Pagode, Forro, Jazz, Rock, Blues, Gospel, Eletronica, Funk)");
                var genero = sc.nextLine();
                musicas.add(new Musica(nomeMusica, genero, artista));

                System.out.println("Deseja salvar outra musica? ");
                opcaoMusica = sc.nextLine();
            }
            artista.setMusicas(musicas);
            repository.save(artista);
        } else {
            System.out.println("Artista não encontrado!");
        }
    }

    private void listarMusicas(){
        List<Musica> musicasEncontradas = repository.allMusicas();
        musicasEncontradas.forEach(System.out::println);
    }
    private void listarMusicasPorArtista(){
        System.out.println("Qual o artista dessa musica?");
        var artistaMusica = sc.nextLine();
        Optional<Artista> artistaEncontrado = repository.findByNomeContainingIgnoreCase(artistaMusica);
        if (artistaEncontrado.isPresent()) {
            Artista artista = artistaEncontrado.get();
            List<Musica> musicasEncontradas = repository.encontrarMusicaPorArtista(artista);
            System.out.println(musicasEncontradas);
        }else {
            System.out.println("Artista nao encontrado!");
        }
    }
}
