package com.alura.musics.principal;

import java.util.Scanner;

public class Principal {

    private Scanner leitura = new Scanner(System.in);

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
    }

    private void buscarMusicaPorArtita() {
    }

    private void listarMusicaBuscada() {
    }

    private void cadastrarMusicas() {
    }

    private void cadastrarArtitas() {
        
    }
}
