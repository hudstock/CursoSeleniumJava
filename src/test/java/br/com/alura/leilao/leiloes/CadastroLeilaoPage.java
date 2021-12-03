package br.com.alura.leilao.leiloes;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CadastroLeilaoPage {
	
	private WebDriver browser;
	
	public CadastroLeilaoPage(WebDriver browser) {
		this.browser = browser;
	}
	
	public void fechar() {
		this.browser.quit();
	}

	public LeiloesPage cadastrarLeilao(String nomeLeilao, String valor, String dataAbertura) {
		browser.findElement(By.id("nome")).sendKeys(nomeLeilao);
		browser.findElement(By.id("valorInicial")).sendKeys(valor);
		browser.findElement(By.id("dataAbertura")).sendKeys(dataAbertura);		
		browser.findElement(By.id("button-submit")).submit();
		return new LeiloesPage(browser);
	}

}
