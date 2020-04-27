package personal.zhou.travelshare.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.springframework.util.StopWatch;

import java.util.Properties;

@Slf4j
public class TimeSpendInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // Invocation 的参数是看拦截的方法来的，这里 query 方法第一个参数是语句对象
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        String id = mappedStatement.getId();
        Object proceed = null;
        try {
            proceed = invocation.proceed();
        } finally {
            stopWatch.stop();
            log.info("sqlId:" + id + " 执行时间为:" + stopWatch.getTotalTimeMillis() + " ms");
        }
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
