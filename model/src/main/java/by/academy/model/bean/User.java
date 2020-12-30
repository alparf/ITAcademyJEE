package by.academy.model.bean;

import by.academy.model.constant.ExceptionConstant;

public class User {
    private String fio;
    private int age;
    private String userName;
    private String password;
    private UserType userType;

    public User() {
    }

    public User(String fio, int age, String userName, String password, UserType userType)
            throws IllegalArgumentException {
        this.fio = fio;
        this.setAge(age);
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getAge() {
        return age;
    }

    /**
     * Set age if age >= 0 or throw IllegalArgumentException if age < 0
     * @param age - age of user
     * @throws IllegalArgumentException
     */

    public void setAge(int age) throws IllegalArgumentException {
        if(age < 0) {
            throw new IllegalArgumentException(ExceptionConstant.INVALID_AGE_VALUE);
        } else {
            this.age = age;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "fio='" + fio + '\'' +
                ", age=" + age +
                ", userName='" + userName + '\'' +
                ", userType=" + userType +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if((null == obj) && (null == this.userName)) {
            return false;
        }
        return this.userName.equals(((User) obj).getUserName());
    }
}
