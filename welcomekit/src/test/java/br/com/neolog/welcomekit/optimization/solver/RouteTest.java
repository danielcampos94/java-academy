package br.com.neolog.welcomekit.optimization.solver;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.neolog.welcomekit.optimization.problem.Problem;

@RunWith( MockitoJUnitRunner.class )
public class RouteTest
{
    @Mock
    private Solver exactSpy;
    @Mock
    private Solver heuristicSpy;

    private Route route;

    @Test( expected = NullPointerException.class )
    public void shouldThrowNullPointerExcetptionWhenParameterIsNull()
    {
        route.optimize( null );
    }

    @Test
    public void testName()
    {
        final Route route = new Route( heuristicSpy, exactSpy );
        final Problem problemMocked = Mockito.mock( Problem.class );
        when( problemMocked.getQuantitySum() ).thenReturn( 22 );

        route.optimize( problemMocked );
        verify( exactSpy, times( 0 ) ).optimize( problemMocked );
        verify( heuristicSpy, times( 1 ) ).optimize( problemMocked );
    }

    @Test
    public void testName2()
    {
        final Route route = new Route( heuristicSpy, exactSpy );
        final Problem problemMocked2 = Mockito.mock( Problem.class );
        when( problemMocked2.getQuantitySum() ).thenReturn( 21 );

        route.optimize( problemMocked2 );
        verify( exactSpy, times( 1 ) ).optimize( problemMocked2 );
        verify( heuristicSpy, times( 0 ) ).optimize( any() );
    }
}