package web.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank @Column(nullable = false)
    private String firstName;

    @NotBlank @Column(nullable = false)
    private String lastName;

    private String email;

    public User() {}
    public User(String firstName, String lastName, String email) {
        this.firstName = firstName; this.lastName = lastName; this.email = email;
    }

    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getEmail() { return email; }

    public void setId(Long id) { this.id = id; }
    public void setFirstName(String v) { this.firstName = v; }
    public void setLastName(String v) { this.lastName = v; }
    public void setEmail(String v) { this.email = v; }
}
