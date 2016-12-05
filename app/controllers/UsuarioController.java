package controllers;

import akka.util.Crypt;
import com.google.inject.Inject;
import daos.TokenDeCadastroDAO;
import daos.UsuarioDAO;
import models.EmailDeCadastro;
import models.TokenDeCadastro;
import models.Usuario;
import play.api.libs.mailer.MailerClient;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.*;
import validators.ValidadorDeUsuario;
import views.html.*;

import java.util.Optional;

public class UsuarioController extends Controller {

    @Inject
    private FormFactory formularios;

    @Inject
    private ValidadorDeUsuario validadorDeUsuario;

    @Inject
    private MailerClient enviador;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private TokenDeCadastroDAO tokenDeCadastroDAO;

    public Result formularioDeNovoUsuario() {
        Form<Usuario> formulario = formularios.form(Usuario.class);
        return ok(formularioDeNovoUsuario.render(formulario));
    }

    public Result salvaNovoUsuario() {
        Form<Usuario> formulario = formularios.form(Usuario.class).bindFromRequest();

        if (validadorDeUsuario.temErros(formulario)) {
            flash("danger", "Há erros no formulário de cadastro!");
            return badRequest(formularioDeNovoUsuario.render(formulario));
        }
        Usuario usuario = formulario.get();
        String senhaCrupto = Crypt.sha1(usuario.getSenha());
        usuario.setSenha(senhaCrupto);
        usuario.save();
        TokenDeCadastro token = new TokenDeCadastro(usuario);
        token.save();
        enviador.send(new EmailDeCadastro(token));
        flash("success", "Um email foi enviado para confirmar o seu cadastro!");
        return redirect("/login");
    }

    public Result confirmaUsuario(String email, String codigo) {
        Optional<Usuario> possivelUsuario = usuarioDAO.comEmail(email);
        Optional<TokenDeCadastro> possivelToken = tokenDeCadastroDAO.comCodigo(codigo);
        if (possivelToken.isPresent() && possivelToken.isPresent()) {
            TokenDeCadastro token = possivelToken.get();
            Usuario usuario = possivelUsuario.get();
            if (token.getUsuario().equals(usuario)) {
                token.delete();
                usuario.setVerificado(true);
                usuario.update();
                flash("success", "Cadastrado confirmado com sucesso");
                // TODO fazer login
                return redirect("/usuario/painel"); // TODO
            }
        }

        flash("danger", "Ocorreu um erro ao confirmar o cadastro!");
        return redirect("/login"); // TODO
    }
}
