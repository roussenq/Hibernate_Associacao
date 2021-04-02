/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Funcionario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author David
 */
public interface FuncionarioDao extends BaseDao<Funcionario, Long>{
    
    List<Funcionario> pesquisarPorNome(String nome, Session sessao) 
                                            throws HibernateException;
}
