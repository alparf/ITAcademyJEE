package by.academy.model.factory;

import by.academy.model.bean.Coach;
import by.academy.model.bean.User;

import java.util.Deque;
import java.util.LinkedList;

public class CoachFactory {
    public static Coach createCoach(long id, User user) {
        return new Coach(id, user, new LinkedList<>());
    }

    public static Coach createCoach(long id, User user, Deque<Integer> salaries) {
        return new Coach(id, user, salaries);
    }
}
