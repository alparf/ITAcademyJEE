package by.academy.specification.impl;

import by.academy.model.bean.Coach;
import by.academy.specification.ICoachSpecification;

public class CoachSpecificationGetAll implements ICoachSpecification {

    @Override
    public boolean specification(Coach coach) {
        return true;
    }
}
