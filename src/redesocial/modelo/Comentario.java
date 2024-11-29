package redesocial.modelo;

public class Comentario {
    private Usuario autor;
    private String texto;

    public Comentario(Usuario autor, String texto) {
        this.autor = autor;
        this.texto = texto;
    }

    public Usuario getAutor() {
        return this.autor;
    }

    public String getTexto() {
        return this.texto;
    }

    public String toString() {
        String var10000 = this.autor.getUsername();
        return var10000 + ": " + this.texto;
    }
}