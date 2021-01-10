package by.academy.service.impl;

import by.academy.model.bean.Coach;
import by.academy.repository.ICoachRepository;
import by.academy.repository.impl.CoachRepositoryInMemory;
import by.academy.service.ICoachService;
import by.academy.specification.impl.inmemory.CoachInMemoryGetAll;

import java.util.List;


public class CoachService implements ICoachService {
    @Override
    public List<Coach> getAll() {
        ICoachRepository repository = new CoachRepositoryInMemory();
        return repository.query(new CoachInMemoryGetAll());
    }

    @Override
    public void addSalary(Coach coach, Integer salary) {
        ICoachRepository repository = new CoachRepositoryInMemory();
        repository.addSalary(coach, salary);
    }
}
