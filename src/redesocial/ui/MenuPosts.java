//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package redesocial.ui;

import java.util.Scanner;
import redesocial.gerenciador.GerenciadorPosts;
import redesocial.modelo.Usuario;

public class MenuPosts {
    private GerenciadorPosts gerenciadorPosts;
    private Scanner scanner;

    public MenuPosts(GerenciadorPosts gerenciadorPosts, Scanner scanner) {
        this.gerenciadorPosts = gerenciadorPosts;
        this.scanner = scanner;
    }

    public void exibirMenuPosts(Usuario usuarioLogado) {
        while(true) {
            System.out.println("1. Criar Post");
            System.out.println("2. Listar Meus Posts");
            System.out.println("0. Voltar");
            int opcao = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (opcao) {
                    case 0:
                        return;
                    case 1:
                        System.out.print("Conteúdo do post: ");
                        String conteudo = this.scanner.nextLine();
                        this.gerenciadorPosts.criarPost(usuarioLogado, conteudo);
                        System.out.println("Post criado com sucesso!");
                        break;
                    case 2:
                        this.gerenciadorPosts.listarPostsPorUsuario(usuarioLogado);
                }
            } catch (Exception var4) {
                System.out.println("Erro: " + var4.getMessage());
            }
        }
    }
}
