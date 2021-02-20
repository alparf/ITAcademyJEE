package by.academy.specification.impl.group;

import by.academy.model.bean.Group;
import by.academy.specification.ISpecification;

public class AllGroupSpecification implements ISpecification<Group> {

    @Override
    public boolean isNotCorrect(Group group) {
        return false;
    }
}
