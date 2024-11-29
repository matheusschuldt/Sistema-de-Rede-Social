package redesocial.gerenciador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import redesocial.modelo.Comentario;
import redesocial.modelo.Post;
import redesocial.modelo.Usuario;

public class GerenciadorPosts {
    public GerenciadorPosts() {
    }

    public void criarPost(Usuario usuario, String conteudo) throws Exception {
        Post post = new Post(usuario, conteudo);
        usuario.getPosts().add(post);
    }

    public List<Post> listarPostsDeAmigos(Usuario usuario) {
        List<Post> posts = new ArrayList();
        Iterator var3 = usuario.getAmigos().iterator();

        while(var3.hasNext()) {
            Usuario amigo = (Usuario)var3.next();
            posts.addAll(amigo.getPosts());
        }

        return posts;
    }

    public void curtirPost(Usuario usuario, Post post) throws Exception {
        if (post.getAutor().equals(usuario)) {
            throw new Exception("Você não pode curtir seus próprios posts.");
        } else if (!post.getCurtidas().contains(usuario)) {
            post.getCurtidas().add(usuario);
        } else {
            throw new Exception("Você já curtiu este post.");
        }
    }

    public void comentarPost(Usuario usuario, Post post, String comentario) throws Exception {
        if (post.getAutor().equals(usuario)) {
            throw new Exception("Você não pode comentar em seu próprio post.");
        } else {
            post.getComentarios().add(new Comentario(usuario, comentario));
        }
    }

    public List<Post> listarPostsPorUsuario(Usuario usuario) {
        return usuario.getPosts();
    }
}
