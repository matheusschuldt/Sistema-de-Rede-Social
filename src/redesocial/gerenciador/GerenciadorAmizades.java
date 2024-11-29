package redesocial.gerenciador;

import java.util.ArrayList;
import java.util.List;
import redesocial.modelo.Usuario;

public class GerenciadorAmizades {
    public GerenciadorAmizades() {
    }

    public boolean enviarSolicitacao(Usuario remetente, Usuario destinatario) throws Exception {
        if (destinatario == null) {
            throw new Exception("Usuário destinatário não encontrado.");
        } else if (destinatario.getSolicitacoesAmizade().contains(remetente)) {
            throw new Exception("Solicitação já enviada.");
        } else {
            destinatario.getSolicitacoesAmizade().add(remetente);
            return true;
        }
    }

    public boolean aceitarSolicitacao(Usuario usuario, Usuario remetente) throws Exception {
        if (!usuario.getSolicitacoesAmizade().contains(remetente)) {
            throw new Exception("Solicitação não encontrada.");
        } else {
            usuario.getSolicitacoesAmizade().remove(remetente);
            usuario.getAmigos().add(remetente);
            remetente.getAmigos().add(usuario);
            return true;
        }
    }

    public void recusarSolicitacao(Usuario usuario, Usuario remetente) throws Exception {
        if (!usuario.getSolicitacoesAmizade().contains(remetente)) {
            throw new Exception("Solicitação não encontrada.");
        } else {
            usuario.getSolicitacoesAmizade().remove(remetente);
        }
    }

    public boolean removerAmizade(Usuario usuario, Usuario amigo) throws Exception {
        if (!usuario.getAmigos().contains(amigo)) {
            throw new Exception("Amizade não encontrada.");
        } else {
            usuario.getAmigos().remove(amigo);
            amigo.getAmigos().remove(usuario);
            return true;
        }
    }

    public List<Usuario> listarAmigos(Usuario usuario) {
        return new ArrayList(usuario.getAmigos());
    }
}
