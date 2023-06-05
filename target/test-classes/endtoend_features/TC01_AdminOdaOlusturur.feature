@e2e
Feature: Medunna Room Creation

  Background: Sign in Medunna

    Given go to "medunnaurl"

    When click on user icon
    And click sign in option
    And send username into username input
    And send password into password input
    And click on sign in submit button


  Scenario Outline: Create Room
    When Click on ItemsAndTitels
    And click on Room option
    And click on Create a new room button
    And enter "<room_number>" room number input
    And select Suite option from Room Type dropdown
    And click on Status checkbox
    And enter "<price>" in Price input
    And enter "<description>" in Description input
    And click on Save button
    And close the application
    Examples:
      | room_number | price | description                 |
      | 72244940    | 123   | Created For End To End Test |