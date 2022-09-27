package angular_blog_application_BE.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "varchar(50)")
    @Size(min = 1)
    private String author;
    @Column(columnDefinition = "LONGTEXT")
    @Size(min = 1)
    private String content;
    @ManyToOne
    @JoinColumn(name = "blog_id")
    private Blog blog;

    public Comment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }
}
