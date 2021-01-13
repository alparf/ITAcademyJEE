package by.academy.service.impl;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.factory.CoachFactory;
import by.academy.repository.ICoachRepository;
import by.academy.repository.IUserRepository;
import by.academy.repository.impl.CoachRepositoryInMemory;
import by.academy.repository.impl.UserRepositoryInMemory;
import by.academy.service.ICoachService;
import by.academy.specification.CoachInMemorySpecifications;
import by.academy.specification.UserInMemorySpecifications;

import java.util.List;


public class CoachService implements ICoachService {

    @Override
    public List<Coach> getAll() {
        ICoachRepository repository = new CoachRepositoryInMemory();
        return repository.query(CoachInMemorySpecifications.allCoaches());
    }

    @Override
    public void addSalary(long coachId, int salary) {
        final int USER = 0;
        ICoachRepository repository = new CoachRepositoryInMemory();
        IUserRepository userRepository = new UserRepositoryInMemory();
        List<User> userList = userRepository.query(UserInMemorySpecifications.userById(coachId));
        User user = null;
        if (!userList.isEmpty()) {
            user = userList.get(USER);
        }
        repository.addSalary(CoachFactory.createCoach(user), salary);
    }
}
