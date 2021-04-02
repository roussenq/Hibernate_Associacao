/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo;

import javax.persistence.*;

/**
 *
 * @author David
 */
@Entity
@Table(name = "funcionario")
@PrimaryKeyJoinColumn(name = "idPessoa")  //nome da chave estrangeira
public class Funcionario extends Pessoa{
    
    @Column(nullable = false, length = 8, unique = true)
    private String cracha;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "funcionario", fetch = FetchType.EAGER) //quando salvar um funcionario salva tb um endereco, mappeby na classe que não tem a pk, EAGER busca a lista toda = +memoria
    private Endereco endereco;

    public Funcionario() {
    }

    public Funcionario(Long id, String nome, String email, String telefone,String cracha) {
        super(id, nome, email, telefone);
        this.cracha = cracha;
    }

    public String getCracha() {
        return cracha;
    }

    public void setCracha(String cracha) {
        this.cracha = cracha;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
