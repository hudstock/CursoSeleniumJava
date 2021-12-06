package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import br.com.alura.leilao.PageObject;
import br.com.alura.leilao.leiloes.LeiloesPage;

public class LoginPage extends PageObject {
	

	private static final String URL_LOGIN = "http://localhost:8080/login";
	private static final String URL_PAGINA_RESTRITA = "http://localhost:8080/leiloes/2";	

	public LoginPage() {
		super(null);		
	}
	
	public void abrirPaginaLogin() {
		this.browser.navigate().to(URL_LOGIN);
	}

	public void abrirPaginaRestrita() {
		this.browser.navigate().to(URL_PAGINA_RESTRITA);
	}

	public void preencherFormulario(String username, String password) {
		browser.findElement(By.id("username")).sendKeys(username);
		browser.findElement(By.id("password")).sendKeys(password);
	}

	public LeiloesPage enviarFormulario() {
		browser.findElement(By.id("login-form")).submit();
		return new LeiloesPage(browser);
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


}
