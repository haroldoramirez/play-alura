package controllers;

import acoes.AcaoDeRegistroDeAcesso;
import autenticadores.AcessoDaApiAutenticado;
import com.fasterxml.jackson.databind.JsonNode;
import daos.ProdutoDAO;
import models.EnvelopeDeProdutos;
import models.Produto;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import play.mvc.With;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Security.Authenticated(AcessoDaApiAutenticado.class)
@With(AcaoDeRegistroDeAcesso.class)
public class ApiController extends Controller {

    private static final List<String> ATRIBUTOS = Arrays.asList("id", "titulo", "codigo", "tipo", "descricao", "preco");

    @Inject
    private ProdutoDAO produtoDAO;

    @Inject
    private FormFactory formularios;

    public Result todos() {
        EnvelopeDeProdutos envelopeDeProdutos = new EnvelopeDeProdutos(produtoDAO.todos());
        return ok(Json.toJson(envelopeDeProdutos));
    }

    public Result doTipo(String tipo) {
        EnvelopeDeProdutos envelopeDeProdutos = new EnvelopeDeProdutos(produtoDAO.doTipo(tipo));
        return ok(Json.toJson(envelopeDeProdutos));
    }

    public Result comFiltros() {
        DynamicForm formulario = formularios.form().bindFromRequest();
        validaFormulario(formulario);

        if (formulario.hasErrors()) {
            JsonNode erros = Json.newObject().set("erros", formulario.errorsAsJson());
            return badRequest(erros);
        }

        Map<String, String> parametros = formulario.data();
        List<Produto> produtos = produtoDAO.comFiltro(parametros);
        EnvelopeDeProdutos envelopeDeProdutos = new EnvelopeDeProdutos(produtos);
        return ok(Json.toJson(envelopeDeProdutos));
    }

    private void validaFormulario(DynamicForm formulario) {

        Map<String, String> parametros = formulario.data();

        parametros.keySet().forEach(chave -> {
            if(!ATRIBUTOS.contains(chave)) {
                formulario.reject(new ValidationError("Atributos inválidos", chave));
            }
        });
    }
}
