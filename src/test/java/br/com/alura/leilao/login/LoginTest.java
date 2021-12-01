package br.com.alura.leilao.login;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginTest {

	private static final String URL_LOGIN_ERROR = "http://localhost:8080/login?error";
	private static final String URL_LOGIN = "http://localhost:8080/login";
	private static final String URL_PAGINA_RESTRITA = "http://localhost:8080/leiloes/2";

	private LoginPage loginPage;

	@BeforeEach
	public void beforeEach() {

		this.loginPage = new LoginPage();
	}

	@AfterEach
	public void afterEach() {

		this.loginPage.fechar();
	}

	@Test
	public void deveEfetuarLoginComDadosValidos() {

		loginPage.abrirPagina(URL_LOGIN);

		loginPage.preencherFormulario("fulano", "pass");

		loginPage.enviarFormulario();

		Assertions.assertFalse(loginPage.getPaginaAtual().equals(URL_LOGIN));

		Assertions.assertEquals("fulano", loginPage.getUsuarioLogado());
	}

	@Test
	public void naoDeveEfetuarLoginComDadosInvalidos() {

		loginPage.abrirPagina(URL_LOGIN);

		loginPage.preencherFormulario("invalido", "123456");
		
		loginPage.enviarFormulario();

		Assertions.assertTrue(loginPage.getPaginaAtual().equals(URL_LOGIN_ERROR));
		Assertions.assertTrue(loginPage.contemTexto("Usuário e senha inválidos."));		
		Assertions.assertNull(loginPage.getUsuarioLogado());
	}

	@Test
	public void naoDeveAcessarPaginaRestritaSemEstarLogado() {

		loginPage.abrirPagina(URL_PAGINA_RESTRITA);

		Assertions.assertTrue(loginPage.getPaginaAtual().equals(URL_LOGIN));
		Assertions.assertFalse(loginPage.contemTexto("Dados do Leilão"));
	}

}
