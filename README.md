# blogservice

질문 목록
1. Blog 서비스를 만드는 것이 목적인지라 Member를 통해 Blog를 생성하는 것이 아닌 Blog를 생성하기 위해   
Member가 필요한 방법으로 풀어봤습니다. 문제 없나요?
2. static method를 통해 Blog Entity의 생성 지점을 하나로 묶어놨습니다. 이 때 직접적으로 인스턴스 메서드를 호출하진 않지만   
생성자를 통해 간접적으로 호출하는 것에 문제가 없는지 궁금합니다.
3. 엔티티의 양방향 연관관계를 맺어주는 메서드에서 매개변수로 받은 엔티티의 참조가 null인지 검증하는 로직에서   
javax.validation 패키지에 속한 @NotNull등을 사용해도 괜찮을까요? 자바 표준 기술이지만    
주로 Controller layer에서 Dto를 검증할 때 사용해서 그런지 Entity가 이 기술에 의존해도 되는지, 추가로 리뷰어님이 사용하시는 검증 방법도 궁금합니다.
4. InterceptorService 클래스에서 사용한 currentRequestAttributes는 실무에서도 사용할 수 있는 기술인가요? 코드를 최대한 깔끔하게 짜보려고 Session등을 매개변수로 넘기지 않으려 적용해봤습니다. 이 것 말고도 ThreadLocal처럼 동시성 문제를 피하며 사용할 수 있는 공통 저장소가 있을까요?
5. HttpSession을 injection 받아 필드 레벨에서 사용하면 동시성 문제가 있을까요?
6. mvc에서 사용할 수 있는  StringUtils 처럼 유용한 클래스들이 무엇이 있나요?
7. Spring Security를 현업에서도 사용하나요?
8. 컨트롤러의 @Valid로 DTO 검증 시 발생 할 수 있는 BindException에 대한 공통적인 예외처리를 하기 위해서 Reflection을 사용해 봤는데 스프링에서 이것과 같은 처리를 해주는 
