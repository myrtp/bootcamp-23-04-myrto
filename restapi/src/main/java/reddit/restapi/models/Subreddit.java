package reddit.restapi.models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "subreddit", schema = "bootcamp2304myrto")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class Subreddit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "created_at")
    private Instant createdAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            schema = "bootcamp2304myrto",
            name = "user_subred",
            joinColumns = @JoinColumn(name = "subreddit_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonBackReference
    private List<UserSubred> UserSubredAuth;

    public Long getId() {
        return id;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<UserSubred> getUserSubredAuth() {
        return UserSubredAuth;
    }

    public void setUserSubredAuth(List<UserSubred> UserSubredAuth) {
        this.UserSubredAuth = UserSubredAuth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subreddit subreddit = (Subreddit) o;

        if (id != subreddit.id) return false;
        if (description != null ? !description.equals(subreddit.description) : subreddit.description != null)
            return false;
        if (title != null ? !title.equals(subreddit.title) : subreddit.title != null) return false;
        if (createdAt != null ? !createdAt.equals(subreddit.createdAt) : subreddit.createdAt != null) return false;

        return true;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, description, title, createdAt);
    }
}

