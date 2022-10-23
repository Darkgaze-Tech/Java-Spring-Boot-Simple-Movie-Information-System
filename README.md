# REST API of Movie Information System
> Simple Movie Information System using Java Spring Boot


## Documentation 

| Method | Url | Role Privilege | Action |
| --- | --- | --- | --- |
| `POST` | http://localhost:8080/api/v1/auth/register | ALL | Register admin / user |
| `POST` | http://localhost:8080/api/v1/auth/login | ALL | Login admin / user |
| `GET` | http://localhost:8080/api/v1/genre | ALL | Get all genre |
| `GET` | http://localhost:8080/api/v1/genre/{id} | ALL | Get genre by id |
| `POST` | http://localhost:8080/api/v1/genre | ADMIN | Create genre |
| `POST` | http://localhost:8080/api/v1/genre/{id} | ADMIN | Update genre by id |
| `DELETE` | http://localhost:8080/api/v1/genre/{id} | ADMIN | Delete genre by id |
| `GET` | http://localhost:8080/api/v1/movie | ALL | Get all movie |
| `GET` | http://localhost:8080/api/v1/movie/{id} | ALL | Get movie by id |
| `POST` | http://localhost:8080/api/v1/movie | ADMIN | Create movie |
| `POST` | http://localhost:8080/api/v1/movie/{id} | ADMIN | Update movie by id |
| `DELETE` | http://localhost:8080/api/v1/movie/{id} | ADMIN | Delete movie by id |
| `GET` | http://localhost:8080/api/v1/review | ALL | Get all review |
| `GET` | http://localhost:8080/api/v1/review/{id} | ALL | Get review by id |
| `POST` | http://localhost:8080/api/v1/review | USER | Create review |
| `POST` | http://localhost:8080/api/v1/review/{id} | USER | Update review by id |
| `DELETE` | http://localhost:8080/api/v1/review/{id} | USER | Delete review by id |


## Details

### Register

<p> Create admin </p>
<p float="left">
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/register_admin.png" width=100% height=100%>
  <span> </br> </span>
<p> Create user </p>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/register_user.png" width=100% height=100%>
</p>
<br />


### Login

<p> Login admin </p>
<p float="left">
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/login_admin.png" width=100% height=100%>
  <span> </br> </span>
<p> Login user </p>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/login_user.png" width=100% height=100%>
</p>

### Genre

<p> Get all genre </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/get_all_genre.png" width=100% height=100%></br>
<p> Get genre by id </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/get_genre_by_id.png" width=100% height=100%></br>
<p> Create genre </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/create_genre.png" width=100% height=100%></br>
<p> Update genre </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/update_genre_by_id.png"  width=100% height=100%></br>
<p> Delete genre </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/delete_genre_by_id.png"  width=100% height=100%></br>


### Movie

<p> Get all movie </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/get_all_movie.png" width=100% height=100%></br>
<p> Get movie by id </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/get_movie_by_id.png" width=100% height=100%></br>
<p> Create movie </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/create_movie.png" width=100% height=100%></br>
<p> Update movie </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/update_movie_by_id.png"  width=100% height=100%></br>
<p> Delete movie </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/delete_movie_by_id.png"  width=100% height=100%></br>



### Review

<p> Get all review </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/get_all_review.png" width=100% height=100%></br>
<p> Get review by id </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/get_review_by_id.png" width=100% height=100%></br>
<p> Create review </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/create_review.png" width=100% height=100%></br>
<p> Update review </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/update_review_by_id.png"  width=100% height=100%></br>
<p> Delete review </p></br>
<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/delete_review_by_id.png"  width=100% height=100%></br>


### Jwt Token

<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/jwt_token_content.png" width=100% height=100%></br>



### Log MongoDB

<img src="https://github.com/Darkgaze-Tech/Java-Spring-Boot-Simple-Movie-Information-System/blob/main/documentation_image/log_mongodb.png" width=100% height=100%></br>

