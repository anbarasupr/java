package blogPost;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
public class BlogPost implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	String title;
	@Getter
	@Setter
	String author;
	@Getter
	@Setter
	BlogPostType type;
	@Getter
	@Setter
	int likes;

	record AuthPostTypesLikes(String author, BlogPostType type, int likes) {
	};
}

enum BlogPostType {
	NEWS, REVIEW, GUIDE
}

@AllArgsConstructor
@ToString
class Tuple implements Serializable {
	private static final long serialVersionUID = 1L;
	@Getter
	@Setter
	BlogPostType type;
	@Getter
	@Setter
	String author;
}