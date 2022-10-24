package com.marcos.cursomc;

import java.text.SimpleDateFormat;
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
import com.marcos.cursomc.domain.PagamentoComBoleto;
import com.marcos.cursomc.domain.PagamentoComCartao;
import com.marcos.cursomc.domain.Pedido;
import com.marcos.cursomc.domain.Produto;
import com.marcos.cursomc.domain.enums.EstadoPagamento;
import com.marcos.cursomc.domain.enums.TipoCliente;
import com.marcos.cursomc.repositories.CategoriaRepository;
import com.marcos.cursomc.repositories.CidadeRepository;
import com.marcos.cursomc.repositories.ClienteRepository;
import com.marcos.cursomc.repositories.EnderecoRepository;
import com.marcos.cursomc.repositories.EstadoRepository;
import com.marcos.cursomc.repositories.PagamentoRepository;
import com.marcos.cursomc.repositories.PedidoRepository;
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
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
		
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
			
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	
		
		Pedido ped1 = new Pedido(sdf.parse("30/09/2017 10:32"), cli1, e1 );
		PagamentoComCartao pagto1 = new PagamentoComCartao(EstadoPagamento.QUITADO, ped1, 6);
		
		Pedido ped2 = new Pedido(sdf.parse("10/10/2017 19:35"), cli1, e2);
		PagamentoComBoleto pagto2 = new PagamentoComBoleto(EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 23:59"), null, ped2);
		
		ped1.setPagamento(pagto1);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
				
					
	}
}
