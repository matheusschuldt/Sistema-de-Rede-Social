package redesocial.gerenciador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import redesocial.modelo.Usuario;
import redesocial.util.Validador;

public class GerenciadorUsuarios {
    private List<Usuario> usuarios = new ArrayList();

    public GerenciadorUsuarios() {
    }

    public Usuario cadastrarUsuario(String username, String email, String senha) throws Exception {
        Validador.validarUsername(username);
        Validador.validarEmail(email);
        Validador.validarSenha(senha);
        Iterator var4 = this.usuarios.iterator();

        Usuario u;
        do {
            if (!var4.hasNext()) {
                Usuario usuario = new Usuario(username, email, senha);
                this.usuarios.add(usuario);
                return usuario;
            }

            u = (Usuario)var4.next();
            if (u.getUsername().equals(username)) {
                throw new Exception("Username já está em uso.");
            }
        } while(!u.getEmail().equals(email));

        throw new Exception("Email já está em uso.");
    }

    public Usuario login(String username, String senha) throws Exception {
        Iterator var3 = this.usuarios.iterator();

        Usuario u;
        do {
            if (!var3.hasNext()) {
                throw new Exception("Usuário ou senha inválidos.");
            }

            u = (Usuario)var3.next();
        } while(!u.getUsername().equals(username) || !u.getSenha().equals(senha));

        return u;
    }

    public Usuario buscarUsuarioPorUsername(String username) throws Exception {
        Iterator var2 = this.usuarios.iterator();

        Usuario u;
        do {
            if (!var2.hasNext()) {
                throw new Exception("Usuário não encontrado.");
            }

            u = (Usuario)var2.next();
        } while(!u.getUsername().equals(username));

        return u;
    }

    public void atualizarPerfil(Usuario usuario, String novoUsername, String novoEmail, String novaSenha) throws Exception {
        Validador.validarUsername(novoUsername);
        Validador.validarEmail(novoEmail);
        Validador.validarSenha(novaSenha);
        Iterator var5 = this.usuarios.iterator();

        Usuario u;
        do {
            do {
                if (!var5.hasNext()) {
                    usuario.setUsername(novoUsername);
                    usuario.setEmail(novoEmail);
                    usuario.setSenha(novaSenha);
                    return;
                }

                u = (Usuario)var5.next();
            } while(u.equals(usuario));
        } while(!u.getUsername().equals(novoUsername) && !u.getEmail().equals(novoEmail));

        throw new Exception("Username ou email já está em uso.");
    }

    public List<Usuario> listarUsuarios() {
        return new ArrayList(this.usuarios);
    }

    public List<Usuario> buscarAmigosPorUsername(Usuario usuario, String username) {
        List<Usuario> amigosEncontrados = new ArrayList();
        Iterator var4 = usuario.getAmigos().iterator();

        while(var4.hasNext()) {
            Usuario amigo = (Usuario)var4.next();
            if (amigo.getUsername().contains(username)) {
                amigosEncontrados.add(amigo);
            }
        }

        return amigosEncontrados;
    }
}
