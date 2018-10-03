package br.com.neolog.welcomekit.services;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import org.apache.http.HttpStatus;
import org.hamcrest.Matchers;
import org.junit.Test;

import br.com.neolog.welcomekit.models.Product;
import br.com.neolog.welcomekit.models.ProductCategory;
import io.restassured.http.ContentType;

public class ProductIntegrationTest extends AbstractIntegrationTest {

	@Test
	public void shouldReturnStatusCreatedWhenAddNewProduct() {

		String json = "{\"code\": 20,	\"name\": \"TV\", \"price\": 2000.0,	\"description\":\"SMART TV 4K\", \"weight\":30.0 , \"category\":{ \"id\": 1, \"code\":10, \"name\":\"Eletronicos\"}}";

		Integer productId = given().contentType(ContentType.JSON).body(json).when().post("/product/add").then().log().everything()
				.assertThat().statusCode(HttpStatus.SC_CREATED).and().extract().response().as(Integer.class);
		
		assertThat(productId).isEqualTo(3);
	
	}

	@Test
	public void shouldReturnStatusOkWhenGetSomeProduct() {

		given().contentType(ContentType.JSON).when().get("product/findId/2").then().log().everything().assertThat()
				.statusCode(HttpStatus.SC_OK).and().body("id", Matchers.equalTo(2));
	
	}
	
	@Test
	public void shouldReturnStatusOkWhenUpdateSomeProduct() {

		String json = "{\"id\" : 1, \"code\": 10,	\"name\": \"TV de tubo\", \"price\": 2000.0,	\"description\":\"SMART TV 4K\", \"weight\":30.0 , \"category\":{ \"id\": 1, \"code\":10, \"name\":\"Eletronicos\"}}";
		Product product = new Product(1,10,"TV de tubo",2000.0,"SMART TV 4K",30.0,new ProductCategory(1,10,"Eletronicos"));
		
		given().contentType(ContentType.JSON).body(json).when().put("product/update/").then().log().everything().assertThat()
				.statusCode(HttpStatus.SC_OK).and().equals(product);
	}
	
	
}
