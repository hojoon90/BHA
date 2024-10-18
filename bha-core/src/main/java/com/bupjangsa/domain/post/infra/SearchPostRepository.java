package com.bupjangsa.domain.post.infra;

import com.bupjangsa.domain.post.dto.PostCriteria;
import com.bupjangsa.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SearchPostRepository {
    Page<Post> selectPostPage(PostCriteria.SearchList criteria, Pageable pageable);
}
