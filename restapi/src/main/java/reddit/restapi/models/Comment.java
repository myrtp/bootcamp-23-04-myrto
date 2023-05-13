package reddit.restapi.models;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "comment", schema = "bootcamp2304myrto")
public class Comment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "post_id")
    private Long postId;
    @Basic
    @Column(name = "user_id")
    private Long userId;
    @Basic
    @Column(name = "text")
    private String text;
    @Basic
    @Column(name = "image")
    private String image;
    @Basic
    @Column(name = "comm_reply")
    private String commReply;
    @Basic
    @Column(name = "created_at")
    private Timestamp createdAt;
    @Basic
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCommReply() {
        return commReply;
    }

    public void setCommReply(String commReply) {
        this.commReply = commReply;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (id != comment.id) return false;
        if (postId != comment.postId) return false;
        if (userId != comment.userId) return false;
        if (text != null ? !text.equals(comment.text) : comment.text != null) return false;
        if (image != null ? !image.equals(comment.image) : comment.image != null) return false;
        if (commReply != null ? !commReply.equals(comment.commReply) : comment.commReply != null) return false;
        if (createdAt != null ? !createdAt.equals(comment.createdAt) : comment.createdAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(comment.updatedAt) : comment.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (postId ^ (postId >>> 32));
        result = 31 * result + (int) (userId ^ (userId >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (commReply != null ? commReply.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
