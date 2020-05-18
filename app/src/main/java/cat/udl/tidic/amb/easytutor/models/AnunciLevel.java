package cat.udl.tidic.amb.easytutor.models;
/*primaria = "P"
    eso = "E"
    batxillerat="B"
    grau_mitja="M"
    grau_superior="S"
    universitat="U"*/
public enum AnunciLevel {
    P("P","primaria"),E("E","eso"),B("B","batxillerat"),M("M","grau_mitja"),S("S","grau_superior"),U("U","universitat");

    String id;
    String name;

    public String getName(){
        return name;
    }

    AnunciLevel(String _id, String _name){
        id = _id;
        name = _name;
    }
}
