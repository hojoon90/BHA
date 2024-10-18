package com.bupjangsa.service;

import com.bupjangsa.domain.post.entity.Post;
import com.bupjangsa.exception.ForbiddenException;
import com.bupjangsa.exception.NotFoundException;
import com.bupjangsa.type.BoardType;
import com.bupjangsa.domain.post.dto.PostCriteria;
import com.bupjangsa.domain.post.infra.PostRepository;
import com.bupjangsa.domain.user.entity.User;
import com.bupjangsa.domain.user.infra.UserRepository;
import com.bupjangsa.exception.DataProcessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.bupjangsa.domain.post.dto.PostDto.*;
import static com.bupjangsa.message.MessageConst.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public void registerPost(Register boardDto){
        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage()));

        Optional<Long> lastPostNo = postRepository.findPostNoByBoardTypeOrderByPostNoDesc(boardDto.getBoardType());
        Long newPostNo = lastPostNo.map(i -> i + 1).orElse(1L);

        Post entity = boardDto.toEntity(user, newPostNo);
        postRepository.save(entity);
    }


    @Transactional
    public void updatePost(Update boardDto){

        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage()));

        Post post = postRepository.findByPostNoAndBoardType(boardDto.getPostNo(), boardDto.getBoardType())
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND.getMessage()));

        post.updatePostData(boardDto.getTitle(), boardDto.getContents(), user);
    }

    @Transactional
    public void deletePost(Delete boardDto){

        User user = userRepository.findById(boardDto.getUserId())
                .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND.getMessage()));

        Post post = postRepository.findByPostNoAndBoardType(boardDto.getPostNo(), boardDto.getBoardType())
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND.getMessage()));

        //등록자가 아닐 경우 예외처리
        if(!post.getCreatedBy().getAccountId().equals(user.getAccountId())){
            throw new ForbiddenException(FORBIDDEN_AUTHORIZED.getMessage());
        }

        postRepository.delete(post);
    }

    //단건 조회
    public PostInfo selectPost(BoardType boardType, Long postNo){
        return postRepository.findByPostNoAndBoardType(postNo, boardType)
                .map(PostInfo::from)
                .orElseThrow(() -> new NotFoundException(POST_NOT_FOUND.getMessage()));
    }

    //게시물 목록 조회
    public Page<PostInfo> selectPostList(PostCriteria.SearchList criteria, Pageable pageable){

        Page<Post> boardPage = postRepository.selectPostPage(criteria, pageable);

        final List<PostInfo> boardInfoList = boardPage.getContent().stream()
                .map(PostInfo::from)
                .toList();

        return new PageImpl<>(boardInfoList, pageable, boardPage.getTotalElements());
    }

}
