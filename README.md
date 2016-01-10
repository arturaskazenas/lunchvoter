# The implementation of the test task with a JSON API using Hibernate/Spring/SpringMVC.


## How to run with Maven

    mvn clean spring-boot:run
	
   if yuo want to override the default time limit
   mvn clean spring-boot:run -Dlt.ak.lunchvoter.timeLimitToVote=17:00:00	

   
## How to test with curl

creating restaurants
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"restaurants\",\"attributes\": {\"name\":\"Restaurant A\"}}}" http://localhost:8080/restaurants
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"restaurants\",\"attributes\": {\"name\":\"Restaurant B\"}}}" http://localhost:8080/restaurants
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"restaurants\",\"attributes\": {\"name\":\"Restaurant C\"}}}" http://localhost:8080/restaurants

updating a restaurant
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -X PATCH -d "{\"data\":{\"type\":\"restaurants\",\"attributes\": {\"name\":\"Restaurant BB\"}}}" http://localhost:8080/restaurants/2

deleting a restaurant
curl -v -u admin:admin -X DELETE http://localhost:8080/restaurants/3

getting the restaurant list
curl -v -u admin:admin http://localhost:8080/restaurants


creating menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menus\",\"attributes\": {\"date\":1452376800000}}}" http://localhost:8080/menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menus\",\"attributes\": {\"date\":1452376800000}}}" http://localhost:8080/menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menus\",\"attributes\": {\"date\":1452463200000}}}" http://localhost:8080/menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menus\",\"attributes\": {\"date\":1452463200000}}}" http://localhost:8080/menus

creating restaurant menus relationships
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menus\",\"id\":1}]}" http://localhost:8080/restaurants/1/relationships/menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menus\",\"id\":2}]}" http://localhost:8080/restaurants/1/relationships/menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menus\",\"id\":3}]}" http://localhost:8080/restaurants/1/relationships/menus
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menus\",\"id\":4}]}" http://localhost:8080/restaurants/1/relationships/menus

deleting menu
curl -v -u admin:admin -X DELETE http://localhost:8080/menus/3

getting the restaurant list with menus
curl -v -u admin:admin -g http://localhost:8080/restaurants/?include[restaurants]=menus
getting a restaurant with menus
curl -v -u admin:admin -g http://localhost:8080/restaurants/1/?include[restaurants]=menus


creating menu items
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menuitems\",\"attributes\": {\"dishName\":\"Dish A\", \"price\":111.22}}}" http://localhost:8080/menuitems
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menuitems\",\"attributes\": {\"dishName\":\"Dish B\", \"price\":222.33}}}" http://localhost:8080/menuitems
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menuitems\",\"attributes\": {\"dishName\":\"Dish C\", \"price\":333.44}}}" http://localhost:8080/menuitems
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"menuitems\",\"attributes\": {\"dishName\":\"Dish D\", \"price\":444.55}}}" http://localhost:8080/menuitems

creating menu menuitems relationships
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menuitems\",\"id\":1}]}" http://localhost:8080/menus/1/relationships/menuitems
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menuitems\",\"id\":2}]}" http://localhost:8080/menus/1/relationships/menuitems
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menuitems\",\"id\":3}]}" http://localhost:8080/menus/2/relationships/menuitems
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":[{\"type\":\"menuitems\",\"id\":4}]}" http://localhost:8080/menus/2/relationships/menuitems

updating menuitem
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -X PATCH -d "{\"data\":{\"type\":\"menuitems\",\"attributes\": {\"dishName\":\"Dish BB\"}}}" http://localhost:8080/menuitems/2

deleting menuitem
curl -v -u admin:admin -X DELETE http://localhost:8080/menuitems/4

getting menus with items
curl -v -u admin:admin -g http://localhost:8080/menus


creating users
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"users\",\"attributes\": {\"name\":\"Jonas\", \"password\":\"jonas\"}}}" http://localhost:8080/users
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"users\",\"attributes\": {\"name\":\"Petras\", \"password\":\"petras\"}}}" http://localhost:8080/users
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"users\",\"attributes\": {\"name\":\"Darius\", \"password\":\"darius\"}}}" http://localhost:8080/users
curl -v -u admin:admin -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"users\",\"attributes\": {\"name\":\"Marius\", \"password\":\"marius\"}}}" http://localhost:8080/users
getting the user list
curl -v -u admin:admin http://localhost:8080/users/
checking user rights
curl -v -u Jonas:jonas -X DELETE http://localhost:8080/restaurants/1



voting
curl -v -u Jonas:jonas -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"votes\",\"attributes\": {},\"relationships\": {\"menu\": {\"data\": {\"id\":1}}}}}" http://localhost:8080/votes
curl -v -u Petras:petras -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"votes\",\"attributes\": {},\"relationships\": {\"menu\": {\"data\": {\"id\":1}}}}}" http://localhost:8080/votes
curl -v -u Darius:darius -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"votes\",\"attributes\": {},\"relationships\": {\"menu\": {\"data\": {\"id\":1}}}}}" http://localhost:8080/votes
curl -v -u Marius:marius -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"votes\",\"attributes\": {},\"relationships\": {\"menu\": {\"data\": {\"id\":1}}}}}" http://localhost:8080/votes
changing mind
curl -v -u Jonas:jonas -H "Content-Type: application/vnd.api+json;charset=UTF-8" -d "{\"data\":{\"type\":\"votes\",\"attributes\": {},\"relationships\": {\"menu\": {\"data\": {\"id\":2}}}}}" http://localhost:8080/votes
only admin can delete a vote
curl -v -u Darius:darius -X DELETE http://localhost:8080/votes/3
curl -v -u admin:admin -X DELETE http://localhost:8080/votes/3

getting the votes list
curl -v -u admin:admin http://localhost:8080/votes/

getting the menu list filtered by date with votes and items included by default
curl -v -u admin:admin -g http://localhost:8080/menus/?filter[menus][date]=1452290400000


