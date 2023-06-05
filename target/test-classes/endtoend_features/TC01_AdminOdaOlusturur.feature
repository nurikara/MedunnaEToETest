@e2e
Feature: Medunna Oda Olusturma

  Background: Sign in Medunna

    Given go to "http://medunna.com"

    When click on user icon
    And click sign in option
    And send username into username input
    And send password into password input
    And click on sign in submit button

  Scenario: Create Room
    When Click on ItemsAndTitels