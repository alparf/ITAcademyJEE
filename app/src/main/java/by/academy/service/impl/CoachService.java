package by.academy.service.impl;

import by.academy.exception.AppException;
import by.academy.model.bean.Coach;
import by.academy.model.bean.User;
import by.academy.repository.ICoachRepository;
import by.academy.repository.IRepository;
import by.academy.repository.impl.CoachRepositoryDB;
import by.academy.repository.impl.UserRepositoryDB;
import by.academy.service.ICoachService;
import by.academy.specification.CoachDBSpecifications;
import by.academy.specification.UserDBSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;


public class CoachService implements ICoachService {

    private static final Logger log = LoggerFactory.getLogger(CoachService.class);

    @Override
    public List<Coach> getAll() {
        List<Coach> coachList = new LinkedList<>();
        try {
            ICoachRepository repository = new CoachRepositoryDB();
            coachList = repository.query(CoachDBSpecifications.allCoaches());
        } catch (AppException e) {
            log.error(e.getMessage());
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
            log.error(e.getMessage());
        }
        if(user.isPresent()) {
            return coachRepository.addSalary(Coach.newBuilder()
                    .withUser(user.get())
                    .build(), salary);
        }
        return false;
    }
}
