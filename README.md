To run the application:
 - create a maven run configuration with the command `clean spring-boot:run`
 - or run it manually from the `Application` class

While it is running, **in a linux-like command line**:
- List all movies (works out-of-the box): `curl http://localhost:8080/movies`
- Get movie with Id 8: `curl http://localhost:8080/movies/8`
- Insert a new movie: `curl -X POST -i -H "Content-Type:application/json" -d '{"name":"E.T","releaseYear":1982,"genre":"Drama"}' http://localhost:8080/movies`
- Try to list the movies again (check E.T. is there)
- Modify E.T: `curl -X PUT -i -H "Content-Type:application/json" -d '{"name":"E.T","releaseYear":1982,"genre":"Drama/Science-Fiction"}' http://localhost:8080/movies/11`
- Delete the movie: `curl -X DELETE -i  http://localhost:8080/movies/11`
- Check it is not there: `curl -i  http://localhost:8080/movies/11` returns status 404

When relaunching, changes are lost

In a Windows terminal, you may need to escape the `"` Better use git bash