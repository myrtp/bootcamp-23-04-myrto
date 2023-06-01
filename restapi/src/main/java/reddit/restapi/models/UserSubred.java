package reddit.restapi.models;

import jakarta.persistence.*;

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
    @Basic
    @Column(name = "user_id")
    private long userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

        if (id != that.id) return false;
        if (subredditId != that.subredditId) return false;
        if (userId != that.userId) return false;
        if (role != null ? !role.equals(that.role) : that.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (subredditId ^ (subredditId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }
}
