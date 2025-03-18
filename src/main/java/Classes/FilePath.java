/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Classes;


import java.nio.file.Paths;

public class FilePath {
    private static final String BASE_PATH = Paths.get(System.getProperty("user.dir"), "src", "main", "java", "TextFiles").toString();

    public static String itemPath = Paths.get(BASE_PATH, "items.txt").toString();
    public static String posPath = Paths.get(BASE_PATH, "pos.txt").toString();
    public static String requestPath = Paths.get(BASE_PATH, "request.txt").toString();
    public static String salesPath = Paths.get(BASE_PATH, "Sales.txt").toString();
    public static String paymentPath = Paths.get(BASE_PATH, "Payment.txt").toString();
    public static String supplierPath = Paths.get(BASE_PATH, "suppliers.txt").toString();
    public static String userPath = Paths.get(BASE_PATH, "user.txt").toString();
}