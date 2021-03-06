package br.com.neolog.welcomekit.optimization.solver;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.neolog.welcomekit.optimization.Item;
import br.com.neolog.welcomekit.optimization.problem.ProblemImpl;
import br.com.neolog.welcomekit.optimization.solution.Solution;

@RunWith( SpringJUnit4ClassRunner.class )
@SpringBootTest
public class ExactTest
{
    @Autowired
    private Exact subject;

    @Test( expected = NullPointerException.class )
    public void shouldThrowNullPointerExcetptionWhenParameterIsNull()
    {
        subject.optimize( null );
    }

    @Test
    public void shouldReturnEmptySolutionWhenListOfProblemItemsIsEmpty()
    {
        final List<Item> items = new LinkedList<>();
        final ProblemImpl problem = ProblemImpl.create( items, 100_000 );
        final Solution optimizedSolution = subject.optimize( problem );
        assertThat( optimizedSolution ).isEqualTo( Solution.empty() );
    }

    @Test
    public void shouldReturnSumOfAllItemsWhenThisSumIsLessThanTarget()
    {
        final List<Item> items = new LinkedList<>();

        items.add( Item.create( 10, 5_000, 5 ) );
        items.add( Item.create( 11, 3_000, 2 ) );

        final ProblemImpl problem = ProblemImpl.create( items, 100_000 );

        final Solution optimizedSolution = subject.optimize( problem );
        assertThat( optimizedSolution.getValueSum() ).isEqualTo( 31_000 );
    }

    @Test
    public void shouldReturnPerfectSolutionWhenIsPossible()
    {
        final List<Item> items = new LinkedList<>();

        items.add( Item.create( 10, 10_000, 1 ) );
        items.add( Item.create( 11, 9_000, 1 ) );
        items.add( Item.create( 12, 7_000, 1 ) );
        items.add( Item.create( 13, 4_000, 1 ) );
        items.add( Item.create( 14, 2_000, 1 ) );
        items.add( Item.create( 15, 1_000, 1 ) );

        final ProblemImpl problem = ProblemImpl.create( items, 15_000 );

        final Solution optimizedSolution = subject.optimize( problem );
        assertThat( optimizedSolution.getValueSum() ).isEqualTo( 15_000 );
    }
}
