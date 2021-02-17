package by.academy.specification.impl;

import by.academy.model.bean.Salary;
import by.academy.specification.ISpecification;

public class SalaryByCoachIdSpecification implements ISpecification<Salary> {

    private final long coachId;

    public SalaryByCoachIdSpecification(long coachId) {
        this.coachId = coachId;
    }

    @Override
    public boolean isNotCorrect(Salary salary) {
        return false;
    }
}
