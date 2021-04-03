/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.associacao.modelo.dao;

import br.com.associacao.modelo.Endereco;
import br.com.associacao.modelo.Fornecedor;
import static br.com.utilitario.UtilGerador.*;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author User
 */
public class FornecedorDaoImplTest {

    private Fornecedor fornecedor;
    private FornecedorDao fornecedorDao;
    private Session sessao;

    public FornecedorDaoImplTest() {
        fornecedorDao = new FornecedorDaoImpl();
    }

    //@Test
    public void testSalvarFornecedor() {
        System.out.println("Salvar Fornecedor");
        List<Endereco> enderecos = new ArrayList<>();
        fornecedor = new Fornecedor(
                null,
                gerarNome(),
                gerarEmail(),
                gerarTelefoneFixo(),
                "Blablabla.123.."
        );
        enderecos.add(gerarEndereco());
        enderecos.add(gerarEndereco());
        fornecedor.setEnderecos(enderecos);
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        sessao.close();
        assertNotNull(fornecedor.getEnderecos());
        assertNotNull(fornecedor.getId());
    }

    private Endereco gerarEndereco() {
        Endereco endereco = new Endereco(
                null,
                "Rua do Python",
                "Centro",
                gerarCidade(),
                "Santa Catarina",
                gerarNumero(3),
                "Blablabla..."
        );
        return endereco;
    }

    @Test
    public void testAlterarFornecedor() {
        System.out.println("Alterar Fornecedor");
        
        buscarFornecedorBd();
        
        fornecedor.setNome("David Roussenq Maria");
        //fornecedor.getEnderecos().get(0).setCidade("Florianopolis");

        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.salvarOuAlterar(fornecedor, sessao);
        Fornecedor fornecedorAlt = fornecedorDao.pesquisarPorIdComEndereco(fornecedor.getId(), sessao);
        sessao.close();

       assertEquals(fornecedor.getNome(), fornecedorAlt.getNome());
      // assertEquals(fornecedor.getEnderecos().get(0).getCidade(), fornecedorAlt.getEnderecos().get(0).getCidade());
    }

    //@Test
    public void testExcuirFornecedor() {
        System.out.println("Excluir Fornecedor");

        buscarFornecedorBd();
        System.out.println("será excluido\nNome: " + fornecedor.getNome() + "\nId:" + fornecedor.getId());
        sessao = HibernateUtil.abrirConexao();
        fornecedorDao.excluir(fornecedor, sessao);
        sessao.close();
        sessao = HibernateUtil.abrirConexao();
        Fornecedor fornecedorExcluido = fornecedorDao.pesquisarPorIdComEndereco(fornecedor.getId(), sessao);
        sessao.close();

        assertNull(fornecedorExcluido);

    }

    //@Test
    public void testPesquisarPorId() {
        System.out.println("pesquisarPorId");

    }

    //@Test
    public void testPesquisarPorNome() {
        System.out.println("pesquisarPorNome");

        buscarFornecedorBd();
        String nome = fornecedor.getNome();
        int letra = nome.indexOf(" ");
        System.out.println("letra " + letra);
        
    }

    public Fornecedor buscarFornecedorBd() {
        sessao = HibernateUtil.abrirConexao();
        Query consulta = sessao.createQuery("from Fornecedor f join fetch f.enderecos ");
        List<Fornecedor> fornecedores = consulta.list();
        sessao.close();

        if (fornecedores.isEmpty()) {
            testSalvarFornecedor();
        } else {
            fornecedor = fornecedores.get(0);

        }
        return fornecedor;

    }
}
