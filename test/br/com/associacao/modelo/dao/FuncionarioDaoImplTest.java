/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Endereco;
import br.com.associacao.modelo.Funcionario;
import br.com.utilitario.UtilGerador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/**
 *
 * @author David
 */
public class FuncionarioDaoImplTest {
    
    //variaveis globais
    private Funcionario funcionario;
    private FuncionarioDao funcionarioDao;
    private Session sessao;
    
    public FuncionarioDaoImplTest() {
        funcionarioDao = new FuncionarioDaoImpl();
    }

    @Test
    public void testSalvarFuncionario() {
        System.out.println("Salvar Funcionario");
        funcionario = new Funcionario(
                null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTelefoneFixo(),
                UtilGerador.gerarNumero(8)
        );
        
        Endereco endereco = new Endereco(
                null,
                "Rua dos Programadores",
                "Centro",
                UtilGerador.gerarCidade(),
                "SC",
                UtilGerador.gerarNumero(5),
                "Blablabla"
        );
        
        sessao = HibernateUtil.abrirConexao();
        
        funcionario.setEndereco(endereco);
        endereco.setFuncionario(funcionario);
        
        funcionarioDao.salvarOuAlterar(funcionario, sessao);
        
        sessao.close();        

        assertNotNull(funcionario.getId());
        assertNotNull(endereco.getId());
    }
    @Test
    public void testPesquisarFuncionarioPorNome() {
        System.out.println("Funcionario pesquisar Por Nome");
        
        buscarFuncionarioBd();
        String parteDoNome = "es";
        
        sessao = HibernateUtil.abrirConexao();
        
        List<Funcionario> funcionarios = funcionarioDao.pesquisarPorNome(parteDoNome, sessao);
        
        sessao.close();
        assertTrue(!funcionarios.isEmpty());
    }
    
    @Test
    public void testAlterarFuncionario() {
        System.out.println("Alterar Funcionario");
        
        buscarFuncionarioBd();
        
        funcionario.setNome("Guest01");
        funcionario.setEmail("guest01@alunos.sc.senac.br");
        
        sessao = HibernateUtil.abrirConexao();
        funcionarioDao.salvarOuAlterar(funcionario, sessao);
        Funcionario funcionarioNovo = funcionarioDao.pesquisarPorId(funcionario.getId(), sessao);
        
        sessao.close();
        assertEquals(funcionarioNovo.getNome(),funcionario.getNome());
        assertEquals(funcionarioNovo.getEmail(),funcionario.getEmail());
    }

    @Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");
    }
    
    @Test
    public void testExcluirFuncionario() {
        System.out.println("Excluir Funcionario");
        
        buscarFuncionarioBd();
        sessao = HibernateUtil.abrirConexao();
        funcionarioDao.excluir(funcionario, sessao);
        
        Funcionario funcionarioExcluido = 
                funcionarioDao.pesquisarPorId(funcionario.getId(), sessao);
        
        sessao.close();
        assertNull(funcionarioExcluido);
    }
    
    
    public Funcionario buscarFuncionarioBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Funcionario");
        List<Funcionario> funcionarios = consulta.list();
        sessao.close();
        
        if(funcionarios.isEmpty()){
            testSalvarFuncionario();
        }else{
            funcionario = funcionarios.get(0);
        }
        return funcionario;
    }
    
}
