package redesocial.ui;

import java.util.Scanner;
import redesocial.gerenciador.GerenciadorAmizades;
import redesocial.modelo.Usuario;

public class MenuAmizades {
    private GerenciadorAmizades gerenciadorAmizades;
    private Scanner scanner;

    public MenuAmizades(GerenciadorAmizades gerenciadorAmizades, Scanner scanner) {
        this.gerenciadorAmizades = gerenciadorAmizades;
        this.scanner = scanner;
    }

    public void exibirMenuAmizades(Usuario usuarioLogado) {
        while(true) {
            System.out.println("1. Enviar Solicitação de Amizade");
            System.out.println("2. Aceitar Solicitação de Amizade");
            System.out.println("3. Listar Amigos");
            System.out.println("0. Voltar");
            int opcao = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                switch (opcao) {
                    case 0:
                        return;
                    case 1:
                        System.out.print("Digite o username do destinatário: ");
                        String destinatarioUsername = this.scanner.nextLine();
                        this.gerenciadorAmizades.enviarSolicitacao(usuarioLogado, usuarioLogado.buscarAmigoPorUsername(destinatarioUsername));
                        System.out.println("Solicitação enviada com sucesso!");
                        break;
                    case 2:
                        System.out.print("Digite o username do remetente: ");
                        String remetenteUsername = this.scanner.nextLine();
                        this.gerenciadorAmizades.aceitarSolicitacao(usuarioLogado, usuarioLogado.buscarAmigoPorUsername(remetenteUsername));
                        System.out.println("Solicitação aceita!");
                        break;
                    case 3:
                        this.gerenciadorAmizades.listarAmigos(usuarioLogado);
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception var5) {
                System.out.println("Erro: " + var5.getMessage());
            }
        }
    }
}
