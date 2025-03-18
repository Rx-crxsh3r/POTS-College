
package Classes;

import Admin.LoginFrame;

public class mainClass {
    public static void main(String[] args) {
        // Launch the LoginFrame
        java.awt.EventQueue.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true); // Make the LoginFrame visible
        });
    }
}
  