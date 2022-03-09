import java.util.Comparator;
public class Sorter implements Comparator<Tokens>{
    public int compare(Tokens o1,Tokens o2){
        String  numberOne = Integer.toString(o1.getNumber());
        String  numberTwo = Integer.toString(o2.getNumber());
        return numberOne.compareTo(numberTwo);
    }
}
