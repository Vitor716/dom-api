package com.example.demo.service;

import java.util.Optional;

import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ItemPedidoEntradaDto;
import com.example.demo.dtos.ItemPedidoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.ItemPedido;
import com.example.demo.model.Produto;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.validator.ItemPedidoValidator;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ItemPedidoService {
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private ItemPedidoValidator itemPedidoValidator;
	
	public ItemPedidoSaidaDto criar(ItemPedidoEntradaDto itemPedidoEntradaDto) {
		try {
			//fazer validação se existe o produto 
			
			ItemPedido itemPedido = mapper.map(itemPedidoEntradaDto, ItemPedido.class);
			
			if(itemPedidoEntradaDto.getIdProduto() != null) {
				Optional<Produto> optionalProduto = produtoRepository.findById(itemPedidoEntradaDto.getIdProduto());
			
				if(!optionalProduto.isPresent()) {
					log.error("Criar, produto não encontrado: id={}", itemPedidoEntradaDto.getIdProduto());
					throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
				}
				
				Produto produto = optionalProduto.get();
				itemPedido.setProduto(produto);
			}
			
			ItemPedido registroItemPedidoBanco = itemPedidoRepository.save(itemPedido);
			
			ItemPedidoSaidaDto itemPedidoSaidaDto = mapper.map(registroItemPedidoBanco, ItemPedidoSaidaDto.class);
			
			return itemPedidoSaidaDto;	
			
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error("criar, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}
}
