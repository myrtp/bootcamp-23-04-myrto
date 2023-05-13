package reddit.restapi.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "subreddit", schema = "bootcamp2304myrto")
public class Subreddit {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long id;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
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
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
