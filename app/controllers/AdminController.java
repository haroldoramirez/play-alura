package controllers;

import autenticadores.AdminAutenticado;
import com.google.inject.Inject;
import daos.ProdutoDAO;
import daos.UsuarioDAO;
import models.Produto;
import models.Usuario;
import play.mvc.*;
import views.html.*;

import java.util.List;

@Security.Authenticated(AdminAutenticado.class)
public class AdminController extends Controller {

    @Inject
    private ProdutoDAO produtoDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    public Result usuarios() {
        List<Usuario> listaDeUsuarios =  usuarioDAO.todos();
        return ok(usuarios.render(listaDeUsuarios));
    }

    public Result produtos() {
        List<Produto> listaDeProdutos =  produtoDAO.todos();
        return ok(produtos.render(listaDeProdutos));
    }
}
