package acoes;

import com.google.inject.Inject;
import daos.UsuarioDAO;
import models.RegistroDeAcesso;
import models.Usuario;
import play.Logger;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public class AcaoDeRegistroDeAcesso extends Action.Simple {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public CompletionStage<Result> call(Http.Context context) {

        String codigo = context.request().getHeader("API-Token");

        String uri = context.request().uri();
        Usuario usuario = usuarioDAO.comToken(codigo).get();

        RegistroDeAcesso acesso = new RegistroDeAcesso(usuario, uri);
        acesso.save();
        return delegate.call(context);
    }


}
