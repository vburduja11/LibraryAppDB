package com.library.steps;

import com.library.pages.BookPage;
import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.ConfigurationReader;
import com.library.utility.DB_Util;
import com.library.utility.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;

import java.util.Map;

public class US04_ES {
    BookPage bookPage=new BookPage();

    LoginPage loginPage = new LoginPage();
    String bookName;


    @Given("the {string} on the home page_ES")
    public void the_on_the_home_page_es(String userType) {

        loginPage.login(userType);

    }
    @Given("the user navigates to {string} page_ES")
    public void the_user_navigates_to_page_es(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
        BrowserUtil.waitFor(2);


    }
    @When("the user searches for {string} book_ES")
    public void the_user_searches_for_book_es(String bookName) {
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        this.bookName = bookName;
    }
    @When("the user clicks edit book button_ES")
    public void the_user_clicks_edit_book_button_es() {
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }
    @Then("book information must match the Database_ES")
    public void book_information_must_match_the_database_es() {

        BrowserUtil.waitFor(3);
        //UI Steps

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualAuthorName = bookPage.author.getAttribute("value");
        String actualISBN = bookPage.isbn.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualDesc = bookPage.description.getAttribute("value");


        //get same information from database
        String query = "select name,isbn,year,author,description from books\n" +
                "where name = '"+bookName+"'";

        DB_Util.runQuery(query);
        Map<String, String> bookInfo = DB_Util.getRowMap(1);

        String expectedBookName = bookInfo.get("name");
        String expectedAuthorName =bookInfo.get("author");
        String expectedISBN = bookInfo.get("isbn");
        String expectedYear = bookInfo.get("year");
        String expectedDesc = bookInfo.get("description");

        //compare them
        Assert.assertEquals(actualBookName,expectedBookName);
        Assert.assertEquals(actualAuthorName,expectedAuthorName);
        Assert.assertEquals(actualISBN,expectedISBN);
        Assert.assertEquals(actualYear,expectedYear);
        Assert.assertEquals(actualDesc, expectedDesc);


    }
}
