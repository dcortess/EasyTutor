package cat.udl.tidic.amb.easytutor.models;

import com.google.gson.annotations.SerializedName;

public class Professor extends User {
    @SerializedName("materia")
    private String subject;
    @SerializedName("titulo")
    private String title;
    @SerializedName("rating")
    private int rating;

    public Professor(String subject) {
        super();
        this.subject = subject;
    }

    public Professor(String name, String surname, String username, String email, String gender, String phone, String subject, int rating) {
        super(name, surname, username, email, gender, phone);
        this.subject = subject;
        this.rating = rating;
    }

    public Professor(String name, String surname, String username, String email, String gender, String phone, String photo, String zone, String subject, int rating) {
        super(name, surname, username, email, gender, phone, photo, zone);
        this.subject = subject;
        this.rating = rating;
    }

    public Professor(String subject, String title, int rating) {
        super();
        this.subject = subject;
        this.title = title;
        this.rating = rating;
    }

    public Professor(String name, String surname, String username, String email, String gender, String phone, String photo, String zone, String subject, String title, int rating) {
        super(name, surname, username, email, gender, phone, photo, zone);
        this.subject = subject;
        this.title = title;
        this.rating = rating;
    }
}
