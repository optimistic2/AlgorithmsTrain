package exception;

import org.springframework.retry.RetryCallback;
import org.springframework.retry.RetryContext;
import org.springframework.retry.RetryPolicy;
import org.springframework.retry.backoff.NoBackOffPolicy;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

import java.util.HashMap;
import java.util.Map;

public abstract class SimpleRetryTemplate<T> {

    private Map<Class<? extends Throwable>, RetryPolicy> policyMap = new HashMap<>();

    /**
     * 设置要重试的异常和重试的次数
     *
     * @param exception
     * @param retryTimes
     * @return
     */
    public SimpleRetryTemplate<T> retryWithException(Class<? extends Throwable> exception, int retryTimes) {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(retryTimes);
        policyMap.put(exception, retryPolicy);
        return this;
    }

    /**
     * 使用默认的超时策略
     *
     * @return
     */
    public SimpleRetryTemplate<T> withDefaultTimeoutPolicy() {
        return retryWithException(Exception.class, 3);
    }

    /**
     * 设置不重试的异常
     *
     * @param exception
     * @return
     */
    public SimpleRetryTemplate<T> noRetryWithException(Class<? extends Throwable> exception) {
        SimpleRetryPolicy retryPolicy = new SimpleRetryPolicy();
        retryPolicy.setMaxAttempts(0);
        policyMap.put(exception, retryPolicy);
        return this;
    }

    /**
     * 执行并重试
     *
     * @return
     * @throws Exception
     */
    public T executeWithRetry() throws Exception {
        RetryTemplate template = newRetryTemplate();
        RetryCallback<T, Exception> callback = new RetryCallback<T, Exception>() {
            @Override
            public T doWithRetry(RetryContext retryContext) throws Exception {
                if (retryContext.getRetryCount() > 0) {
                    System.out.println(String.format("#SimpleRetryTemplate.executeWithRetry# retry count:{%s} throwable:{%s}", retryContext.getRetryCount(), retryContext.getLastThrowable()));
                }
                return invoke();
            }
        };
        return template.execute(callback);
    }

    /**
     * 构建重试策略
     *
     * @return
     */
    private RetryTemplate newRetryTemplate() {
        RetryTemplate template = new RetryTemplate();
        ExceptionClassifierRetryPolicy policy = new ExceptionClassifierRetryPolicy();
        policy.setPolicyMap(policyMap);
        template.setRetryPolicy(policy);
        template.setBackOffPolicy(new NoBackOffPolicy());
        return template;
    }

    /**
     * 要重试的逻辑
     *
     * @return
     * @throws Exception
     */
    public abstract T invoke() throws Exception;
}