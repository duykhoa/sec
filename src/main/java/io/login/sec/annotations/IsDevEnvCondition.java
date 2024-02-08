package io.login.sec.annotations;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

public class IsDevEnvCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment env = context.getEnvironment();
        Optional<String> appEnv = Optional.ofNullable(env.getProperty("APP_ENV", String.class));

        return appEnv.isEmpty() || appEnv.get().equalsIgnoreCase("dev");
    }
}
