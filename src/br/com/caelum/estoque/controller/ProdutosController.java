package br.com.caelum.estoque.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.estoque.Movimentacao;
import br.com.caelum.estoque.Produto;
import br.com.caelum.estoque.dao.GeradorDeMovimentacao;
import br.com.caelum.estoque.dao.MovimentacaoDao;
import br.com.caelum.estoque.dao.ProdutoDAO;

@Controller
@RequestMapping("/produtos")
public class ProdutosController {

	@Autowired
	private ProdutoDAO produtoDao;

	@Autowired
	private GeradorDeMovimentacao geradorDeMovimentacao;

	@Autowired
	private MovimentacaoDao movimentacaoDao;

	@RequestMapping("/index")
	public String index() {
		return "produtos/index";
	}

	@RequestMapping(value = "/lista", method = RequestMethod.GET)
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("produtos/lista");
		modelAndView.addObject(produtoDao.listar());
		return modelAndView;
	}

	@RequestMapping(value = "/mostrar/{id}", method = RequestMethod.GET)
	public ModelAndView mostrar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("produtos/mostra");
		modelAndView.addObject(produtoDao.buscarPorId(id));
		return modelAndView;
	}

	@RequestMapping(value = "/form", method = RequestMethod.GET)
	public String form(Model model) {
		model.addAttribute(new Produto());
		return "produtos/form";
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Valid Produto produto, BindingResult result) {
		if (result.hasErrors()) {
			return "produtos/form";
		}
		produtoDao.salvar(produto);
		return "redirect:/produtos/lista";
	}

	@RequestMapping(value = "/editar/{id}", method = RequestMethod.GET)
	public ModelAndView editar(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("produtos/editar");
		Produto produto = produtoDao.buscarPorId(id);
		modelAndView.addObject(produto);
		return modelAndView;
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	@Transactional
	public String alterar(@Valid Produto produto, BindingResult result) {
		if (result.hasErrors()) {
			return "produtos/editar";
		}
		Movimentacao movimentacao = geradorDeMovimentacao
				.gerarMovimentacao(produto);
		movimentacaoDao.salvar(movimentacao);
		produtoDao.alterar(produto);
		return "redirect:/produtos/lista";
	}

	@RequestMapping(value = "/remover/{id}", method = RequestMethod.GET)
	public String remover(@PathVariable Long id) {
		produtoDao.remover(id);
		return "redirect:/produtos/lista";
	}

}
