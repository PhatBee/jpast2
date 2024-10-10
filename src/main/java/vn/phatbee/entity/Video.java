package vn.phatbee.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v from Video v")
public class Video implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "VideoId")
    private String videoId;

    @Column(name = "Active")
    private int active;

    @Column(name = "Description", columnDefinition = "nvarchar(500) null")
    private String description;

    @Column(name = "Poster", columnDefinition = "nvarchar(500) null")
    private String poster;

    @Column(name = "Title", columnDefinition = "nvarchar(255) null")
    private String title;
    @NotEmpty(message = "Không được bỏ trống")

    @Column(name = "Views")
    private int views;

    //bi-directional many-to-one association to Category
    @ManyToOne
    @JoinColumn(name="CategoryId")
    private Category category;


}
