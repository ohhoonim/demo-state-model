package dev.ohhoonim.post;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import dev.ohhoonim.components.model.state.PostAction;
import dev.ohhoonim.post.model.EntityId;
import dev.ohhoonim.post.model.Post;
import dev.ohhoonim.post.model.PostStatus;
import dev.ohhoonim.post.model.PostTransitionEvent;
import dev.ohhoonim.post.model.PostTransitionPolicy;

@ExtendWith(MockitoExtension.class)
public class PostEventTest {

    PostTransitionPolicy statePolicy;

    @BeforeEach
    void setup() {
        this.statePolicy= new PostTransitionPolicy() {};
    }

    @Test
    @DisplayName("임시저장시 원본의 저장상태는 InProgress이어야 한다.")
    void post_Draft_event() {
        
        // Service를 구현하는 기본 흐름 : AR 복원 -> Service에서 Actitivity의 action들을 조합
        //           모델과 요구사항을 조합하는 주체
        // Activity의 action이란? 유스케이스의 기본흐름, 대안흐름 등의 최소 수행 단위
        //          (구현시 메서드라고 보면 된다)
        //          (usecase를 단위흐름을 구현하는 주체. infra를 직접이용)
        // 아래 예시코드는 service 를 구현하는 tactics 라고 보면 된다. 

        var postId = new EntityId("postId-1234");

        // 1. 넘어온 기본 파라미터를 이용하여 Post AR 생성. 필수정보 validation.  
        var post = new Post(postId, "system");

        // 2. 이후 DB에서 복원 (여기서는 임의로 복원코드 작성)
        // (실제로는 Activity에서 구현해야할 코드)
        var beforePosted = Post.reconsitute(null, "title1", "contents 1", post.audit());
        beforePosted.setStatus(new PostStatus.None()); // 임의로 None 상태로 지정

        // 임시 저장 데이터를 별도 버전관리 테이블에 저장할 수 도 있다.
        List<PostAction<Post>> actions = List.of(
            p -> System.out.println("입시저장 로직을 외부에서 주입. activity.versionSave"));

        // 3. 임시 저장 Draft 이벤트 발행 
        // 상태 전이 규칙은 statePolicy를 파라미터 주입
        beforePosted.transition(new PostTransitionEvent.Draft(actions), statePolicy); 

        // test: None 에서 InProgress로 상태가 전이되었는지 검증 
        assertThat(beforePosted.getStatus()).isInstanceOf(PostStatus.InProgress.class);

    }
}
