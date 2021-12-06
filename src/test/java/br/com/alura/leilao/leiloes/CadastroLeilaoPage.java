package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import br.com.alura.leilao.PageObject;

public class CadastroLeilaoPage extends PageObject {

	public CadastroLeilaoPage(WebDriver browser) {
		super(browser);		
	}

	private static final String URL_LEILOES = "http://localhost:8080/leiloes";
	

	public LeiloesPage cadastrarLeilao(String nomeLeilao, String valor, String dataAbertura) {
		browser.findElement(By.id("nome")).sendKeys(nomeLeilao);
		browser.findElement(By.id("valorInicial")).sendKeys(valor);
		browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);
		browser.findElement(By.id("button-submit")).submit();
		return new LeiloesPage(browser);
	}

	public boolean isPaginaDeCadastro() {
		return browser.getCurrentUrl().equals(URL_LEILOES);
	}

	public boolean isMensagensValidacaoVisiveis() {
		String pageSource = browser.getPageSource();
		return pageSource.contains("não deve estar em branco") && pageSource.contains("minimo 3 caracteres")
				&& pageSource.contains("deve ser um valor maior de 0.1")
				&& pageSource.contains("deve ser uma data no formato dd/MM/yyyy");
	}

}
