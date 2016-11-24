package br.edu.ifsul.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "servico")
public class Servico implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_servico", sequenceName = "seq_servico_id", allocationSize = 1)
    @GeneratedValue(generator = "seq_servico", strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A data deve ser informada")
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Calendar data;

    @NotBlank(message = "O nome deve ser informado")
    @Length(max = 50, message = "O nome não deve ultrapassar {max} caracteres")
    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(name = "descricao", columnDefinition = "text")
    private String descricao;

    @NotNull(message = "O preço deve ser informado")
    @Column(name = "preco", nullable = false, columnDefinition = "decimal(12,2)")
    private Double preco;

    //cliente que contratou o serviço      
    @NotNull(message = "O Cliente não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "pessoa", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;

    //funcionarios que fazem parte do serviço
    @OneToMany(mappedBy = "servico", cascade = CascadeType.ALL,
            orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Funcionario> funcionarios;

        
    /*desejos de serviços do cliente 
    @ManyToMany
    @JoinTable(name = "desejos_servicos",
            joinColumns
            = @JoinColumn(name = "servico", referencedColumnName = "id",
                    nullable = false),
            inverseJoinColumns
            = @JoinColumn(name = "pessoa", referencedColumnName = "id",
                    nullable = false))
    private List<Pessoa> desejam = new ArrayList<>();*/

    //funcionarios que fazem parte do serviço
    public Servico() {
    }
    
    //funcionarios que fazem parte do serviço
    public void adicionarFuncionario(Funcionario obj) {
        obj.setServico(this);
        this.funcionarios.add(obj);
    }

    //funcionarios que fazem parte do serviço
    public void removerFuncionario(int idx) {
        this.funcionarios.remove(idx);
    }
    
    
     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    /*cliente ManyToMany
    public List<Pessoa> getDesejam() {
        return desejam;
    }

    //cliente ManyToMany
    public void setDesejam(List<Pessoa> desejam) {
        this.desejam = desejam;
    }
*/
    //cliente ManyToOne
    public Pessoa getPessoa() {
        return pessoa;
    }
    
    //cliente ManyToOne
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    //Funcionarios OneToMany
    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    //Funcionarios OneToMany
    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Servico other = (Servico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

   

}
