package com.hodolog.request;
import com.hodolog.exception.InvalidRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@ToString
public class PostCreate {

    @NotBlank(message = "타이틀을 입력해주세요.")
    private final String title;

    @NotBlank(message = "콘텐츠를 입력해주세요.")
    private final String content;

    @Builder        // 빌더패턴
    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void validate() {
        if(title.contains("바보")){
            throw new InvalidRequest("title","제목에 바보를 포함할 수 없습니다.");
        }
    }

//    public PostCreate changeTitle(String title){
//        return PostCreate.builder()
//                .title(title)
//                .content(content)
//                .build();
//    }

    // 빌더의 장점
    //  - 가독성이 좋다. ( 값 생성에 대한 유연함 )
    //  - 필요한 값만 받을 수 있다. -> 오버로딩 가능한 조건 확인
    //  - 객체의 불변성

}
