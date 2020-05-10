package com.streammedia.entity;
import com.fasterxml.jackson.annotation.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.*;
import javax.ws.rs.core.FeatureContext;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * The type User.
 * https://docs.oracle.com/javaee/5/api/javax/persistence/Column.html
 * Add Hibernate Search:
 * https://thoughts-on-java.org/add-full-text-search-application-hibernate-search/
 *
 * @author Jeanne
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity(name = "User")
@Table(name = "SM_Users")
@JsonIgnoreProperties({"films","trailers","faqs","crews","books","musics","shortStories"})
public class User implements Serializable {


    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private int userId;

    @Field
    @Column(name = "username",nullable = false,unique = true)
    private String username;

    @Field
    @Column(name = "email",nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password",nullable = false)
    private String password;

    @Field
    @Column(name = "first_name")
    private String firstName;

    @Field
    @Column(name = "last_name")
    private String lastName;


    @Column(name = "birthdate")
//    @EqualsAndHashCode.Exclude //todo only make the test pass
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthdate;

    @Column(name = "picture")
    private String picture;

    @Column(name = "gender")
    private String gender;

    @Column(name = "biography")
    private String biography;

    @Column(name = "created_at")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate updateAt;

    //Roles
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Role> roles = new HashSet<Role>();

    //Trailers
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Trailer> trailers = new HashSet<>();

    //Crews
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Crew> crews = new HashSet<>();
    //Films
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Film> films = new HashSet<>();

    //FAQ
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<FAQ> faqs = new HashSet<>();

    //Shortstories
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  Set<ShortStory> shortStories =  new HashSet<>();


    //books
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  Set<Book> books =  new HashSet<>();


    //Musics
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY)
    private  Set<Music> musics =  new HashSet<>();

    /**
     * Instantiates a new User.
     *
     * @param userName  the user name
     * @param email     the email
     * @param password  the password
     * @param createdAt the created at
     * @param updateAt  the update at
     */
    public User(String userName, String email, String password, LocalDate createdAt, LocalDate updateAt) {
        this.username = userName;
        this.email = email;
        this.password = password;
        this.createdAt = createdAt;
        this.updateAt = LocalDate.now();

    }

    /**
     * Add role.
     *
     * @param role the role
     */
    public void addRole(Role role) {
        roles.add(role);

    }

    /**
     * Remove role.
     *
     * @param role the role
     */
    public void removeRole(Role role){
        roles.remove(role);
    }
    //https://www.baeldung.com/hibernate-one-to-many


    /**
     * https://howtodoinjava.com/java/calculate-age-from-date-of-birth/
     * https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html
     * Calculate age based on Today's date and birth date.
     *
     * @return age int
     */
    public int getAge(){
        int years = 0;
        LocalDate now = LocalDate.now();                        //Today's date
        if( this.getBirthdate() != null) {
            Period age = Period.between(this.getBirthdate(), now); //difference between the dates is calculated
            years = age.getYears();
        }
    return years;
    }

    /**
     * Get full name string.
     *
     * @return the string
     */
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

}
