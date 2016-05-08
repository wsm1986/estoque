package br.com.caelum.estoque.dao;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.caelum.estoque.Movimentacao;
import br.com.caelum.estoque.Produto;
import br.com.caelum.estoque.TipoMovimentaca;

@Service
public class GeradorDeMovimentacao {

	private final ProdutoDAO dao;

	@Autowired
	public GeradorDeMovimentacao(ProdutoDAO dao) {
		this.dao = dao;
	}
	
	public Movimentacao gerarMovimentacao(Produto produto){
		Movimentacao mov = new Movimentacao();
		mov.setProduto(produto);
		mov.setData(Calendar.getInstance());
		
		Integer quantidadeAtual  = dao.estoqueAtual(produto);
		if(produto.getQuantidade()>quantidadeAtual){
			mov.setTipo(TipoMovimentaca.ENTRADA);
		}else{
			mov.setTipo(TipoMovimentaca.SAIDA);
		}
		mov.setQuantidade(Math.abs((produto.getQuantidade()-quantidadeAtual)));
		return mov;
	}
	
}
