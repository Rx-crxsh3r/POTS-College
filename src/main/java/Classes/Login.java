package Classes;


import java.util.List;

public class Login extends ViewUsers {

    // Method to validate user and determine role
    public String determineRole(String username, String password) {
        List<User> users = getUsers(); // Utilize the getUsers method from ViewUsers
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user.getRole(); // Return role if login is successful
            }
        }
        return "Invalid username or password.";
    }

    public String getLoggedInUserId(String username) {
        List<User> users = getUsers(); // Utilize the getUsers method from ViewUsers
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user.getId(); // Return the user ID
            }
        }
        return null; // Return null if no user is found
    }
}
