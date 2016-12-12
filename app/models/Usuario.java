package models;

import com.avaje.ebean.Model;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.List;

@Entity
public class Usuario extends Model {

    @Id
    @GeneratedValue
    private Long id;

    @Constraints.Required(message = "Campo título é obrigatório")
    private String nome;

    @Constraints.Required(message = "Campo email é obrigatório")
    private String email;

    @Constraints.Required(message = "Campo senha é obrigatório")
    private String senha;

    private boolean verificado;

    //um usuario para um token, para que nao cria uma referencia na tabela usuario e sim so no modelo
    @OneToOne(mappedBy = "usuario")
    private TokenDaApi token;

    @OneToMany(mappedBy = "usuario")
    private List<RegistroDeAcesso> acessos;

    //Declarar o papel padrão do usuário que é o cliente
    @Enumerated(EnumType.STRING)
    private Papel papel = Papel.CLIENTE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isVerificado() {
        return verificado;
    }

    public void setVerificado(boolean verificado) {
        this.verificado = verificado;
    }

    public TokenDaApi getToken() {
        return token;
    }

    public void setToken(TokenDaApi token) {
        this.token = token;
    }

    public List<RegistroDeAcesso> getAcessos() {
        return acessos;
    }

    public void setAcessos(List<RegistroDeAcesso> acessos) {
        this.acessos = acessos;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    public boolean isAdmin() {
        return papel == Papel.ADMIN;
    }
}
