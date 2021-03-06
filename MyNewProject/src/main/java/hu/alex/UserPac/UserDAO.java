package hu.alex.UserPac;

import java.util.List;

public interface UserDAO extends AutoCloseable{
    public void saveUser(User u);
    public void deleteUser(User u);
    public void updateUser(User u);
    public List<User> getUsers();
}
