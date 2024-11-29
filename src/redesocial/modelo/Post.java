package redesocial.modelo;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private Usuario autor;
    private String conteudo;
    private List<Usuario> curtidas;
    private List<Comentario> comentarios;

    public Post(Usuario autor, String conteudo) {
        this.autor = autor;
        this.conteudo = conteudo;
        this.curtidas = new ArrayList();
        this.comentarios = new ArrayList();
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getConteudo() {
        return this.conteudo;
    }

    public List<Usuario> getCurtidas() {
        return this.curtidas;
    }

    public List<Comentario> getComentarios() {
        return this.comentarios;
    }

    public void curtir(Usuario usuario) throws Exception {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário inválido.");
        } else if (!this.curtidas.contains(usuario)) {
            this.curtidas.add(usuario);
        } else {
            throw new Exception("Usuário já curtiu esse post.");
        }
    }

    public void comentar(Usuario usuario, String texto) throws Exception {
        if (usuario != null && texto != null && !texto.isEmpty()) {
            Comentario comentario = new Comentario(usuario, texto);
            this.comentarios.add(comentario);
        } else {
            throw new IllegalArgumentException("Usuário ou texto inválido.");
        }
    }

    public void removerComentario(Comentario comentario) throws Exception {
        if (comentario == null) {
            throw new IllegalArgumentException("Comentário inválido.");
        } else if (!this.comentarios.contains(comentario)) {
            throw new Exception("Comentário não encontrado.");
        } else {
            this.comentarios.remove(comentario);
        }
    }
}
