package models;

import com.avaje.ebean.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class RegistroDeAcesso extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Usuario usuario;

    private String uri;
    private Date dataDeAcesso;

    public RegistroDeAcesso(Usuario usuario, String uri) {
        this.usuario = usuario;
        this.uri = uri;
        this.dataDeAcesso =  new Date();
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Date getDataDeAcesso() {
        return dataDeAcesso;
    }

    public void setDataDeAcesso(Date dataDeAcesso) {
        this.dataDeAcesso = dataDeAcesso;
    }
}
