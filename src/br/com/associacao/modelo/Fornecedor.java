/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author User
 */

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends Pessoa{
    
    private String descricao;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fornecedor")
    private List<Endereco> enderecos;

    public Fornecedor() {
    }

    public Fornecedor(Long id, String nome, String email, String telefone,String descricao) {
        super(id, nome, email, telefone);
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }
     
    
    
    
    
}
