package by.academy.specification.impl;

import by.academy.model.bean.Salary;
import by.academy.specification.ISpecification;

public class FindSalaryByCoachId implements ISpecification<Salary> {

    private final long coachId;

    public FindSalaryByCoachId(long coachId) {
        this.coachId = coachId;
    }

    @Override
    public boolean isNotCorrect(Salary salary) {
        return this.coachId != salary.getCoach().getId();
    }
}
