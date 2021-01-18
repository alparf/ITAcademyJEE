package by.academy.service.impl;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.factory.CoachFactory;
import by.academy.repository.ICoachRepository;
import by.academy.repository.IUserRepository;
import by.academy.repository.impl.CoachRepositoryDB;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.ICoachService;
import by.academy.specification.CoachDBSpecifications;
import by.academy.specification.UserDBSpecifications;

import java.util.List;
import java.util.Optional;


public class CoachService implements ICoachService {

    @Override
    public List<Coach> getAll() {
        ICoachRepository repository = new CoachRepositoryDB();
        return repository.query(CoachDBSpecifications.allCoaches());
    }

    @Override
    public boolean addSalary(long coachId, int salary) {
        ICoachRepository coachRepository = new CoachRepositoryDB();
        IUserRepository userRepository = new UserRepositoryDB();
        List<User> userList = userRepository.query(UserDBSpecifications.userById(coachId));
        Optional<User> user = userList.stream().findFirst();
        if(user.isPresent()) {
            return coachRepository.addSalary(CoachFactory.createCoach(user.get()), salary);
        }
        return false;
    }
}
