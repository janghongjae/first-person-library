# first-person-library-be
일인칭 서재 나만의 독서 코멘트


<p align="center">
  <img src="https://github.com/janghongjae/first-person-library/assets/115918352/e37e61fa-b8d6-4122-bff4-1263a1b5b194" height="500" width="800">
</p>

> AladinLibraryAPI</br></br>
> 인터페이스 AladinAPI
구현체로 AladinLibraryAPI 사용하시면됩니다

#예시
---
```
public class Test {

    private AladinAPI api;

    public Test(AladinAPI api) {
        this.api = api;
    }

    @GetMapping("/test")
    public AladinBookDTO test() {
        return api.getSearchLibrarys("자바");


    }
}
```

> 반환타입으로는 AladinBookDTO를 사용합니다

```
api.getSearchLibrarys(String keyword, int page)

```

- String 타입에 파라미터를 보내면 해당 키워드에 맞는 도서를 검색해줍니다 (검색어, 페이지)

## 🖥️ Commit  Message

<div align="center"> 

|Message|설명|
|:---:|:---|
|feat|새로운 기능 추가|
|fix|버그 수정|
|docs|문서 수정|
|style|코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우|
|refactor|코드 리팩토링|
|test|테스트 코드|
|chore |빌드 업무 수정, 패키지 매니저 수정|
|init |프로젝트 시작에 대한 커밋|
</div>
