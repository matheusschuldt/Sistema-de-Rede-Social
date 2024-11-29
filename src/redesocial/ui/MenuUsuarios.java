package redesocial.ui;

import java.util.Scanner;
import redesocial.gerenciador.GerenciadorUsuarios;
import redesocial.modelo.Usuario;

public class MenuUsuarios {
    private GerenciadorUsuarios gerenciadorUsuarios;
    private Scanner scanner;

    public MenuUsuarios(GerenciadorUsuarios gerenciadorUsuarios, Scanner scanner) {
        this.gerenciadorUsuarios = gerenciadorUsuarios;
        this.scanner = scanner;
    }

    public Usuario menuCadastroLogin() {
        while(true) {
            System.out.println("1. Cadastrar");
            System.out.println("2. Login");
            System.out.println("0. Sair");
            int opcao = this.scanner.nextInt();
            this.scanner.nextLine();

            try {
                String username;
                String senha;
                switch (opcao) {
                    case 0:
                        return null;
                    case 1:
                        System.out.print("Username: ");
                        username = this.scanner.nextLine();
                        System.out.print("Email: ");
                        String email = this.scanner.nextLine();
                        System.out.print("Senha: ");
                        senha = this.scanner.nextLine();
                        return this.gerenciadorUsuarios.cadastrarUsuario(username, email, senha);
                    case 2:
                        System.out.print("Username: ");
                        username = this.scanner.nextLine();
                        System.out.print("Senha: ");
                        senha = this.scanner.nextLine();
                        return this.gerenciadorUsuarios.login(username, senha);
                }
            } catch (Exception var5) {
                System.out.println("Erro: " + var5.getMessage());
            }
        }
    }
}
