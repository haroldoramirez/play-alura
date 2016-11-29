package daos;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;
import models.Produto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ProdutoDAO {

    private Model.Finder<Long, Produto> produtos = new Model.Finder<>(Produto.class);

    public Optional<Produto> comCodigo(String codigo) {
        Produto produto = produtos.query()
                .where()
                .eq("codigo", codigo).findUnique();

        return Optional.ofNullable(produto);
    }

    public List<Produto> todos() {
        return produtos.all();
    }

    public List<Produto> doTipo(String tipo) {
        return produtos.where().eq("tipo", tipo).findList();
    }

    public List<Produto> comFiltro(Map<String, String> parametros) {

        ExpressionList<Produto> consulta = produtos.where();

        parametros.entrySet().forEach(entrada -> {
            consulta.eq(entrada.getKey(), entrada.getValue());
        });

        return consulta.findList();
    }
}
