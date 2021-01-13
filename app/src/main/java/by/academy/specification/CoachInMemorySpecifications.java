package by.academy.specification;

import by.academy.specification.impl.inmemory.CoachInMemoryGetAll;

public class CoachInMemorySpecifications {
    public static ICoachSpecification allCoaches() {
        return new CoachInMemoryGetAll();
    }
}
