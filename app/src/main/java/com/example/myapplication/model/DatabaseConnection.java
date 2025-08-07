package com.example.myapplication.model;

public class DatabaseConnection {
//
//    private static DatabaseConnection instance;
//    private String username = "hekim";
//    private String password = "hKM.6406";
//    private String dbName = "tractordealermanagementsystem";
//    private String host = "127.0.0.1";
//    private int port = 3306;
//    private Connection conn = null;
//
//
//    public DatabaseConnection() {
//
//        // jdbc:mysql://localhost:3306/firma
//        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("Driver bulunamadi");
//        }
//
//        try {
//            conn = DriverManager.getConnection(url, username, password);
//            System.out.println("Baglanti basarili.");
//        } catch (SQLException e) {
//            System.out.println("Baglanti basarisiz.");
//            e.printStackTrace();
//
//        }
//    }
//
//    // veritabanı bağlantısını tek defa yapıp tüm projede onu paylaşarak kullanmak
//    // için
//    //Singleton pattern for database connection
//    public static DatabaseConnection getInstance() {
//        if (instance == null) {
//            instance = new DatabaseConnection();
//
//            System.out.println("aasaa");
//        }
//        return instance;
//    }
//
//    public Connection getConnection() {
//        if (conn == null) {
//            // Bağlantı oluşturulmamışsa, yeni bir bağlantı oluşturun
//            String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName + "?useUnicode=true&characterEncoding=utf8";
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//                conn = DriverManager.getConnection(url, username, password);
//                System.out.println("Yeni bağlantı oluşturuldu.");
//            } catch (ClassNotFoundException e) {
//                System.out.println("Driver bulunamadi");
//                e.printStackTrace();
//
//            } catch (SQLException e) {
//                System.out.println("Baglanti basarisiz.");
//                e.printStackTrace();
//            }
//        }
//        return conn;
//    }

}

