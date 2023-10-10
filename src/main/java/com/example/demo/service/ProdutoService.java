package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ProdutoEntradaDto;
import com.example.demo.dtos.ProdutoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Categoria;
import com.example.demo.model.Produto;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.validator.ProdutoValidator;

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
	private ProdutoValidator produtoValidator;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	public ProdutoSaidaDto criar(ProdutoEntradaDto produtoEntradaDto) {
		try {
			produtoValidator.criar(produtoEntradaDto);
			Produto produto = mapper.map(produtoEntradaDto, Produto.class);

			if (produtoEntradaDto.getIdCategoria() != null) {
				Optional<Categoria> optionalCategoria = categoriaRepository
						.findById(produtoEntradaDto.getIdCategoria());

				if (!optionalCategoria.isPresent()) {
					log.error("Criar, categoria não encontrada: id={}", produtoEntradaDto.getIdCategoria());
					throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
				}

				Categoria categoria = optionalCategoria.get();
				produto.setCategoria(categoria);
			}

			log.info("criar, mapeamento: clienteEntradaDto={}, entity={}", produtoEntradaDto, produto);

			Produto registroProdutoBanco = produtoRepository.save(produto);

			ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);

			return produtoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public void editar(Integer id, ProdutoEntradaDto produtoEntradaDto) {
		try {
			produtoValidator.editar(id, produtoEntradaDto);

			Optional<Produto> optional = produtoRepository.findById(id);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
			}

			Produto produto = optional.get();

			if (produtoEntradaDto.getIdCategoria() != null) {
				Optional<Categoria> optionalCategoria = categoriaRepository
						.findById(produtoEntradaDto.getIdCategoria());

				if (!optionalCategoria.isPresent()) {
					log.error("editar, categoria não encontrado: id={}", produtoEntradaDto.getIdCategoria());
					throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
				}

				Categoria categoria = optionalCategoria.get();
				produto.setCategoria(categoria);
			}

			mapper.map(produtoEntradaDto, produto);

			log.info("editar, mapeamento: produtoEntradaDto={}, entity={}", produtoEntradaDto, produto);

			produtoRepository.save(produto);
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public List<ProdutoSaidaDto> listar() {
		try {
			List<Produto> produtos = produtoRepository.findAll();

			List<ProdutoSaidaDto> produtoSaidaDto = mapper.map(produtos, new TypeToken<List<ProdutoSaidaDto>>() {
			}.getType());

			log.info("listar, mapeamento: clienteSaidaDto={}", produtoSaidaDto);

			return produtoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public ProdutoSaidaDto pegarUm(Integer id) {
		try {
			Optional<Produto> optional = produtoRepository.findById(id);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
			}

			Produto registroProdutoBanco = optional.get();

			ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);

			return produtoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public ProdutoSaidaDto pegarUmPorNome(String nome) {
		try {
			Optional<Produto> optional = produtoRepository.findByNome(nome);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
			}

			Produto registroProdutoBanco = optional.get();

			ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);

			return produtoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public ProdutoSaidaDto pegarProdutoPorCategoriaId(Integer id) {
		try {
			Optional<Produto> optional = produtoRepository.findProdutosByCategoriaId(id);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
			}

			Produto registroProdutoBanco = optional.get();

			ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);

			return produtoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public ProdutoSaidaDto pegarProdutoPorNomeCategoria(String nome) {
		try {
			Optional<Produto> optional = produtoRepository.findProdutosByCategoriaNome(nome);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
			}

			Produto registroProdutoBanco = optional.get();

			ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);

			return produtoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	@Transactional
	public void excluir(Integer id) {
		try {
			produtoValidator.excluir(id);

			log.info("excluir, mapeamento: id={}", id);

			produtoRepository.deleteById(id);
		} catch (Exception e) {
			log.error("excluir, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}
}
