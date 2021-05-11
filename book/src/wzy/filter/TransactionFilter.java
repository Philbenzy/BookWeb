package wzy.filter;

import wzy.utils.JDBCUtils;
import javax.servlet.*;
import java.io.IOException;

/**
 * TransactionFilter 事务过滤器，给每一个事务添加回滚与提交
 */
public class TransactionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            filterChain.doFilter(servletRequest,servletResponse);
            JDBCUtils.commitAndClose();
        }catch (Exception e){
            JDBCUtils.rollbackAndClose();
            e.printStackTrace();
            throw new RuntimeException(e); //出错后抛给tomcat
        }
    }

    @Override
    public void destroy() {

    }
}
