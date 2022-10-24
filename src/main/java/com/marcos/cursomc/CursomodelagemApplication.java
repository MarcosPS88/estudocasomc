package com.marcos.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.marcos.cursomc.domain.Categoria;
import com.marcos.cursomc.domain.Cidade;
import com.marcos.cursomc.domain.Cliente;
import com.marcos.cursomc.domain.Endereco;
import com.marcos.cursomc.domain.Estado;
import com.marcos.cursomc.domain.Produto;
import com.marcos.cursomc.domain.enums.TipoCliente;
import com.marcos.cursomc.repositories.CategoriaRepository;
import com.marcos.cursomc.repositories.CidadeRepository;
import com.marcos.cursomc.repositories.ClienteRepository2;
import com.marcos.cursomc.repositories.EnderecoRepository2;
import com.marcos.cursomc.repositories.EstadoRepository;
import com.marcos.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomodelagemApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository2 clienteRepository;
	@Autowired
	private EnderecoRepository2 enderecoRepository;
	
		
	public static void main(String[] args) {
		SpringApplication.run(CursomodelagemApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritório");
	
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
		Produto p1 = new Produto("Computador", 2000.00);
		Produto p2 = new Produto("Impressora", 800.00);
		Produto p3 = new Produto("Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategoria().addAll(Arrays.asList(cat1));
		p2.getCategoria().addAll(Arrays.asList(cat1, cat2));
		p3.getCategoria().add(cat1);
		
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		
		Estado est1 = new Estado("São Paulo"); 
		Estado est2 = new Estado("Minas Gerais");
		
		Cidade cid1 = new Cidade("São Paulo");
		Cidade cid2 = new Cidade("Uberlândia");
		Cidade cid3 = new Cidade("Campinas");
		
		cid1.setEstado(est1);
		cid2.setEstado(est2);
		cid3.setEstado(est1);
		
		est1.getCidades().addAll(Arrays.asList(cid1, cid3));
		est2.getCidades().addAll(Arrays.asList(cid2));

		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2, cid3));
		
		Cliente cli1 = new Cliente("Maria Silva", "maria@gmail.com", "33216546546", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("321653216", "95846513"));
		
		Endereco e1 = new Endereco("Rua Flores", "300", "Apto 303", "Jardim", "38216532", cli1, cid2);
		Endereco e2 = new Endereco("Av Matos", "105", "Sala 800", "Centro", "33874512", cli1, cid1);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
			
					
	}
}
