package daos;

import com.avaje.ebean.Model;
import models.Usuario;

import java.util.Optional;

public class UsuarioDAO {

    private Model.Finder<Long, Usuario > usuarios = new Model.Finder<>(Usuario.class);

    public Optional<Usuario> comEmail(String email) {

        Usuario usuario = usuarios.where().eq("email", email).findUnique();

        return Optional.ofNullable(usuario);
    }
}
