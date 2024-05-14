package newws.authorization.security.context;

import java.util.function.Supplier;

public interface CustomHolderStrategy {

    void clearContext();

    NewwsSecurityContext getContext();

    default Supplier<NewwsSecurityContext> getDeferredContext() {
        return this::getContext;
    }

    void setContext(NewwsSecurityContext context);


    default void setDeferredContext(Supplier<NewwsSecurityContext> deferredContext) {
        setContext(deferredContext.get());
    }

    NewwsSecurityContext createEmptyContext();
}
