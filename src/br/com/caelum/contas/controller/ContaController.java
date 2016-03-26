package br.com.caelum.contas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.contas.dao.ContaDAO;
import br.com.caelum.contas.modelo.Conta;

@Scope(value = "request")
@Controller
public class ContaController {
	
	@RequestMapping("/adicionaConta")
	public String adiciona(@Valid Conta conta, BindingResult result){
		System.out.println("entrou");
		if(result.hasErrors()) {
			System.out.println("entrou no erro");
		      return "Conta/formulario";
		}
		System.out.println("Conta adicionada: "+ conta.getDescricao());
		
		ContaDAO dao = new ContaDAO();
		dao.adiciona(conta);
		return "redirect:listaContas";
	}
	
	@RequestMapping(value="/form")
	public String form() {
		return "Conta/formulario";
	}
	 
	@RequestMapping("/removeConta")
	public String remove(Conta conta){
		ContaDAO dao = new ContaDAO();
		dao.remove(conta);
		return "redirect:listaContas";
	}
	 
	@RequestMapping("/listaContas")
	public ModelAndView  lista() {
		ContaDAO dao = new ContaDAO();
		List<Conta> contas = dao.lista();
	   
	   ModelAndView mv = new ModelAndView("Conta/lista");
	   mv.addObject("contas", contas);
	   return mv;
	 }
	
	@RequestMapping("/mostraConta")
	public String mostra(Long id, Model model){
		ContaDAO dao = new ContaDAO();
		model.addAttribute("conta", dao.buscaPorId(id));
		return "Conta/mostra";
	}
	
	@RequestMapping("/alteraConta")
	public String altera(Conta conta) {
		ContaDAO dao = new ContaDAO();
		dao.altera(conta);
		
		return "redirect:listaContas";
	}
}
