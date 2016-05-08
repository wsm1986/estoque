package br.com.caelum.estoque.dao;

import java.util.List;

import br.com.caelum.estoque.Produto;

public interface ProdutoDAO {
	void salvar(Produto produuto);
	void alterar(Produto produto);
	List<Produto> listar();
	Produto buscarPorId(Long id);
	void remover(Long id);
	Integer estoqueAtual(Produto produto);
}
