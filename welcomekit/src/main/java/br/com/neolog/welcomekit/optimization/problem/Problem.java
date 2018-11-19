package br.com.neolog.welcomekit.optimization.problem;

import java.util.List;

import br.com.neolog.welcomekit.optimization.Item;

public interface Problem
{

    List<Item> getItems();

    long getTargetValue();

    int getQuantitySum();

}