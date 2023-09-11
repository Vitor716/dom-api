package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.produto.ProdutoEntradaDto;
import com.example.demo.dtos.produto.ProdutoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper mapper;
	
	public ProdutoSaidaDto criar(ProdutoEntradaDto produtoEntradaDto) {
		Produto produto = mapper.map(produtoEntradaDto, Produto.class);
		
		if(produtoEntradaDto.getIdCategoria() != null) {
			Optional<Categoria> optionalCategoria = categoriaRepository.findById(produtoEntradaDto.getIdCategoria());
			
			if(!optionalCategoria.isPresent()) {
				log.error("Criar, categoria não encontrada: id={}", produtoEntradaDto.getIdCategoria());
				throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
			}
			
			Categoria categoria  = optionalCategoria.get();
			produto.setCategoria(categoria);
		}
		
		log.info("criar, mapeamento: clienteEntradaDto={}, entity={}", produtoEntradaDto, produto);
		
		Produto registroProdutoBanco = produtoRepository.save(produto);
		
		ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);
		
		return produtoSaidaDto;
	}
	
	public List<ProdutoSaidaDto> listar(){
		List<Produto> produtos = produtoRepository.findAll();
		
		List<ProdutoSaidaDto> produtoSaidaDto = mapper.map(produtos, new TypeToken<List<ProdutoSaidaDto>>() {
		}.getType());
		
		log.info("listar, mapeamento: clienteSaidaDto={}", produtoSaidaDto);
		
		return produtoSaidaDto;
	}
	
	@Transactional
	public void excluir(Integer id) {
		log.info("excluir, mapeamento: id={}", id);
		
		produtoRepository.deleteById(id);
	}
}
