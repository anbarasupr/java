package com.demo.blogPost;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BlogPostMain {

	public static void main(String[] args) throws IOException {

		String url = "F:\\git\\java\\java\\stream\\src\\main\\resources\\json\\blog\\blog.json";
		ObjectMapper mapper = new ObjectMapper();
		// List<BlogPost> posts = mapper.readValue(new File(url), List.class);
		List<BlogPost> posts = Arrays.asList(new BlogPost("Java", "Tagore", BlogPostType.GUIDE, 100),
				new BlogPost("Times of India", "Times", BlogPostType.NEWS, 200));
		// System.out.println("blogs: " +
		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(posts));
		v5(posts, mapper);
	}

	// Simple Grouping by a Single Column
	public static void v1(List<BlogPost> posts, ObjectMapper mapper) throws JsonProcessingException {
		Map<BlogPostType, List<BlogPost>> postsPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType));
		System.out.println("postsPerType: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(postsPerType));
	}

	// groupingBy with a Complex Map Key Type
	public static void v2(List<BlogPost> posts, ObjectMapper mapper) throws JsonProcessingException {
		Map<Tuple, List<BlogPost>> postsPerTypeAndAuthor = posts.stream()
				.collect(Collectors.groupingBy(post -> new Tuple(post.getType(), post.getAuthor())));
		System.out.println("postsPerTypeAndAuthor: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(postsPerTypeAndAuthor));
	}

	// groupingBy with a Complex Map Key Type
	public static void v3(List<BlogPost> posts, ObjectMapper mapper) throws JsonProcessingException {
		Map<BlogPost.AuthPostTypesLikes, List<BlogPost>> postsPerTypeAndAuthor = posts.stream()
				.collect(Collectors.groupingBy(
						post -> new BlogPost.AuthPostTypesLikes(post.getAuthor(), post.getType(), post.getLikes())));
		System.out.println("postsPerTypeAndAuthor: "
				+ mapper.writerWithDefaultPrettyPrinter().writeValueAsString(postsPerTypeAndAuthor));
	}

	// Modifying the Returned Map Value Type
	public static void v4(List<BlogPost> posts, ObjectMapper mapper) throws JsonProcessingException {
		Map<BlogPostType, Set<BlogPost>> postsPerType = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getType, Collectors.toSet()));
		System.out.println("postsPerType: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(postsPerType));
	}

	// Grouping by Multiple Fields
	public static void v5(List<BlogPost> posts, ObjectMapper mapper) throws JsonProcessingException {
		Map<String, Map<BlogPostType, List<BlogPost>>> map = posts.stream()
				.collect(Collectors.groupingBy(BlogPost::getAuthor, Collectors.groupingBy(BlogPost::getType)));
		System.out.println("map: " + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(map));
	}
}
