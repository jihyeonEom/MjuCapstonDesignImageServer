# MjuCapstonDesignImageServer
명지대 자연캠 캡스톤 디자인 플라스틱 AI 모델 이미지 서버

1. Lombok을 사용합니다. 없으면 설치해주세요.<br/>

2. 이미지 사이즈가 커서 업로드 되지 않으면 __src/main/resource/application.properties__에 다음을 추가해주세요.<br/>
<pre>
  <code>
    spring.servlet.multipart.maxFileSize=10MB
    spring.servlet.multipart.maxRequestSize=10MB
  </code>
</pre>

3. 저는 8000번대 포트를 사용하고 있습니다. 디폴트 포트(8080)이 아닌 다른 포트를 사용하고 싶으시면 application.properties에 다음을 추가해주세요.<br/>
<pre>
  <code>
    server.port=(포트 넘버)
  </code>
</pre>

4. __MemberController__에 다음 코드를 수정해주세요.<br/>
```java
@GetMapping(value="images/{member_id}/result")
    public ResponseEntity<ResponseDto<Void>> getImageResult(@PathVariable("member_id") UUID memberId) throws IOException {
        // ai_server.py가 있는 디렉토리 절대경로
        String path = "";
        this.memberService.getImageResult(memberId, path);
        return new ResponseEntity<>(HttpStatus.OK);
    }
```

5. __MemberRepository__에 다음 코드를 수정해주세요.<br/>
```java
// 파일 업로드 경로
    private final String filePath = "";
```
