package br.com.alura.leilao.login;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginPage {

	private WebDriver browser;

	public LoginPage() {
		System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
		browser = new ChromeDriver();

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

}
