package com.wanted.backend.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.wanted.backend.global.config.jwt.MemberTokenInfo;
import com.wanted.backend.global.dto.IdResponse;
import com.wanted.backend.member.entity.Member;
import com.wanted.backend.post.entity.Post;
import com.wanted.backend.post.entity.dto.reponse.PostPageResponse;
import com.wanted.backend.post.entity.dto.reponse.PostResponse;
import com.wanted.backend.post.entity.dto.request.PostCreateRequest;
import com.wanted.backend.post.entity.dto.request.PostUpdateRequest;
import com.wanted.backend.post.repository.PostRepository;
import com.wanted.backend.post.service.PostService;
import com.wanted.backend.support.DatabaseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.util.ReflectionTestUtils;

@DatabaseTest
@DisplayName("Post 서비스의")
public class PostServiceTest {

    @Autowired
    private PostService postService;
    @Autowired
    private PostRepository postRepository;

    private final String email = "yunho@gmail.com";
    private final String password = "12345678";

    @BeforeEach
    void setup() {
        Member member = new Member(email, password);
        Post post1 = new Post("content1", "title1", member);
        Post post2 = new Post("content2", "title2", member);
        Post post3 = new Post("content3", "title3", member);

        ReflectionTestUtils.setField(member, "id", 1L);
        ReflectionTestUtils.setField(post1, "id", 1L);
        ReflectionTestUtils.setField(post2, "id", 2L);
        ReflectionTestUtils.setField(post3, "id", 3L);
    }
    @Test
    @DisplayName("글 작성이 되는가")
    void create() {
        //given
        String title = "제목1";
        String content = "내용1";

        //when
        IdResponse<Long> response = postService.create(new PostCreateRequest(1L, title, content));

        //then
        Post postEntity = postRepository.findById(response.getId()).orElseThrow();

        assertThat(response.getId()).isEqualTo(postEntity.getId());
    }

    @Test
    @DisplayName("글 수정이 되는가")
    void update() {
        //given
        String updateTitle = "제목1 업데이트";
        String updateContent = "내용1 업데이트";

        //when
        IdResponse<Long> response = postService.update(
            new PostUpdateRequest(1L, updateTitle, updateContent), new MemberTokenInfo(1L, email));

        //then
        Post postEntity = postRepository.findById(response.getId()).orElseThrow();

        assertThat(postEntity.getTitle()).isEqualTo(updateTitle);
        assertThat(postEntity.getContent()).isEqualTo(updateContent);
    }

    @Test
    @DisplayName("단일글 조회가 되는가")
    void getOnePost() {
        //give
        String title = "제목1";
        String content = "내용1";
        Post post = postRepository.save(new Post( title, content,
            new Member("yunho@gmail.com","12345678")));

        //when
        PostResponse response = postService.getOnePost(post.getId());

        //then
        assertThat(response.getTitle()).isEqualTo(title);
        assertThat(response.getContent()).isEqualTo(content);
    }

    @Test
    @DisplayName("복수글 조회가 되는가")
    void getPostByPagination() {
        //give
        int offset = 0;
        int size = 3;

        //when
        PostPageResponse response = postService.getPostByPagination(offset, size);

        //then
        assertThat(response.getPostResponses()).hasSize(size);
    }

    @Test
    @DisplayName("글 삭제가 되는가")
    void deletePost() {
        //give
        Long postId = 1L;
        Long memberId = 1L;

        //when
        postService.deletePost(postId, new MemberTokenInfo(memberId, email));

        //then
        Post post = postRepository.findById(postId).orElseThrow();

        assertThat(post.getBaseTime().getDeletedAt()).isNotNull();
    }
}
