/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Funcionario;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;


public class FuncionarioDaoImpl extends BaseDaoImpl<Funcionario, Long> implements FuncionarioDao, Serializable {

    @Override
    public List<Funcionario> pesquisarPorNome(String nome, Session sessao) throws HibernateException {
            Query consulta = sessao.createQuery("from Funcionario where nome like :nomeHQL"); //HQL)
            consulta.setParameter("nomeHQL", "%" + nome + "%");
        return consulta.list();
    }

    @Override
    public Funcionario pesquisarPorId(Long id, Session sessao) throws HibernateException {
        return (Funcionario)sessao.get(Funcionario.class, id);
    }
    
}
