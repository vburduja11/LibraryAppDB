package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class US02_VB {
    String actualBookBorrowed;
    LoginPage loginPage;
    DashBoardPage dashBoardPage;

    @Given("the {string} on the home page_VB")
    public void the_on_the_home_page_vb(String userType) {
        loginPage = new LoginPage();
        loginPage.login(userType);
        BrowserUtil.waitFor(1);
    }

    @When("the librarian gets borrowed books number_VB")
    public void the_librarian_gets_borrowed_books_number_vb() {
        dashBoardPage = new DashBoardPage();
        actualBookBorrowed = dashBoardPage.getModuleCount("Borrowed Books");
        System.out.println("actualBookBorrowed = " + actualBookBorrowed);


    }

    @Then("borrowed books number information must match with DB_VB")
    public void borrowed_books_number_information_must_match_with_db_vb() {
        String query = "select count(*) from book_borrow where is_returned=0";
        DB_Util.runQuery(query);
        String expectedBookBorrowed = DB_Util.getFirstRowFirstColumn();
        System.out.println("expectedBookBorrowed = " + expectedBookBorrowed);

        Assert.assertEquals(expectedBookBorrowed,actualBookBorrowed);

    }
}
