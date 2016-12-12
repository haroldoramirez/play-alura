package models;

import akka.util.Crypt;
import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
public class TokenDaApi extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Usuario usuario;

    private String codigo;

    private Date expicacao;

    public TokenDaApi(Usuario usuario) {
        this.usuario = usuario;
        this.expicacao = new Date();
        this.codigo = Crypt.sha1(Crypt.generateSecureCookie()+expicacao.toString()+usuario.toString());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getExpicacao() {
        return expicacao;
    }

    public void setExpicacao(Date expicacao) {
        this.expicacao = expicacao;
    }
}
