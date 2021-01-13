package by.academy.specification;

import by.academy.specification.impl.db.CoachDBGetAll;

public class CoachDBSpecifications {
    public static ICoachSpecification allCoaches() {
        return new CoachDBGetAll();
    }
}
