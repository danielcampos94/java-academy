package br.com.neolog.welcomekit.filters;

import static br.com.neolog.welcomekit.CustomerLocal.removeCurrentCustomerId;
import static br.com.neolog.welcomekit.CustomerLocal.setCurrentCustomerId;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.neolog.welcomekit.models.Session;
import br.com.neolog.welcomekit.repository.SessionRepository;

@WebFilter( urlPatterns = {
    "/cart/*",
    "/customer/update",
    "/customer/search/email",
    "/customer/search/all",
    "/customer/inactivate",
    "/product/*",
    "/category/*",
    "/stock/*"
} )
public class AuthenticationFilter
    implements
        Filter
{
    @Autowired
    private SessionRepository sessionRepository;

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
        final HttpServletRequest servletRequest = (HttpServletRequest) request;
        final HttpServletResponse servletResponse = (HttpServletResponse) response;
        final String token = servletRequest.getHeader( "token" );

        if( isValid( token ) ) {
            final Session session = sessionRepository.findByToken( token );
            final Integer currentCustomerId = session.getCustomer().getId();
            setCurrentCustomerId( currentCustomerId );
            try {
                chain.doFilter( servletRequest, servletResponse );
            } finally {
                removeCurrentCustomerId();
            }
        } else {
            servletResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "Invalid Token" );
        }

    }

    @Override
    public void destroy()
    {

    }

    private boolean isValid(
        final String token )
    {
        if( token == null || token.isEmpty() ) {
            return false;
        }
        final Session session = sessionRepository.findByToken( token );
        if( session == null || session.getExpirationDate().isBeforeNow() ) {
            return false;
        }
        return true;
    }

}
