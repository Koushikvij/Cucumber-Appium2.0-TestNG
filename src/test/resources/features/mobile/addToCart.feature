Feature: Add an item to Cart
 
Background: 
   Given The device is connected and the app is launched
  
   Scenario: add items to cart
      
    When User enters username as "John", country as "Argentina" and gender as "Female" to login
    And Add products with name "Jordan 6 Rings" to cart
    Then User should be able to validate the count in cart page
