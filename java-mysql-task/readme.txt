run application: mvn jety:run

To make call for inserting new user:

POST localhost:8080/user.json

{
	"username":"zxc",
	"email":"zxc",
	"password":"zxc",
	"categories": [{"name":"zxc"}]
}

To make call for updating user:

PUT localhost:8080/user/{id}.json

{
	"username":"jkljkl",
	"email":"jkljkl",
	"password":"jkljkl",
	"categories": [{"name":"jkljkl"}]
}

To make call for selecting all users:

GET localhost:8080/user.json

To make call for selecting user by id:

GET localhost:8080/user/{id}.json