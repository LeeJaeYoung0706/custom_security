package newws.authorization.security.context;

import org.springframework.util.ReflectionUtils;

import java.util.function.Supplier;

/**
 * Context 접근 객체
 */
public class NewwsSecurityHolder {

    // 1가지 방법을 채택하고 있는데 확장성을 위해서 interface로 선언하는 것이 올바른 방법일 것인가,
    private static CustomHolderStrategy contextStrategy;
    // 초기화 횟수
    private static int initializeCount = 0;

    // 정적 초기화 블럭 > 클래스가 생성될 때 실행됨 ( Spring Application으로 치면 PostConstructor )
    static {
        initialize();
        initializeCount++;
    }

    private static void initialize() {
        try {
            contextStrategy = new NewwsSecurityHolderStrategy();
        } catch (Exception ex) {
            ReflectionUtils.handleReflectionException(ex);
        }
    }

    public static void clearContext() {
        contextStrategy.clearContext();
    }

    public static NewwsSecurityContext getContext() {
        return contextStrategy.getContext();
    }

    public static Supplier<NewwsSecurityContext> getDeferredContext() {
        return contextStrategy.getDeferredContext();
    }
    // 초기화 횟수 테스트
    public static int getInitializeCount() {
        return initializeCount;
    }

    public static void setContext(NewwsSecurityContext context) {
        contextStrategy.setContext(context);
    }

    public static void setDeferredContext(Supplier<NewwsSecurityContext> deferredContext) {
        contextStrategy.setDeferredContext(deferredContext);
    }

    public static NewwsSecurityContext createEmptyContext() {
        return contextStrategy.createEmptyContext();
    }

    // 스토리지 변경은 아직 사용할 예정이 없어서 추가하지 않습니다.
}
