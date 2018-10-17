package br.com.neolog.welcomekit.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter()
public class LoginFilter
    implements
        Filter
{

    @Override
    public void init(
        final FilterConfig filterConfig )
        throws ServletException
    {

    }

    @Override
    public void doFilter(
        final ServletRequest request,
        final ServletResponse response,
        final FilterChain chain )
        throws IOException,
            ServletException
    {

    }

    @Override
    public void destroy()
    {

    }

}
