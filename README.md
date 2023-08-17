# Talos - User Post Administration

This is a Spring Boot application for user posts management. Users can create, update, and delete posts. Only registered users provided by https://jsonplaceholder.typicode.com/users can create the posts.

## Technologies Used

- [Spring Boot](https://spring.io/)
- [Hibernate](https://hibernate.org/)
- [Project Lombok](https://projectlombok.org/)
- [Mapstruct](https://mapstruct.org/) 
- [Flyway](https://flywaydb.org/)
- [Docker](https://docs.docker.com/)

## Requirements

To run this application, you need to have the following software installed on your system:

- Java Development Kit (JDK) 8 or later
- PostgreSQL 12.0v on port 5432 
- Docker with Docker compose (optional, for containerization)


<p align="right">(<a href="#readme-top">back to top</a>)</p>

## Getting Started

#### Preconditions

 * Database configuration : Name: 'talos' | User: 'postgres' | Pass: 'pelikan'


Follow the steps below to set up and run the application:

1. Clone the repository to your local machine:
  ```sh
  git clone git@gitlab.com:skrha/talos.git
  ```
2.Build the application using Maven:
  ```sh
  mvn clean install
  ```
3.Run the application:
  ```sh
  mvn spring-boot:run -Dspring-boot.run.profiles=dev
  ```
The application will start running on the default port 8080.

Access the Swagger documentation for the REST API:

Open your web browser and go to http://localhost:8080/swagger-ui/index.html.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Dockerization (Optional)

If you prefer running the application in a Docker container, follow these below.
Docker will start database container with Postgres DB and Talos App (Spring boot). 

Dockerization steps :
1. Navigate to the project directory:
  ```sh
   cd src/main/docker/
  ```

2. Run Docker compose :
  ```sh
   docker-compose up
  ```

The application will be accessible on http://localhost:8080.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


##API Usage
Once the application is running, you can use the following endpoints to manage user posts:

 * GET /api/posts/{id}: Retrieve details of a specific post.
 * POST /api/posts: Create a new post. Create a post is allowed only for users from https://jsonplaceholder.typicode.com/users
 * PUT /api/posts/{id}: Update an existing post.
 * DELETE /api/posts/{id}: Delete a post.
 * GET /api/users/{userId}/posts/{id}: Retrieve details of a specific post od specific user. If the user's post does not exists it get post from https://jsonplaceholder.typicode.com/posts  


<p align="right">(<a href="#readme-top">back to top</a>)</p>

 

<!-- LICENSE -->
## License

Distributed under the MIT License. 

<p align="right">(<a href="#readme-top">back to top</a>)</p>



<!-- CONTACT -->
## Contact

Your Name - [Michal Škrha](https://sk.linkedin.com/in/michal-%C5%A1krha-23929873) - michal.skrha@kodeosolutions.sk

Project Link: [https://gitlab.com/users/skrha/projects](https://gitlab.com/users/skrha/projects)

<p align="right">(<a href="#readme-top">back to top</a>)</p>

