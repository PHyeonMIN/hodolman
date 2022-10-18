package com.hodolog.controller;

import com.hodolog.request.PostCreate;
import com.hodolog.request.PostSearch;
import com.hodolog.response.PostResponse;
import com.hodolog.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {

    // SSR -> jsp, thymeleaf, mustache, freemarker
        // -> html rendering
    // SPA -> vue(vue + SSR = nuxt), react(react + SSR = next)
        // -> javascript + <-> API (JSON)

    // Http Method
    // GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
    // 글 등록
    // POST Method

    @PostMapping("/posts1")
//    public String post(@RequestParam String title, @RequestParam String content) {
//    public String post(@RequestParam Map<String, String> params) {
    // ModelAttribute : 쿼리스트링, 폼    // @RequestBody : JSON 및 XML
    public Map<String,String> post1(@RequestBody @Valid PostCreate params, BindingResult result) throws Exception {
        // 데이터를 검증하는 이유

        // 1. client 개발자가 깜박할 수있다. 실수로 값을 안보낼 수 있다.
        // 2. client bug로 값이 누락될 수 있다
        // 3. 외부에 나쁜 사람이 값을 임의로 조작해서 보낼 수 있다
        // 4. DB에 값을 저장할 때 의도치 않은 오류가 발생할 수 있다
        // 5. 서버 개발자의 편안함을 위해서

        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            FieldError firstFieldError = fieldErrors.get(0);
            String fieldName = firstFieldError.getField(); //title
            String errorMessage = firstFieldError.getDefaultMessage();//..에러메시지

            Map<String, String> error = new HashMap<>();
            error.put(fieldName, errorMessage);
            return error;
        }
        log.info("params={}",params.toString());
        // {"title" : "타이틀 값이 없습니다"}
        String title = params.getTitle();
        if(title == null || title.equals("")){
            // 1. 빡세다. (노가다)
            // 2. 개발팁 -> 무언가 3번 이상 반복작업을 할 때 내가 뭔가 잘못하고 있는 건 아닐지 의심한다.
            // 3. 누락 가능성
            // 4. 생각보다 검증해야할 게 많다 ( 꼼꼼하지 않을 수 있다 )
                // {"title": ""}
                // {"title": "       "}
                // {"title": ".......수십억 글자 "}
            // 5. 뭔가 개발자 스럽지 않다. -> 간지 X
            throw new Exception("타이틀값이 없어요!");
        }
        String content = params.getContent();
        if(content ==null || content.equals("")){
            // error
        }

        return Map.of();
    }

    @PostMapping("/posts2")
    public Map<String, String> post2(@RequestBody @Valid PostCreate params){
        // 1. 매번 메서드마다 값을 검증 해야한다.
        //      > 개발자가 까먹을 수 있다.
        //      > 검증 부분에서 버그가 발생할 여지가 높다
        //      > 지겹다. (간지가 안난다.)
        // 2. 응답값에 HashMap -> 응답 클래스를 만들어주는게 좋다
        // 3. 여러개의 에러 처리 힘듬
        // 4. 세 번이상의 반복적인 작업은 피해야한다.
        //      > 코드 && 개발에 관한 모든것 > 자동화 고려

//        if(result.hasErrors()){
//            List<FieldError> fieldErrors = result.getFieldErrors();
//            FieldError firstFieldError = fieldErrors.get(0);
//            String fieldName = firstFieldError.getField(); //title
//            String errorMessage = firstFieldError.getDefaultMessage();//..에러메시지
//
//            Map<String, String> error = new HashMap<>();
//            error.put(fieldName, errorMessage);
//            return error;
//        }
        return Map.of();
    }



    private final PostService postService;

    @PostMapping("/posts")
    public void post(@RequestBody @Valid PostCreate request) {
        // Case1. 저장한 데이터 Entity -> response로 응답하기
//        return postService.write(request);

        // Case2. 저장한 데이터의 primary_id -> response로 응답하기
        //          Client에서는 수신한 id를 글 조회 API를 통해서 데이터를 수신받음
//        Long postId = postService.write(request);
//        return Map.of("postId", postId);

        // Case3. 응답 필요 없음 -> 클라이언트에서 모든 POST(글) 데이터 context를 잘 관리함
        postService.write(request);

        // Bad Case : 서버에서 -> 반드시 이렇게 할껍니다! fix
        //                  -> 서버에서 차라리 유연하게 대응하는게 좋다.
        //                  -> 코드를 잘짜라.
        //                  -> 한 번에 일괄적으로 잘 처리되는 케이스가 없다.
        //                  -> 잘 관리하는 형태가 중요하다.
    }

    /**
     * /posts -> 글 전체 조회(검색 + 페이징)
     * /posts/{postId} -> 글 한개만 조회
     */

    @GetMapping("/posts/{postId}")
    public PostResponse get(@PathVariable(name ="postId") Long id){
        PostResponse response = postService.get(id);
        return response;
    }

    /**
     * 조회 API
     * 여러개의 글을 조회 API
     */
    @GetMapping("/posts")
//    public List<PostResponse> getList(@PageableDefault(size=5) Pageable pageable) { // yml 설정을 따르고 싶다면 @PageableDefault 제거
    public List<PostResponse> getList(@ModelAttribute PostSearch postSearch) {
        return postService.getList(postSearch);
    }


}
