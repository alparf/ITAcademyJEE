package by.academy.specification.impl.inmemory;

import by.academy.model.bean.Coach;
import by.academy.specification.ICoachSpecification;

public class CoachInMemoryGetAll implements ICoachSpecification {

    @Override
    public boolean specification(Coach coach) {
        return true;
    }
}
