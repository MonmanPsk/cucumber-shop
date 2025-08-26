Feature: Buy products
    As a customer
    I want to buy products

Background:
    Given the store is ready to service customers
    And a product "Bread" with price 20.50 and stock of 5 exists
    And a product "Jam" with price 80.00 and stock of 10 exists
    And a product "Ham" with price 60.00 and stock of 6 exists

Scenario: Buy one product
    When I buy "Bread" with quantity 2
    Then total should be 41.00

Scenario: Buy multiple products
    When I buy "Bread" with quantity 2
    And I buy "Jam" with quantity 1
    And I buy "Ham" with quantity 3
    Then total should be 301.00

Scenario: Reject purchase when stock is insufficient
    When I try to buy "Bread" with quantity 6
    Then buying should fail with message "Insufficient stock for 'Bread': have 5, requested 6"
    And stock of "Bread" should be 5
    And total should be 0.00