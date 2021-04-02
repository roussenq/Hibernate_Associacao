/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Cliente;
import java.util.List;
import org.hibernate.*;

/**
 *
 * @author David
 */
public interface ClienteDao extends BaseDao<Cliente, Long>{
    
    List<Cliente> pesquisarPorNome(String nome, Session sessao) 
                                            throws HibernateException;
}
