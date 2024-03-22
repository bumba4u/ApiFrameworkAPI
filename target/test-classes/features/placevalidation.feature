Feature: Validate Place API's
  @addplace
  Scenario Outline: Verify if place is being added successfully using AddPlace API
  
    Given Add place payload with "<name>" "<language>" "<address>"
    When user call "AddPlaceAPI" with "post" http request
    Then API call is success with status code 200
    And "status" in response is "OK"
    And "scope" in response is "APP"
    And verify "place_id" created maps to "<name>" using "GetPlaceAPI" 
    
      Examples: 
      | name    | language    | address    |
      | AAhouse |    Bengali  | GG Kol   |
#      | ABhouse |    English  | Vir     |
    
   @deleteplace 
  Scenario Outline: Verify if DeleteAPI is working fine or not
  
   Given DeletePlace payload	
   When user call "DeletePlaceAPI" with "delete" http request
   Then API call is success with status code 200
   And "status" in response is "OK"
   
   
  
  