/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;


import br.com.associacao.modelo.Cliente;
import br.com.associacao.modelo.Endereco;
import br.com.associacao.modelo.Fornecedor;
import br.com.associacao.modelo.Funcionario;
import br.com.associacao.modelo.Pessoa;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author David
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory; //Singleton
    
    static {
        try {
            Configuration cfg = new Configuration(); //tem que colocar na sequencia pai/filho
            cfg.addAnnotatedClass(Pessoa.class);
            cfg.addAnnotatedClass(Funcionario.class);
            cfg.addAnnotatedClass(Endereco.class);
            cfg.addAnnotatedClass(Cliente.class);
            cfg.addAnnotatedClass(Fornecedor.class);
        

            cfg.configure("/br/com/associacao/dao/hibernate.cfg.xml");
            StandardServiceRegistryBuilder build = new StandardServiceRegistryBuilder().
                                           applySettings(cfg.getProperties());
            sessionFactory = cfg.buildSessionFactory(build.build());
        } catch (HibernateException ex) {
            System.err.println("Erro ao criar Hibernate util." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static Session abrirConexao() {
        return sessionFactory.openSession();
    }
}
