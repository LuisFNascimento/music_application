package com.alura.musics.principal;

import com.alura.musics.model.Artista;
import com.alura.musics.model.Musica;
import com.alura.musics.model.TipoArtista;
import com.alura.musics.repository.ArtistaRepository;
import com.alura.musics.service.ConsultaChatGPT;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final ArtistaRepository repositorio;

    private Scanner leitura = new Scanner(System.in);

    public Principal(ArtistaRepository repositorio) {
        this.repositorio = repositorio;
    }

    public void exibeMenu() {

        var opcao = -1;

        while (opcao != 9) {

            var menu = """
                    *** Sreen Sound Músicas ***
                                        
                    1 - Cadastrar artitas
                    2 - Cadastrae músicas
                    3 - Listar músicas
                    4 - Buscar músicas por artistas
                    5 - Pesquisar dados sobre o artista
                                        
                    9 - Sair
                    """;

            System.out.println(menu);
            System.out.print("Escolha uma opção: ");
            try {
                opcao = leitura.nextInt();
                leitura.nextLine();
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                leitura.nextLine(); // Limpa o buffer
                continue;
            }
            switch (opcao) {
                case 1:
                    cadastrarArtitas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicaBuscada();
                    break;
                case 4:
                    buscarMusicaPorArtita();
                    break;
                case 5:
                    pesquisarDadosArtitas();
                    break;

                case 9:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }

    private void pesquisarDadosArtitas() {
        System.out.println("Pesquisar dados sobre qual artista? ");
        var nome = leitura.nextLine();
        var resposta = ConsultaChatGPT.obterInfomacao(nome);
        System.out.println(resposta.trim());
    }

    private void buscarMusicaPorArtita() {
        System.out.println("Buscar música de que artista? ");
        var nome = leitura.nextLine();
        List<Musica> musicas = repositorio.buscarMusicasPorArtista(nome);
        musicas.forEach(System.out::println);
    }

    private void listarMusicaBuscada() {
        List<Artista> artistas = repositorio.findAll();
        artistas.forEach(a -> a.getMusicas().forEach(System.out::println));
    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar muúsica de que artista? ");
        var nome = leitura.nextLine();
        Optional<Artista> artista = repositorio.findByNomeContainingIgnoreCase(nome);
        if(artista.isPresent()){
            System.out.println("Informe o título da música: ");
            var nomeMusica = leitura.nextLine();
            Musica musica = new Musica(nomeMusica);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorio.save(artista.get());
        } else {
            System.out.println("Artista não encontrado! ");
        }
    }

    private void cadastrarArtitas() {
        var cadastrarNovo = "S";

        while (cadastrarNovo.equalsIgnoreCase("s")) {
            System.out.println("Informe o nome desse artista: ");
            var nome = leitura.nextLine();
            System.out.println("informe o tipo desse artista: (solo, dupla ou banda) ");
            var tipo = leitura.nextLine();
            TipoArtista tipoArtista = TipoArtista.valueOf(tipo.toUpperCase());
            Artista artista = new Artista(nome, tipoArtista);
            repositorio.save(artista);
            System.out.println("Cadastrar novo artista? (S/N");
            cadastrarNovo = leitura.nextLine();
        }
    }
}
