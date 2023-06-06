@api,@e2e
Feature: Get Room

  Scenario: Get room and Validate

    Given send get request by Id
    When validate response body