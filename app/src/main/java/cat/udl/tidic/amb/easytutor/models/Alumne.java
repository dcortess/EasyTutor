package cat.udl.tidic.amb.easytutor.models;

import com.google.gson.annotations.SerializedName;

public class Alumne extends User {
    @SerializedName("materia")
    private String subject;

    public Alumne(String subject) {
        super();
        this.subject = subject;
    }

    public Alumne(String name, String surname, String username, String email, String gender, String phone, String subject) {
        super(name, surname, username, email, gender, phone);
        this.subject = subject;
    }

    public Alumne(String name, String surname, String username, String email, String gender, String phone, String photo, String zone, String subject) {
        super(name, surname, username, email, gender, phone, photo, zone);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
