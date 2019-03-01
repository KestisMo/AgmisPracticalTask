package lt.kestutismozeris.weatherforecast.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "First Name is required")
    private String name;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Home Location is required")
    private String homeLocation;

    @NotBlank(message = "Favorite city is required")
    private String favoriteCity;


    public User() {
    }

    public User(String name, String email, String lastName, String favoriteCity, String homeLocation, String username) {
        this.name = name;
        this.email = email;
        this.lastName = lastName;
        this.favoriteCity = favoriteCity;
        this.homeLocation = homeLocation;
        this.username = username;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(String homeLocation) {
        this.homeLocation = homeLocation;
    }

    public String getFavoriteCity() {
        return favoriteCity;
    }

    public void setFavoriteCity(String favoriteCity) {
        this.favoriteCity = favoriteCity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "User{" + "id=" + id + ", name=" + name + ", email=" + email + ", username=" + username + ", last name=" + lastName + ", favorite city=" + favoriteCity +
                ", home location=" + homeLocation + '}';
    }
}


