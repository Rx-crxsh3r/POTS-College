package Classes;

public class User {
    private String id, name, username, password, role;   
    
    public User(){
        
    }
    // Constructor to initialize a User from given parameters
    public User(String id, String name, String username, String password, String role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    // Method to return user info as a formatted String to be saved in a file
    @Override
    public String toString() {
        return id + "," + name + "," + username + "," + password + "," + role;
    }

    // Static method to create a User object from a line of text
    public static User fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("Invalid data format in line: " + line);
        }
        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}
