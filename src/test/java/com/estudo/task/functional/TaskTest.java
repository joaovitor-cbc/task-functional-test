package com.estudo.task.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TaskTest {

	private WebDriver driver;
	
	private DesiredCapabilities capabilities;
	
	private final String HUB = "http://172.19.0.1:4444/wd/hub";
	
	private final String HOST_APP = "http://172.19.0.1:8001/tasks";
	
	@Before
	public void setup() throws MalformedURLException {
		capabilities = DesiredCapabilities.chrome();
		driver = new RemoteWebDriver(new URL(HUB), capabilities);
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws InterruptedException {
		try {
			driver.navigate().to(HOST_APP);
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("addTodo")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("dueDate")).sendKeys("14/06/2100");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("saveButton")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			String message = driver.findElement(By.id("message")).getText();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			Assert.assertEquals("Success!", message);
			Thread.sleep(Duration.ofSeconds(3).toMillis());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada() throws InterruptedException {
		try {
			driver.navigate().to(HOST_APP);
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("addTodo")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("dueDate")).sendKeys("14/06/2010");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("saveButton")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			String message = driver.findElement(By.id("message")).getText();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			Assert.assertEquals("Due date must not be in past", message);
			Thread.sleep(Duration.ofSeconds(3).toMillis());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData() throws InterruptedException {
		try {
			driver.navigate().to(HOST_APP);
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("addTodo")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("task")).sendKeys("Teste via Selenium");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("dueDate")).sendKeys("");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("saveButton")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			String message = driver.findElement(By.id("message")).getText();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			Assert.assertEquals("Fill the due date", message);
			Thread.sleep(Duration.ofSeconds(3).toMillis());
		} finally {
			driver.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao() throws InterruptedException {
		try {
			driver.navigate().to(HOST_APP);
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("addTodo")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("task")).sendKeys("");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("dueDate")).sendKeys("14/06/2100");
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			driver.findElement(By.id("saveButton")).click();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			String message = driver.findElement(By.id("message")).getText();
			Thread.sleep(Duration.ofSeconds(2).toMillis());
			Assert.assertEquals("Fill the task description", message);
			Thread.sleep(Duration.ofSeconds(3).toMillis());
		} finally {
			driver.quit();
		}
	}
}
