package reddit.restapi.models;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "user_subred", schema = "bootcamp2304myrto")
public class UserSubred {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "subreddit_id")
    private long subredditId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
    @Basic
    @Column(name = "role")
    private String role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubredditId() {
        return subredditId;
    }

    public void setSubredditId(long subredditId) {
        this.subredditId = subredditId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserSubred that = (UserSubred) o;
        return id == that.id && subredditId == that.subredditId && Objects.equals(user, that.user) && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subredditId, user, role);
    }
}
