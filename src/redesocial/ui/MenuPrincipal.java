package redesocial.ui;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import redesocial.gerenciador.GerenciadorAmizades;
import redesocial.gerenciador.GerenciadorPosts;
import redesocial.gerenciador.GerenciadorUsuarios;
import redesocial.modelo.Post;
import redesocial.modelo.Usuario;

public class MenuPrincipal {
    private static Scanner scanner;
    private static GerenciadorUsuarios gerenciadorUsuarios;
    private static GerenciadorPosts gerenciadorPosts;
    private static GerenciadorAmizades gerenciadorAmizades;
    private static Usuario usuarioLogado;

    public MenuPrincipal() {
    }

    public static void main(String[] args) {
        boolean rodando = true;

        while(rodando) {
            if (usuarioLogado == null) {
                exibirMenuInicial();
            } else {
                exibirMenuPrincipal();
            }
        }

    }

    private static void exibirMenuInicial() {
        System.out.println("\n=== Menu Inicial ===");
        System.out.println("1. Cadastro de Usuário");
        System.out.println("2. Login");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 0:
                System.out.println("Saindo...");
                System.exit(0);
                break;
            case 1:
                cadastrarUsuario();
                break;
            case 2:
                login();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }

    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n=== Menu Principal ===");
        System.out.println("1. Ver Feed de Amigos");
        System.out.println("2. Criar Post");
        System.out.println("3. Listar Posts");
        System.out.println("4. Amizades");
        System.out.println("5. Listar Amigos");
        System.out.println("6. Logout");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                verFeedAmigos();
                break;
            case 2:
                criarPost();
                break;
            case 3:
                listarPosts();
                break;
            case 4:
                menuAmizades();
                break;
            case 5:
                listarAmigos();
                break;
            case 6:
                logout();
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }

    }

    private static void cadastrarUsuario() {
        try {
            System.out.print("Digite o nome de usuário: ");
            String username = scanner.nextLine();
            System.out.print("Digite o email: ");
            String email = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();
            gerenciadorUsuarios.cadastrarUsuario(username, email, senha);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (Exception var3) {
            System.out.println("Erro ao cadastrar usuário: " + var3.getMessage());
        }

    }

    private static void login() {
        try {
            System.out.print("Digite o nome de usuário: ");
            String username = scanner.nextLine();
            System.out.print("Digite a senha: ");
            String senha = scanner.nextLine();
            usuarioLogado = gerenciadorUsuarios.login(username, senha);
            System.out.println("Login realizado com sucesso!");
        } catch (Exception var2) {
            System.out.println("Erro no login: " + var2.getMessage());
        }

    }

    private static void logout() {
        System.out.println("Você foi desconectado.");
        usuarioLogado = null;
    }

    private static void verFeedAmigos() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para ver o feed.");
        } else {
            try {
                List<Post> posts = gerenciadorPosts.listarPostsDeAmigos(usuarioLogado);
                Iterator var1 = posts.iterator();

                while(var1.hasNext()) {
                    Post post = (Post)var1.next();
                    PrintStream var10000 = System.out;
                    String var10001 = post.getConteudo();
                    var10000.println("- " + var10001 + " (Autor: " + post.getAutor().getUsername() + ")");
                    System.out.println("1. Curtir | 2. Comentar | 0. Voltar");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();
                    switch (opcao) {
                        case 0:
                            return;
                        case 1:
                            gerenciadorPosts.curtirPost(usuarioLogado, post);
                            System.out.println("Post curtido!");
                            break;
                        case 2:
                            System.out.print("Digite o comentário: ");
                            String comentario = scanner.nextLine();
                            gerenciadorPosts.comentarPost(usuarioLogado, post, comentario);
                            System.out.println("Comentário adicionado!");
                            break;
                        default:
                            System.out.println("Opção inválida!");
                    }
                }
            } catch (Exception var5) {
                System.out.println("Erro ao exibir o feed de amigos: " + var5.getMessage());
            }

        }
    }

    private static void criarPost() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para criar um post.");
        } else {
            try {
                System.out.print("Digite o conteúdo do post: ");
                String conteudo = scanner.nextLine();
                gerenciadorPosts.criarPost(usuarioLogado, conteudo);
                System.out.println("Post criado com sucesso!");
            } catch (Exception var1) {
                System.out.println("Erro ao criar post: " + var1.getMessage());
            }

        }
    }

    private static void listarPosts() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para listar os posts.");
        } else {
            try {
                List<Post> posts = gerenciadorPosts.listarPostsPorUsuario(usuarioLogado);
                Iterator var1 = posts.iterator();

                while(var1.hasNext()) {
                    Post post = (Post)var1.next();
                    System.out.println(post.getConteudo());
                }
            } catch (Exception var3) {
                System.out.println("Erro ao listar posts: " + var3.getMessage());
            }

        }
    }

    private static void listarAmigos() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para listar seus amigos.");
        } else {
            try {
                List<Usuario> amigos = gerenciadorAmizades.listarAmigos(usuarioLogado);
                if (amigos.isEmpty()) {
                    System.out.println("Você ainda não tem amigos.");
                } else {
                    System.out.println("Lista de Amigos:");
                    Iterator var1 = amigos.iterator();

                    while(var1.hasNext()) {
                        Usuario amigo = (Usuario)var1.next();
                        System.out.println("- " + amigo.getUsername());
                    }
                }
            } catch (Exception var3) {
                System.out.println("Erro ao listar amigos: " + var3.getMessage());
            }

        }
    }

    private static void menuAmizades() {
        System.out.println("\n=== Menu de Amizades ===");
        System.out.println("1. Enviar Solicitação de Amizade");
        System.out.println("2. Aceitar Solicitação de Amizade");
        System.out.println("3. Remover Amigo");
        System.out.println("4. Voltar");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();
        switch (opcao) {
            case 1:
                enviarSolicitacaoAmizade();
                break;
            case 2:
                aceitarSolicitacaoAmizade();
                break;
            case 3:
                removerAmigo();
                break;
            case 4:
                return;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }

    }

    private static void enviarSolicitacaoAmizade() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para enviar uma solicitação.");
        } else {
            try {
                System.out.print("Digite o nome de usuário do amigo: ");
                String usernameAmigo = scanner.nextLine();
                Usuario amigo = gerenciadorUsuarios.buscarUsuarioPorUsername(usernameAmigo);
                if (amigo == null) {
                    System.out.println("Usuário não encontrado.");
                    return;
                }

                boolean solicitacaoEnviada = gerenciadorAmizades.enviarSolicitacao(usuarioLogado, amigo);
                if (solicitacaoEnviada) {
                    System.out.println("Solicitação de amizade enviada com sucesso.");
                } else {
                    System.out.println("Você já enviou uma solicitação para este usuário.");
                }
            } catch (Exception var3) {
                System.out.println("Erro ao enviar solicitação de amizade: " + var3.getMessage());
            }

        }
    }

    private static void aceitarSolicitacaoAmizade() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para aceitar solicitações.");
        } else {
            try {
                System.out.print("Digite o nome de usuário da pessoa que enviou a solicitação: ");
                String usernameAmigo = scanner.nextLine();
                Usuario amigo = gerenciadorUsuarios.buscarUsuarioPorUsername(usernameAmigo);
                if (amigo == null) {
                    System.out.println("Usuário não encontrado.");
                    return;
                }

                boolean solicitacaoAceita = gerenciadorAmizades.aceitarSolicitacao(usuarioLogado, amigo);
                if (solicitacaoAceita) {
                    System.out.println("Solicitação de amizade aceita!");
                } else {
                    System.out.println("Não há solicitação pendente.");
                }
            } catch (Exception var3) {
                System.out.println("Erro ao aceitar solicitação de amizade: " + var3.getMessage());
            }

        }
    }

    private static void removerAmigo() {
        if (usuarioLogado == null) {
            System.out.println("Você precisa estar logado para remover um amigo.");
        } else {
            try {
                System.out.print("Digite o nome de usuário do amigo que deseja remover: ");
                String usernameAmigo = scanner.nextLine();
                Usuario amigo = gerenciadorUsuarios.buscarUsuarioPorUsername(usernameAmigo);
                if (amigo == null) {
                    System.out.println("Usuário não encontrado.");
                    return;
                }

                boolean amizadeRemovida = gerenciadorAmizades.removerAmizade(usuarioLogado, amigo);
                if (amizadeRemovida) {
                    System.out.println("Amizade removida com sucesso.");
                } else {
                    System.out.println("Erro ao remover amizade.");
                }
            } catch (Exception var3) {
                System.out.println("Erro ao remover amigo: " + var3.getMessage());
            }

        }
    }

    static {
        scanner = new Scanner(System.in);
        gerenciadorUsuarios = new GerenciadorUsuarios();
        gerenciadorPosts = new GerenciadorPosts();
        gerenciadorAmizades = new GerenciadorAmizades();
    }

    public void exibirMenu() {
    }
}
