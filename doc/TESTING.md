# Testing
Spring test 에 관한 참고 자료들

## @SpringBootTest
`@RunWith(SpringRunner.class)`문구와 같이 사용된다.
사용하게되면 앱의 설정을 그대로 사용하며 테스트를 수행하게 된다.

`@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)`
위 와 같이 설정하여 테스트 간에 포트 충돌을 피할 수 있다. 
`@LocalServerPort private int port`를 통해 포트 설정을 주입 할 수 도 있다.

## @MockMvc
`@AutoConfigureMockMvc` 어노테이션을 클래스에 추가하여 MockMvc 자동으로 설정한다.
`MockMvc` 앱을 가상의 객체로 만들어 사용할 수 있는 도구다.

```java
@Runwith(SpringRunnger.class)
@WebMvcTest
public class WebLayerTest { 
    //...
}
```

위와 같이 테스트 클래스를 설정하면 다른 계층과의 연계 없이 web 계층만을 사용한다.
마찬가지로 `@WebMvcTest`를 `@DataJpaTest`로 변환하면 JPA domain 계층만을 테스트하는 환경을 구성한다.

`@MockBean` 어노테이션은 가상의 객체를 생성하여 테스트하게 해준다.

## @DataJpaTest
가상의 데이터베이스를 구축하여 테스트 환경을 구성하는것이 목적이다.
다음과 같은 4가지 작업을 기반으로 한다.

    1. H2 데이터베이스를 구성한다.
    2. hibernate, spring data 와 DataSource를 설정한다.
    3. `@EntityScan`을 시행한다.
    4. SQL 로깅을 시작한다.

* `TestEntityManager`

    주로 테스트 데이터 저장에 사용된다.
* `@DataJpaTest` 환경에서는 Auditing 설정을 로드하지 않음으로 Auditing관련 테스트는 서비스 혹은 컨트롤러에서 수행한다.