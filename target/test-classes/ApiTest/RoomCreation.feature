@create_room @api
Feature:Create Room Bey Id

  Scenario: TC01_Create_Room
    Given  send post request for creating room
    Then get the response and validate