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
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "idpessoa")
public class Cliente extends Pessoa {
    @Column(nullable = false, precision = 8, scale = 2)
    private double salario;
    
    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Endereco endereco;

    public Cliente() {
    }

    public Cliente(double salario, Long id, String nome, String email, String telefone) {
        super(id, nome, email, telefone);
        this.salario = salario;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
    
}
