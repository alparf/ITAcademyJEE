package by.academy.service.impl;

import by.academy.exception.AppException;
import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.factory.CoachFactory;
import by.academy.repository.ICoachRepository;
import by.academy.repository.IRepository;
import by.academy.repository.impl.CoachRepositoryDB;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.ICoachService;
import by.academy.specification.CoachDBSpecifications;
import by.academy.specification.UserDBSpecifications;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class CoachService implements ICoachService {

    @Override
    public List<Coach> getAll() {
        List<Coach> coachList = new LinkedList<>();
        try {
            ICoachRepository repository = new CoachRepositoryDB();
            coachList = repository.query(CoachDBSpecifications.allCoaches());
        } catch (AppException e) {
            e.printStackTrace();
        }
        return coachList;
    }

    @Override
    public boolean addSalary(long coachId, int salary) {
        Optional<User> user = Optional.empty();
        ICoachRepository coachRepository = new CoachRepositoryDB();
        try {
            IRepository<User> userRepository = new UserRepositoryDB();
            List<User> userList = userRepository.query(UserDBSpecifications.userById(coachId));
            user = userList.stream().findFirst();
        } catch (AppException e) {
            e.printStackTrace();
        }
        if(user.isPresent()) {
            return coachRepository.addSalary(CoachFactory.createCoach(user.get()), salary);
        }
        return false;
    }
}
