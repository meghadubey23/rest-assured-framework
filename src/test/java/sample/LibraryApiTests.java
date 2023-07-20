package sample;

import Utilities.AssertUtility;
import Utilities.ParseJsonUtility;
import Utilities.StringUtility;
import apis.libraryapis.BookData;
import apis.libraryapis.LibraryDynamicPayload;
import datadriven.BaseTest;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryApiTests extends BaseTest {

    @Test(dataProvider = "book data", groups = "Library")
    public void addBook(BookData[] bookData) {
        if (bookData != null) {
            for (BookData data : bookData) {
                //Add book
                String responseAddBook = given().spec(getRequestSpecification()).header("Content-Type", "application/json").body(LibraryDynamicPayload.addBookPayload(data.getIsbn(), data.getAisle()))
                        .when().post("Library/Addbook.php")
                        .then().spec(getResponseSpecification()).statusCode(200).extract().asString();
                JsonPath js = new JsonPath(responseAddBook);
                String id = js.getString("ID");
                System.out.println(id);

                // Get details using Author Name
                String responseUsingAuthorName = given().spec(getRequestSpecification()).queryParam("AuthorName", "Megha Dubey").header("Content-Type", "application/json")
                        .when().get("Library/GetBook.php")
                        .then().spec(getResponseSpecification()).statusCode(200).extract().asString();

                var list = ParseJsonUtility.responseList(responseUsingAuthorName);
                for (int i = 0; i < list.size(); i++) {
                    String bookName = list.get(i).get("book_name").toString();
                    AssertUtility.assertEquals(bookName, "Learn Appium Automation with Java", "bookName");
                    String isbn = list.get(i).get("isbn").toString();
                    AssertUtility.assertEquals(isbn, data.getIsbn(), "isbn");
                    String aisle = list.get(i).get("aisle").toString();
                    AssertUtility.assertEquals(aisle, data.getAisle().startsWith("0") ? data.getAisle().substring(1) : data.getAisle(), "aisle");
/*                    StringBuilder sb = new StringBuilder();
                    System.out.println(sb.append(list.get(i).get("isbn")).append(list.get(i).get("aisle")));*/
                }

                // Get details using ID
                String responseUsingId = given().spec(getRequestSpecification()).queryParam("ID", id).header("Content-Type", "application/json")
                        .when().get("Library/GetBook.php")
                        .then().spec(getResponseSpecification()).statusCode(200).extract().asString();

                list = ParseJsonUtility.responseList(responseUsingId);
                for (int i = 0; i < list.size(); i++) {
                    String bookName = list.get(i).get("book_name").toString();
                    AssertUtility.assertEquals(bookName, "Learn Appium Automation with Java", "bookName");
                    String isbn = list.get(i).get("isbn").toString();
                    AssertUtility.assertEquals(isbn, data.getIsbn(), "isbn");
                    String aisle = list.get(i).get("aisle").toString();
                    AssertUtility.assertEquals(aisle, data.getAisle().startsWith("0") ? data.getAisle().substring(1) : data.getAisle(), "aisle");
                }

                // Delete using ID
                given().spec(getRequestSpecification()).header("Content-Type", "application/json").body(LibraryDynamicPayload.deleteBookPayload(id))
                        .when().post("Library/DeleteBook.php")
                        .then().spec(getResponseSpecification()).statusCode(200).body("msg", equalTo("book is successfully deleted"));

                // Get details using Author Name
                given().spec(getRequestSpecification()).queryParam("AuthorName", "Megha Dubey").header("Content-Type", "application/json")
                        .when().get("Library/GetBook.php")
                        .then().spec(getResponseSpecification()).statusCode(404).body("msg", equalTo("The book by requested bookid / author name does not exists!"));

            }
        }
    }

    @DataProvider(name = "book data")
    public Object[][] addLibData() {
        BookData addBookData = new BookData();
        addBookData.setIsbn(StringUtility.randomString(5));
        addBookData.setAisle(StringUtility.randomNumeric(5));

        BookData addBookData2 = new BookData();
        addBookData2.setIsbn(StringUtility.randomString(5));
        addBookData2.setAisle(StringUtility.randomNumeric(5));

        BookData[] arr = new BookData[2];
        arr[0] = addBookData;
        arr[1] = addBookData2;

        return new Object[][]{arr};
    }

    /*
    * {
        "book_name": "Learn Appium Automation with Java",
        "isbn": "4FUBg",
        "aisle": "5117"
    },
    {
        "book_name": "Learn Appium Automation with Java",
        "isbn": "04W1x",
        "aisle": "65179"
    },
    {
        "book_name": "Learn Appium Automation with Java",
        "isbn": "x1mRk",
        "aisle": "33934"
    },
    {
        "book_name": "Learn Appium Automation with Java",
        "isbn": "GX6PS",
        "aisle": "87712"
    }*/
}
