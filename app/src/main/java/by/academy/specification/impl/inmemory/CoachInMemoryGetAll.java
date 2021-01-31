package by.academy.specification.impl.inmemory;

import by.academy.model.bean.Coach;
import by.academy.specification.ISpecification;

public class CoachInMemoryGetAll implements ISpecification<Coach> {

    @Override
    public boolean specification(Coach coach) {
        return true;
    }
}
