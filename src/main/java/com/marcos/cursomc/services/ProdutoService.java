package com.marcos.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.cursomc.domain.Produto;
import com.marcos.cursomc.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository prod;
	
	public Produto find(Integer id) {
		Optional<Produto> obj = prod.findById(id);
		return obj.orElse(null);
	}
}
