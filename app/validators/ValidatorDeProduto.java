package validators;

import daos.ProdutoDAO;
import models.Produto;
import play.data.Form;
import play.data.validation.ValidationError;

import javax.inject.Inject;

public class ValidatorDeProduto {

    @Inject
    private ProdutoDAO produtoDAO;

    public boolean temErros(Form<Produto> formulario) {

        String codigo = formulario.field("codigo").value();

        double preco = -1.0;

        try {
            preco = Double.parseDouble(formulario.field("preco").value());
        } catch (Exception e) {
            formulario.reject(new ValidationError("preco", "O preço do produto deve ser um número!"));
        }

        if (preco < 0.0) {
            formulario.reject(new ValidationError("preco", "O preço do produto deve ser maior que 0!"));
        }

        if (produtoDAO.comCodigo(codigo).isPresent()) {
            formulario.reject(new ValidationError("codigo", "Já existe um produto com este código!"));
        }

        return formulario.hasErrors();

    }

}
