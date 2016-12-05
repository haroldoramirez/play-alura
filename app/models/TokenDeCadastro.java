package models;

import akka.util.Crypt;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TokenDeCadastro extends Model {

    @Id
    @GeneratedValue
    private Long id;

    private String codigo;

    @OneToOne
    private Usuario usuario;

    public TokenDeCadastro(Usuario usuario) {
        this.usuario = usuario;
        this.codigo = Crypt.sha1(usuario.getNome()+usuario.getEmail()+Crypt.generateSecureCookie());
    }

    public Long getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
