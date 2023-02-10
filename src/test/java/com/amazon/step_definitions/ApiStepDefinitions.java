package com.amazon.step_definitions;
import static io.restassured.RestAssured.*;

import com.amazon.pojo.Post;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApiStepDefinitions {


    int count = 0;
    List<Post> postList = new ArrayList<>();
    @When("I get a list of blog posts using the API endpoint")
    public void i_get_a_list_of_blog_posts_using_the_api_endpoint() {
        baseURI = "https://jsonplaceholder.typicode.com";
        JsonPath jsonPath = given().accept(ContentType.JSON).get("/posts").then()
                .statusCode(200)
                .extract().jsonPath();
        postList = jsonPath.getList("", Post.class);

    }
    @Then("user {int} should have {int} posts.")
    public void user_should_have_posts(int user, int numposts) {

        for (Post post : postList) {
            if (post.getUserId()==user) {
              count++;

            }
        }
        Assert.assertEquals(numposts,count);
    }

    @Then("each blog post should have a unique ID")
    public void eachBlogPostShouldHaveAUniqueID() {

        List<Integer> listOfId = new ArrayList<>();
        Set<Integer> setOfId = new HashSet<>();

        for (Post post : postList) {
            listOfId.add(post.getId());
            setOfId.add(post.getId());


        }
        Assert.assertEquals(listOfId.size(),setOfId.size());
        System.out.println(listOfId.size());
        System.out.println(setOfId.size());
    }
}
