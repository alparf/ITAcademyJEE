package by.academy.service.impl;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.model.bean.UserType;
import by.academy.model.factory.CoachFactory;
import by.academy.model.factory.UserFactory;
import by.academy.repository.ICoachRepository;
import by.academy.repository.impl.CoachRepositoryDB;
import by.academy.service.ICoachService;
import by.academy.specification.impl.jdbc.CoachDBGetAll;

import java.util.List;


public class CoachService implements ICoachService {

    @Override
    public List<Coach> getAll() {
        ICoachRepository repository = new CoachRepositoryDB();
        return repository.query(new CoachDBGetAll());
    }

    @Override
    public void addSalary(long coachId, int salary) {
        ICoachRepository repository = new CoachRepositoryDB();
        User user = UserFactory.createUser();
        user.setId(coachId);
        user.setUserType(UserType.COACH);
        Coach coach = CoachFactory.createCoach(user, null);
        repository.addSalary(coach, salary);
    }
}
