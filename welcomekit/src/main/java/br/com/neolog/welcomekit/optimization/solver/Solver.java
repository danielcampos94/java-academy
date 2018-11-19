package br.com.neolog.welcomekit.optimization.solver;

import br.com.neolog.welcomekit.optimization.problem.Problem;
import br.com.neolog.welcomekit.optimization.solution.Solution;

public interface Solver
{
    Solution optimize(
        Problem problem );
}
