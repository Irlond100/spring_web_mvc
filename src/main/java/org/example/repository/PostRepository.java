package org.example.repository;

import org.example.model.Post;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepository {
    private final AtomicLong counter = new AtomicLong();
    private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        return Optional.ofNullable(posts.get(id));
    }

    public Post save(Post post) {
        if (post.getId() == 0) {
            long newId = counter.incrementAndGet();
            post.setId(newId);
        }
        posts.put(post.getId(), post);
        return post;
    }

    public Post removeById(long id) {
        return posts.remove(id);
    }
}