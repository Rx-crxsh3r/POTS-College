package Classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ViewUsers {
    private static final String userPath =   FilePath.userPath;

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(userPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                User user = User.fromString(line); // Create a User instance from a line
                users.add(user);
            }
        } catch (IOException e) {
            System.err.println("An error occurred while reading the file: " + e.getMessage());
        }
        return users;
    }
}
