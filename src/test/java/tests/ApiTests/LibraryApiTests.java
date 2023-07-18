package tests.ApiTests;

import Utilities.AssertUtility;
import Utilities.ParseJsonUtility;
import Utilities.StringUtility;
import apis.libraryapis.BookData;
import apis.libraryapis.DynamicPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LibraryApiTests {

    @Test(dataProvider = "bok data")
    public void addBook(BookData[] bookData) {
        if (bookData != null) {
            for (BookData data : bookData) {
                RestAssured.baseURI = "http://216.10.245.166";

                //Add book
                String responseAddBook = given().log().all().header("Content-Type", "application/json").body(DynamicPayload.addBookPayload(data.getIsbn(), data.getAisle()))
                        .when().post("Library/Addbook.php")
                        .then().log().all().statusCode(200).extract().asString();
                JsonPath js = ParseJsonUtility.convertToJson(responseAddBook);
                String id = js.getString("ID");
                System.out.println(id);

                // Get details using Author Name
                String responseUsingAuthorName = given().log().all().queryParam("AuthorName", "Megha Dubey").header("Content-Type", "application/json")
                        .when().get("Library/GetBook.php")
                        .then().log().all().statusCode(200).extract().asString();

                var list = ParseJsonUtility.responseList(responseUsingAuthorName);
                for (int i = 0; i < list.size(); i++) {
                    String bookName = list.get(i).get("book_name").toString();
                    AssertUtility.assertEquals(bookName, "Learn Appium Automation with Java", "bookName");
                    String isbn = list.get(i).get("isbn").toString();
                    AssertUtility.assertEquals(isbn, data.getIsbn(), "isbn");
                    String aisle = list.get(i).get("aisle").toString();
                    AssertUtility.assertEquals(aisle, data.getAisle(), "aisle");
/*                    StringBuilder sb = new StringBuilder();
                    System.out.println(sb.append(list.get(i).get("isbn")).append(list.get(i).get("aisle")));*/
                }

                // Get details using ID
                String responseUsingId = given().log().all().queryParam("ID", id).header("Content-Type", "application/json")
                        .when().get("Library/GetBook.php")
                        .then().log().all().statusCode(200).extract().asString();

                list = ParseJsonUtility.responseList(responseUsingId);
                for (int i = 0; i < list.size(); i++) {
                    String bookName = list.get(i).get("book_name").toString();
                    AssertUtility.assertEquals(bookName, "Learn Appium Automation with Java", "bookName");
                    String isbn = list.get(i).get("isbn").toString();
                    AssertUtility.assertEquals(isbn, data.getIsbn(), "isbn");
                    String aisle = list.get(i).get("aisle").toString();
                    AssertUtility.assertEquals(aisle, data.getAisle(), "aisle");
                }

                // Delete using ID
                given().log().all().header("Content-Type", "application/json").body(DynamicPayload.deleteBookPayload(id))
                        .when().post("Library/DeleteBook.php")
                        .then().log().all().statusCode(200).body("msg", equalTo("book is successfully deleted"));

                // Get details using Author Name
                given().log().all().queryParam("AuthorName", "Megha Dubey").header("Content-Type", "application/json")
                        .when().get("Library/GetBook.php")
                        .then().log().all().statusCode(404).body("msg", equalTo("The book by requested bookid / author name does not exists!"));

            }
        }
    }

    @DataProvider(name = "bok data")
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
}
