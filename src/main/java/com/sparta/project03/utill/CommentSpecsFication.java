package com.sparta.project03.utill;

import com.sparta.project03.domain.Comment;
import org.springframework.data.jpa.domain.Specification;

public class CommentSpecsFication {

    public static Specification<Comment> withArticle_id(Long article_id) {
        return ((root, query, builder) ->
                builder.equal(root.get("article_id"), article_id)
        );
    }
}
