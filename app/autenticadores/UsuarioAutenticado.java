package autenticadores;

import controllers.UsuarioController;
import controllers.routes;
import daos.UsuarioDAO;
import models.Usuario;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security.Authenticator;

import javax.inject.Inject;
import java.util.Optional;

public class UsuarioAutenticado extends Authenticator {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public String getUsername(Http.Context context) {
        String codigo = context.session().get(UsuarioController.AUTH);
        Optional<Usuario> possivelUsuario = usuarioDAO.comToken(codigo);
        if (possivelUsuario.isPresent()) {
            Usuario usuario = possivelUsuario.get();
            context.args.put("usuario", usuario);
            return possivelUsuario.get().getNome();
        }
        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context context) {
        context.flash().put("danger", "Não Autorizado!");
        return redirect(routes.UsuarioController.formularioDeLogin());
    }
}
