package newws.authorization.security.context;


import org.springframework.util.Assert;

import java.util.function.Supplier;

public class NewwsSecurityHolderStrategy implements CustomHolderStrategy {

    // 요청이 들어오게 되면 스레드 생성 -> 스레드 에 대한 무결성 유지를 위해서 ( 데이터 일관성 ) 생성
    // 웹 애플리케이션에서 각 HTTP 요청을 처리하는 스레드에서는 요청마다 고유한 상태를 유지하기 위해 ThreadLocal을 사용할 수 있음.
    // 단 request scope 로 처리할 경우
    // request 요청 이후 소멸함으로 필요하지 않을 것 같습니다. jwt token으로 사용한다면, 뭐가 더 나은 방법일지..
    private static final ThreadLocal<Supplier<NewwsSecurityContext>> contextHolder = new ThreadLocal<>();

    //  Scope가 종료된 이후 처리하여 메모리 누수 방지 해야 합니다. response interceptor에서 구현하는 것이 나을것 같습ㄴ.. 흠
    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public NewwsSecurityContext getContext() {
        return getDeferredContext().get();
    }

    //       Supplier<SecurityContext> supplier = contextHolder.get();
    //        return (supplier != null) ? supplier.get() : SecurityContextHolder.createEmptyContext();
    //  Spring Security에서는 이렇게 풀어서 작성
    @Override
    public Supplier<NewwsSecurityContext> getDeferredContext() {
        // 매개 변수 없이 제공
        Supplier<NewwsSecurityContext> result = contextHolder.get();
        if (result == null) {
            NewwsSecurityContext context = createEmptyContext();
            result = () -> context;
            contextHolder.set(result);
        }
        return result;
    }

    // filter애서 지연 으로 생성하는게 맞을까, 지연 방식일 경우 올바르지 않은 방식은 것같다.
    @Override
    public void setDeferredContext(Supplier<NewwsSecurityContext> deferredContext) {
        Assert.notNull(deferredContext, "Only non-null Supplier instances are permitted");
        Supplier<NewwsSecurityContext> notNullDeferredContext = () -> {
            NewwsSecurityContext result = deferredContext.get();
            Assert.notNull(result, "A Supplier<SecurityContext> returned null and is not allowed.");
            return result;
        };
        contextHolder.set(notNullDeferredContext);
    }

    @Override
    public void setContext(NewwsSecurityContext context) {
        if (context != null)
            contextHolder.set(() -> context);
    }
    @Override
    public NewwsSecurityContext createEmptyContext() {
        return new NewwsSecurityContext();
    }

}
