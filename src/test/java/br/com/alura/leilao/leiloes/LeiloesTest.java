package br.com.alura.leilao.leiloes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import br.com.alura.leilao.login.LoginPage;

public class LeiloesTest {

	private LeiloesPage leiloesPage;

	@AfterEach
	public void afterEach() {
		this.leiloesPage.fechar();
	}

	@Test
	public void deveCadastrarLeilao() {
		LoginPage loginPage = new LoginPage();
		loginPage.abrirPaginaLogin();
		loginPage.preencherFormulario("fulano", "pass");
		leiloesPage = loginPage.enviarFormulario();
		CadastroLeilaoPage cadastroLeilaoPage = leiloesPage.abrirPaginaCadatro();

		String dataHoje = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		String nomeLeilao = "Leilao do dia " + dataHoje;
		String valor = "500.00";

		leiloesPage = cadastroLeilaoPage.cadastrarLeilao(nomeLeilao, valor, dataHoje);

		Assertions.assertTrue(leiloesPage.isLeilaoCadastradoUltimaLinha(nomeLeilao, valor, dataHoje));
	}

}
