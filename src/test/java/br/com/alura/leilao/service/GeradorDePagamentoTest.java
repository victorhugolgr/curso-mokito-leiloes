package br.com.alura.leilao.service;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.com.alura.leilao.dao.PagamentoDao;
import br.com.alura.leilao.model.Lance;
import br.com.alura.leilao.model.Leilao;
import br.com.alura.leilao.model.Usuario;

class GeradorDePagamentoTest {

	@Mock
	private PagamentoDao pagamentoDao;
	
	private GeradorDePagamento geradorDePagamento;
	
	@BeforeEach
	public void beforeEach() {
		MockitoAnnotations.initMocks(this);
		this.geradorDePagamento = new GeradorDePagamento(pagamentoDao);
	}
	
	@Test
	void deveriaCriarPagamentoParaVencedorDoLeilao() {
		Leilao leilao = leilao();
		Lance lanceVencedor = leilao.getLanceVencedor();
		geradorDePagamento.gerarPagamento(lanceVencedor);
		Mockito.verify(pagamentoDao).salvar(Mockito.any());
	}
	
	private Leilao leilao() {

		Leilao leilao = new Leilao("Celular", new BigDecimal("500"), new Usuario("Fulano"));
		
		Lance segundo = new Lance(new Usuario("Ciclano"), new BigDecimal("900"));
		
		leilao.propoe(segundo);
		leilao.setLanceVencedor(segundo);
		return leilao;
	}

}
