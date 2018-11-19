package br.com.neolog.welcomekit.optimization.solution;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.neolog.welcomekit.optimization.Item;
import br.com.neolog.welcomekit.optimization.recommendation.RecommendationItem;
import br.com.neolog.welcomekit.repository.ProductRepository;

@Component
public class SolutionConverter
{
    @Autowired
    private ProductRepository productRepository;

    public List<RecommendationItem> toRecommendation(
        final Solution solution )
    {
        final List<Item> solutionItems = solution.getSolutionItems();
        final List<RecommendationItem> recommendations = new LinkedList<>();

        for( final Item item : solutionItems ) {
            final String productName = productRepository.findByCode( item.getProductCode() ).getName();
            recommendations.add( RecommendationItem.create( productName, item.getProductCode(), item.getPrice(), item.getQuantity() ) );
        }
        return recommendations;
    }
}
