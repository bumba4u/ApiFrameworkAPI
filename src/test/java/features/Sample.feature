Feature: Validate Place API's
  @addplace
  Scenario Outline: Verify if place is being added successfully using AddPlace API
  
    Given Add place payload with "<name>" "<language>" "<address>"
    When user call1 "AddPlaceAPI" with "post" http request
    Then API call1 is success with status code 200
    And "status" in response1 is "OK"
     And verify "place_id" created maps1 to "<name>" using "GetPlaceAPI" 
    
      Examples: 
      | name    | language    | address    |
      | AAhouse |    Bengali  | GG Kol   |
#      | ABhouse |    English  | Vir    |
    

	  