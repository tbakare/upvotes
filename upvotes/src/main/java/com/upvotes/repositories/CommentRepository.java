package com.upvotes.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.upvotes.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

	List<Comment> findByFeatureId(Integer featureId);

}
