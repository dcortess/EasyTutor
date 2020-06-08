package cat.udl.tidic.amb.easytutor.models;

/*doy = "D"
    busco = "B"*/
public enum AnunciType {
    D("D","doy"),B("B","busco");

    String id;
    String name;

    AnunciType(String _id, String _name){
        id = _id;
        name = _name;
    }

    public String getType(){
        return this.id;
    }
}
