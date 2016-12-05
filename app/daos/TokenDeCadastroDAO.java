package daos;

import com.avaje.ebean.Model;
import models.TokenDeCadastro;

import java.util.Optional;

public class TokenDeCadastroDAO {

    private Model.Finder<Long, TokenDeCadastro> tokens = new Model.Finder<>(TokenDeCadastro.class);

    public Optional<TokenDeCadastro> comCodigo(String codigo) {
        TokenDeCadastro tokenDeCadastro = tokens.where().eq("codigo", codigo).findUnique();
        return Optional.ofNullable(tokenDeCadastro);
    }
}
