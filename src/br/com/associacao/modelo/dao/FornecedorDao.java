/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Fornecedor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;


/**
 *
 * @author User
 */
    public interface FornecedorDao extends BaseDao<Fornecedor, Long>{
        
        List<Fornecedor> pesquisarPorNome(String nome, Session sessao) throws HibernateException;
        
        Fornecedor pesquisarPorIdComEndereco(Long id, Session sessao) throws HibernateException;
    
}
