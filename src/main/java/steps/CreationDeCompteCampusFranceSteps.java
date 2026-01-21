package steps;

import io.cucumber.java.en.*;
import models.Etudiant;
import utils.ReadData;

import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreationDeCompteCampusFranceSteps {

    private WebDriver driver;
    private JavascriptExecutor js;
    private ReadData read = new ReadData();
    private Etudiant etudiant;

    @Given("je suis sur la page de creation de compte")
    public void jeSuisSurLaPageDeCreationDeCompte() throws InterruptedException {
        etudiant = read.readDataFromJson();

        // ----------- Configuration Edge ----------------
        EdgeOptions options = new EdgeOptions();

        // Détecte si on est en CI (GitHub Actions)
        String ci = System.getenv("CI");
        if (ci != null && ci.equalsIgnoreCase("true")) {
            // Mode headless pour CI
            options.addArguments("--headless=new"); // ou "--headless" selon ta version Edge
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");
            System.out.println("CI detected: running Edge in headless mode");
        } else {
            // Mode normal local
            System.out.println("Local execution: running Edge with UI");
        }

        driver = new EdgeDriver(options);
        js = (JavascriptExecutor) driver;
        driver.get("https://www.campusfrance.org/fr/user/register");

        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id=\"tarteaucitronAllDenied2\"]")).click();
    }

    @When("je saisi les informations utilisateur")
    public void jeSaisiLesInformationsUtilisateur() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Email
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[2]/div/div[1]/input")))
            .sendKeys(etudiant.getEmail());

        // Mot de passe
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[2]/div/div[2]/div[1]/input")))
            .sendKeys(etudiant.getMotDePasse());
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[2]/div/div[2]/div[2]/input")))
            .sendKeys(etudiant.getConfirmationMotDePasse());

        // Civilité
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[3]/div[1]/fieldset/div/div/div[1]/label")))
            .click();

        // Nom et prénom
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"edit-field-nom-0-value\"]")))
            .sendKeys(etudiant.getNom());
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"edit-field-prenom-0-value\"]")))
            .sendKeys(etudiant.getPrenom());

        // Pays de résidence
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"edit-field-pays-concernes-selectized\"]"))).click();
        WebElement france = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[3]/div[4]/div/div/div[2]/div/div[164]")));
        js.executeScript("arguments[0].parentElement.scrollTop = arguments[0].offsetTop;", france);
        france.click();

        driver.findElement(By.xpath("//*[@id=\"edit-field-nationalite-0-target-id\"]"))
                .sendKeys(etudiant.getPaysNationalite());

        // Adresse
        driver.findElement(By.xpath("//*[@id=\"edit-field-code-postal-0-value\"]")).sendKeys(etudiant.getCodePostal());
        driver.findElement(By.xpath("//*[@id=\"edit-field-ville-0-value\"]")).sendKeys(etudiant.getVille());
        driver.findElement(By.xpath("//*[@id=\"edit-field-telephone-0-value\"]")).sendKeys(etudiant.getTelephone());

        // Profil
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"user-form\"]/div[4]/h2")));
        driver.findElement(By.xpath("//*[@id=\"edit-field-publics-cibles\"]/div[1]/label")).click();

        // Domaine d'étude
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"edit-field-domaine-etudes-wrapper\"]/div/div/div[1]/div"))).click();
        WebElement medcine = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[4]/div[2]/div[1]/div/div/div[2]/div/div[17]")));
        js.executeScript("arguments[0].parentElement.scrollTop = arguments[0].offsetTop;", medcine);
        medcine.click();

        // Niveau d'étude
        js.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath("//*[@id=\"edit-field-domaine-etudes-wrapper\"]/div/label")));
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"edit-field-niveaux-etude-wrapper\"]/div/div/div[1]/div"))).click();
        WebElement master = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/div[2]/main/div[2]/div/div[2]/form/div[4]/div[2]/div[2]/div/div/div[2]/div/div[8]")));
        js.executeScript("arguments[0].parentElement.scrollTop = arguments[0].offsetTop;", master);
        master.click();

        // Conditions
        WebElement conditions = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"edit-field-accepte-communications-wrapper\"]/div/label")));
        js.executeScript("arguments[0].parentElement.scrollTop = arguments[0].offsetTop;", conditions);
        conditions.click();
    }

    @Then("le bouton creer affiche un message {string}")
    public void leBoutonCreerAfficheUnMessage(String msg) {
        driver.quit();
    }
}
