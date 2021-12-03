package br.com.alura.leilao.leiloes;

import java.util.function.BooleanSupplier;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LeiloesPage {

	private static final String URL_CADASTRO_LEILAO = "http://localhost:8080/leiloes/new";

	private WebDriver browser;

	public LeiloesPage(WebDriver browser) {

		this.browser = browser;

	}

	public void fechar() {

		this.browser.quit();
	}

	public void abrirPagina(String urlLogin) {

		this.browser.navigate().to(urlLogin);

	}

	public void preencherFormulario(String username, String password) {

		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);

	}

	public void enviarFormulario() {

		browser.findElement(By.id("login-form")).submit();
	}

	public String getPaginaAtual() {

		return browser.getCurrentUrl();
	}

	public String getUsuarioLogado() {
		try {
			return browser.findElement(By.id("usuario-logado")).getText();

		} catch (NoSuchElementException e) {
			return null;
		}

	}

	public Boolean contemTexto(String conteudo) {

		return browser.getPageSource().contains(conteudo);
	}

	public CadastroLeilaoPage abrirPaginaCadatro() {
		this.browser.navigate().to(URL_CADASTRO_LEILAO);
		return new CadastroLeilaoPage(browser);

	}

	public boolean isLeilaoCadastradoUltimaLinha(String nomeLeilao, String valor, String dataHoje) {
		WebElement linhaTabela = this.browser.findElement(By.cssSelector("#tabela-leiloes tbody tr:last-child"));
		WebElement colunaNome = linhaTabela.findElement(By.cssSelector("td:nth-child(1)"));
		WebElement colunaDataAbertura = linhaTabela.findElement(By.cssSelector("td:nth-child(2)"));
		WebElement colunaValorInicial = linhaTabela.findElement(By.cssSelector("td:nth-child(3)"));

		return colunaNome.getText().equals(nomeLeilao) && colunaDataAbertura.getText().equals(dataHoje)
				&& colunaValorInicial.getText().equals(valor);
	}

}
