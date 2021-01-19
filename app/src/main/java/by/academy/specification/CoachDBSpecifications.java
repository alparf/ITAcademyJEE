package by.academy.specification;

import by.academy.model.bean.Coach;
import by.academy.specification.impl.db.CoachDBGetAll;

public class CoachDBSpecifications {
    public static ISpecification<Coach> allCoaches() {
        return new CoachDBGetAll();
    }

}
