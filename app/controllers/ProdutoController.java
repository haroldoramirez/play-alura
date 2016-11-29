package controllers;

import models.Produto;
import play.data.Form;
import play.data.FormFactory;
import play.mvc.Controller;
import play.mvc.Result;
import validators.ValidatorDeProduto;
import views.html.*;

import javax.inject.Inject;

public class ProdutoController extends Controller {

    @Inject
    private FormFactory formularios;

    @Inject
    private ValidatorDeProduto validatorDeProduto;

    public Result salvaNovoProduto() {
        Form<Produto> formulario = formularios.form(Produto.class).bindFromRequest();
        Produto produto = formulario.get();

        if (validatorDeProduto.temErros(formulario)) {
            flash("danger", "Existem erros no seu formulário!");
            return badRequest(formularioDeNovoProduto.render(formulario));
        }
        produto.save();
        flash("success", "Seu produto '" + produto.getTitulo() + "' foi cadastrado com sucesso!");
        return redirect(routes.ProdutoController.formularioDeNovoProduto());
    }

    public Result formularioDeNovoProduto() {
        Produto produto = new Produto();
        produto.setTipo("Livro");
        Form<Produto> formulario = formularios.form(Produto.class).fill(produto);
        return ok(formularioDeNovoProduto.render(formulario));
    }
}