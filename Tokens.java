public class Tokens implements Cloneable{
    private final String Id;        private final String Name;        private int Number;

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }

    public Tokens(String Id, String Name, int Number) {
        this.Id = Id;
        this.Name = Name;
        this.Number = Number;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }
}
