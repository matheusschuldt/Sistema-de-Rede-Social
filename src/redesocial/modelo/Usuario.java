package redesocial.modelo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Usuario {
    private String username;
    private String email;
    private String senha;
    private List<Usuario> amigos;
    private List<Usuario> solicitacoesAmizade;
    private List<Post> posts;

    public Usuario(String username, String email, String senha) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.amigos = new ArrayList();
        this.solicitacoesAmizade = new ArrayList();
        this.posts = new ArrayList();
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> getAmigos() {
        return this.amigos;
    }

    public void adicionarAmigo(Usuario amigo) {
        this.amigos.add(amigo);
    }

    public void removerAmigo(Usuario amigo) {
        this.amigos.remove(amigo);
    }

    public List<Usuario> getSolicitacoesAmizade() {
        return this.solicitacoesAmizade;
    }

    public void enviarSolicitacaoAmizade(Usuario amigo) {
        if (!this.solicitacoesAmizade.contains(amigo) && !this.amigos.contains(amigo)) {
            this.solicitacoesAmizade.add(amigo);
        }

    }

    public void aceitarSolicitacao(Usuario amigo) {
        if (this.solicitacoesAmizade.contains(amigo)) {
            this.amigos.add(amigo);
            this.solicitacoesAmizade.remove(amigo);
        }

    }

    public void recusarSolicitacao(Usuario amigo) {
        this.solicitacoesAmizade.remove(amigo);
    }

    public List<Post> getPosts() {
        return this.posts;
    }

    public void adicionarPost(Post post) {
        this.posts.add(post);
    }

    public void removerPost(Post post) {
        this.posts.remove(post);
    }

    public void atualizarPerfil(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public boolean isAmigo(Usuario usuario) {
        return this.amigos.contains(usuario);
    }

    public Usuario buscarAmigoPorUsername(String username) throws Exception {
        Iterator var2 = this.amigos.iterator();

        Usuario amigo;
        do {
            if (!var2.hasNext()) {
                throw new Exception("Amigo com o username " + username + " não encontrado.");
            }

            amigo = (Usuario)var2.next();
        } while(!amigo.getUsername().equals(username));

        return amigo;
    }
}
