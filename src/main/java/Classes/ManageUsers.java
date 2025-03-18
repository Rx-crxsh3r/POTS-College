package Classes;

import static Classes.FilePath.userPath;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ManageUsers {
    private static final String userPath = FilePath.userPath;
    Random random = new Random();

    // Generate a random ID
    public String randomId() throws IOException {
        File file = new File(userPath);
        if (!file.exists()) {
            file.createNewFile();
        }
        String id;
        do {
            id = String.valueOf(10000 + random.nextInt(90000)); // Generates a number between 10000 and 99999
        } while (!validateUnique(id, "id"));
        return id;
    }

    // Add new user to the file
    public User addUser(String name, String role) throws IOException {
        String id = randomId();
        String password = "password@" + id;
        String username = generateUsername(role, id);

        User newUser = new User(id, name, username, password, role);
        if (saveUserInfo(newUser)) {
            return newUser; // Return the created User object
        } else {
            return null; // Return null if the save fails
        }
    }

    
    public User getUserById(String id) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(userPath));
            for (String line : lines) {
                String[] data = line.split(","); // Assumes CSV format
                if (data[0].equals(id)) {
                    return new User(data[0], data[1], data[2], data[3], data[4]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Return null if the user is not found
    }

    public boolean updateUser(User updatedUser) {
        List<String> lines = new ArrayList<>();
        boolean userFound = false;

        try {
            List<String> allLines = Files.readAllLines(Paths.get(userPath));

            for (String line : allLines) {
                String[] data = line.split(",");
                if (data[0].equals(updatedUser.getId())) {
                    userFound = true;
                    // Update only non-null values
                    String name = updatedUser.getName() != null ? updatedUser.getName() : data[1];
                    String username = updatedUser.getUsername() != null ? updatedUser.getUsername() : data[2];
                    String password = updatedUser.getPassword() != null ? updatedUser.getPassword() : data[3];
                    String role = updatedUser.getRole() != null ? updatedUser.getRole() : data[4];

                    lines.add(String.join(",", data[0], name, username, password, role));
                } else {
                    lines.add(line); // Keep other users unchanged
                }
            }

            // If no matching user ID was found, return false
            if (!userFound) {
                return false;
            }

            // Write updated content back to the file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userPath))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    // Generate username based on role and ID
    public String generateUsername(String role, String id) {
        StringBuilder sb = new StringBuilder();
        String[] letters = role.split(" ");
        for (String letter : letters) {
            sb.append(letter.charAt(0));
        }
        return sb + id;
    }

    // Save user information to the file
    public boolean saveUserInfo(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userPath, true))) {
            writer.write(user.toString()); // Save the User object to the file
            writer.newLine();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    // Validate if ID or username is unique
    public boolean validateUnique(String value, String type) throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get(userPath));
        for (String line : fileContent) {
            User user = User.fromString(line);
            if ((type.equals("id") && user.getId().equals(value)) || 
                (type.equals("username") && user.getUsername().equals(value))) {
                return false;
            }
        }
        return true;
    }
    
         public String editUser(String id, int field, String newValue) throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get(userPath));
        boolean userFound = false;

        for (int i = 0; i < fileContent.size(); i++) {
            String line = fileContent.get(i);
            User user = User.fromString(line);

            if (user.getId().equals(id)) {
                userFound = true;

                // Update user details based on the field selected
                switch (field) {
                    case 1 -> user.setName(newValue);
                    case 2 -> {
                        if (!validateUnique(newValue, "username")) {
                            return "Error: Username already exists.";
                        }
                        user.setUsername(newValue);
                    }
                    case 3 -> user.setPassword(newValue);
                    case 4 -> user.setRole(newValue);
                    default -> {
                        return "Error: Invalid field number.";
                    }
                }

                // Update file content
                fileContent.set(i, user.toString());
                Files.write(Paths.get(userPath), fileContent);
                return "User updated successfully!";
            }
        }

        return userFound ? "User updated successfully!" : "Error: User with ID " + id + " not found.";
    }

    public void deleteUser(String id) throws IOException {
        List<String> fileContent = Files.readAllLines(Paths.get(userPath));
        boolean userFound = false;

        for (int i = 0; i < fileContent.size(); i++) {
            User user = User.fromString(fileContent.get(i));
            if (user.getId().equals(id)) {
                fileContent.remove(i);
                userFound = true;
                break;
            }
        }

        if (userFound) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(userPath))) {
                for (String line : fileContent) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }
    }
}
