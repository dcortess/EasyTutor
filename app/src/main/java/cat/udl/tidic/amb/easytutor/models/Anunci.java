package cat.udl.tidic.amb.easytutor.models;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/*id = Column(Integer, primary_key=True)
        title = Column(Unicode(250), nullable=False)
        level = Column(Enum(AnunciLevelEnum), nullable=False)
        description = Column(UnicodeText, nullable=False)
        price = Column(Float, nullable=False)
        admits_negotiation = Column(Boolean, default=False, nullable=False)
        distance_to_serve = Column(Integer)
        type = Column(Enum(AnunciTypeEnum), nullable=False)*/
public class Anunci {

    @SerializedName("id")
    private String id;
    @SerializedName("title")
    private String title;
    @SerializedName("level")
    private AnunciLevel level;
    @SerializedName("description")
    private String description;
    @SerializedName("price")
    private float price;
    @SerializedName("admits_negotiation")
    private boolean admits_negotation;
    @SerializedName("distance_to_serve")
    private int distance_to_serve;
    @SerializedName("type")
    private AnunciType type;

    Anunci(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AnunciLevel getLevel() {
        return level;
    }

    public void setLevel(AnunciLevel level) {
        this.level = level;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isAdmits_negotation() {
        return admits_negotation;
    }

    public void setAdmits_negotation(boolean admits_negotation) {
        this.admits_negotation = admits_negotation;
    }

    public int getDistance_to_serve() {
        return distance_to_serve;
    }

    public void setDistance_to_serve(int distance_to_serve) {
        this.distance_to_serve = distance_to_serve;
    }

    public AnunciType getType() {
        return type;
    }

    public void setType(AnunciType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Anunci)) return false;
        Anunci anunci = (Anunci) o;
        return this.price == getPrice() &&
                this.admits_negotation == isAdmits_negotation() &&
                this.distance_to_serve == getDistance_to_serve() &&
                this.id.equals(getId()) &&
                this.title.equals(getTitle()) &&
                this.level == anunci.level &&
                this.description.equals(getDescription()) &&
                this.type == anunci.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, level, description, price, admits_negotation, distance_to_serve, type);
    }
}
