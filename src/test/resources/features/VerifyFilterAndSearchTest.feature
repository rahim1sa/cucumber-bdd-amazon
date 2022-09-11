Feature: Test scenarios for Amazon
  Background:
    Given User is on homepage
    And User clicks category

  Scenario: Verify brand name
    When User filters products by first brand name
    Then one of listed products contain brand name

  Scenario: Verify price range
    When User filters products by price range
    Then products prices should be in range

  Scenario: Verify sorted price
    When User sorts products
    Then Products appear with sorted price