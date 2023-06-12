package reddit.restapi.models;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Objects;

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
    private Instant createdAt;
    @Basic
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Basic
    @Column(name = "subreddit_id")
    private Long subreddit_id;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Long getSubredditId() {
        return subreddit_id;
    }

    public void setSubredditId(Long subreddit_id) {
        this.subreddit_id = subreddit_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(postId, comment.postId) && Objects.equals(userId, comment.userId) && Objects.equals(text, comment.text) && Objects.equals(image, comment.image) && Objects.equals(commReply, comment.commReply) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(updatedAt, comment.updatedAt) && Objects.equals(subreddit_id, comment.subreddit_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postId, userId, text, image, commReply, createdAt, updatedAt, subreddit_id);
    }


}
