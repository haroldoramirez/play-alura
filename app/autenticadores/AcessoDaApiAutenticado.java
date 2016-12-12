package autenticadores;

import com.google.inject.Inject;
import daos.UsuarioDAO;
import models.Usuario;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AcessoDaApiAutenticado extends Security.Authenticator {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public String getUsername(Http.Context context) {
        String codigo = context.request().getHeader("API-Token");

        Optional<Usuario> possivelUsuario = usuarioDAO.comToken(codigo);

        if (possivelUsuario.isPresent()) {
            return possivelUsuario.get().getNome();
        }

        return null;
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {

        Map<String, String> parametrosDoErro = new HashMap<>();

        parametrosDoErro.put("código", "401");
        parametrosDoErro.put("mensagem", "Não Autorizado!");

        Map<String, Object> erros = new HashMap<>();

        erros.put("erros", parametrosDoErro);

        return unauthorized(Json.toJson(erros));
    }
}
