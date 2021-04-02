/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Cliente;
import br.com.associacao.modelo.Endereco;
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
public class ClienteDaoImplTest {

    private Session sessao;
    private Cliente cliente;
    private ClienteDao clienteDao;

    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

    //@Test
    public void testSalvarCliente() {
        System.out.println("pesquisarPorNome");

        cliente = new Cliente(
                Double.parseDouble(UtilGerador.gerarNumero(4)),
                null,
                UtilGerador.gerarNome(),
                UtilGerador.gerarEmail(),
                UtilGerador.gerarTelefoneFixo()
        );

        Endereco endereco = new Endereco(
                null,
                "Rua do Java", "Capoeiras",
                UtilGerador.gerarCidade(),
                "SC",
                UtilGerador.gerarNumero(3),
                "Blablabla"
        );

        cliente.setEndereco(endereco);
        endereco.setCliente(cliente);

        sessao = HibernateUtil.abrirConexao();
        clienteDao.salvarOuAlterar(cliente, sessao);
        Cliente clienteSalvo = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();

        assertNotNull(clienteSalvo.getId());
        assertNotNull(clienteSalvo.getEndereco());
    }
    
    //@Test
    public void testAlterarCliente() {
        System.out.println("Alterar Cliente");
        
        buscarClienteBd();
        
        cliente.setTelefone(UtilGerador.gerarTelefoneFixo());
        cliente.getEndereco().setCidade(UtilGerador.gerarCidade());
        
        sessao = HibernateUtil.abrirConexao();
        
        clienteDao.salvarOuAlterar(cliente, sessao);
        Cliente clienteAlterado = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        
        sessao.close();

        assertEquals(clienteAlterado.getTelefone(),cliente.getTelefone());
        assertEquals(clienteAlterado.getEndereco().getCidade(),cliente.getEndereco().getCidade());
    }
    
    @Test
    public void testExcluirCliente() {
        System.out.println("Excluir Cliente");
        buscarClienteBd();
        sessao = HibernateUtil.abrirConexao();
        clienteDao.excluir(cliente, sessao);
        Cliente clienteExcluido = clienteDao.pesquisarPorId(cliente.getId(), sessao);
        sessao.close();

        assertNull(clienteExcluido);
    }

    @Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");
        buscarClienteBd();
        sessao = HibernateUtil.abrirConexao();
        List<Cliente> clientes = clienteDao.pesquisarPorNome(cliente.getNome(), sessao);
        sessao.close();
        assertTrue(!clientes.isEmpty());
    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");

    }

    public Cliente buscarClienteBd() {

        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Cliente");
        List<Cliente> clientes = consulta.list();
        sessao.close();
        if (clientes.isEmpty()) {
            testSalvarCliente();
        } else {
            cliente = clientes.get(0);
        }

        return cliente;
    }

}
