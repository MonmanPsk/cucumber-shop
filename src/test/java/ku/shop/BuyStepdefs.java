package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private Exception lastError;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        Product prod = catalog.getProduct(name);
        order.addItem(prod, quantity);
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @When("I try to buy {string} with quantity {int}")
    public void i_try_to_buy_with_quantity(String name, int quantity) {
        Product prod = catalog.getProduct(name);
        try {
            order.addItem(prod, quantity);
            lastError = null;
        } catch (Exception e) {
            lastError = e;
        }
    }

    @Then("buying should fail with message {string}")
    public void buying_should_fail_with_message(String message) {
        assertNotNull(lastError, "Expected an error but none was thrown");
        assertEquals(message, lastError.getMessage());
    }

    @Then("stock of {string} should be {int}")
    public void stock_of_should_be(String name, int expected) {
        assertEquals(expected, catalog.getProduct(name).getStock());
    }
}

