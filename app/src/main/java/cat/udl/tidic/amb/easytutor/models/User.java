package cat.udl.tidic.amb.easytutor.models;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("name")
    private String name;
    @SerializedName("surname")
    private String surname;
    @SerializedName("username")
    private String username;
    @SerializedName("email")
    private String email;
    @SerializedName("genere")
    private String gender;
    @SerializedName("phone")
    private String phone;
    @SerializedName("photo")
    private String photo;
    @SerializedName("zone")
    private String zone;

    public User() {

    }

    public User(String name, String surname, String username, String email, String gender, String phone) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
    }

    public User(String name, String surname, String username, String email, String gender, String phone, String photo, String zone) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.email = email;
        this.gender = gender;
        this.phone = phone;
        this.photo = photo;
        this.zone = zone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    @Override
    public String toString(){

        return ""+this.name+" "+this.surname+" "+this.email+" "+this.phone+" "+this.gender;
    }
}
