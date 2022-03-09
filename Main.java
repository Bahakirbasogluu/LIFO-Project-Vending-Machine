// *******************************************************************************
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
// *******************************************************************************

public  class Main {
    //Printing the last version of Parts
    public static void PrintingBoard(ArrayList<Items> items, ArrayList<Stack<Items>> parts,FileWriter writer) throws IOException {
        for(Stack<Items> Parts : parts){
            int titleCounter = 0;       int numberCounter = 0;
            for(Items item : items){
                if (Parts.getName().equals(item.getName())){
                    if(titleCounter==0){
                        writer.write("\n"+Parts.getName()+":");
                        titleCounter+=1; }
                    writer.write("\n"+item.getId());
                    numberCounter+=1; } }
            if(numberCounter==0){
                writer.write("\n"+Parts.getName()+":"); }
            writer.write("\n"+"-----------"); } }
    //Printing the last version of TokenBox
    public static void PrintingTokenBox(Queue<Tokens> tokens,FileWriter writer) throws IOException {
        writer.write("\n"+"Token Box:");
        for( Tokens token : tokens){
            if(token.getNumber()>0){
                writer.write("\n"+token.getId()+" "+token.getName()+" "+token.getNumber()); } } }
    //Cloning the obje for desinging queue.
    public Object clone() throws CloneNotSupportedException{
        return super.clone(); }
    //******************************************************************* Main
    public static void main(String[] args) throws CloneNotSupportedException, IOException {
        ArrayList<Items> Items = new ArrayList<>();
        ArrayList<Tokens> Tokens = new ArrayList<>();
        ArrayList<Stack<Items>> PartStacks = new ArrayList<>();
        // Reading the items.txt file
        String[] itemLines = ReadFromFile.readFile(args[1]);
        assert itemLines != null;
        for (String itemLine : itemLines){
            String[] itemWords = itemLine.split(" ");
            Items.add(new Items(itemWords[0],itemWords[1])); }
        // Reading the parts.txt file
        String[] partLines = ReadFromFile.readFile(args[0]);
        // Creating the Stack for ordering items under their parts
        assert partLines != null;
        for (String partLine : partLines){
            Stack<Items> stack = new Stack<>(partLine);
            // In this for no need split cause every line has only 1 word. The partLine equals the word.
            for (Items item : Items ){ if(item.getName().equals(partLine)){ stack.push(item); } }   PartStacks.add(stack); }
        // Reading the tokens.txt file
        String[] tokenLines = ReadFromFile.readFile(args[2]);
        // Taking objects to Arraylist
        assert tokenLines != null;
        for (String tokenLine : tokenLines){
            String[] tokenWords = tokenLine.split(" ");
            Tokens.add(new Tokens(tokenWords[0],tokenWords[1],Integer.parseInt(tokenWords[2]))); }
        // Reversing for reading from the front side
            Tokens.sort(new Sorter().reversed());
        // Taking objects Arraylist to special Queue class list.
        Queue<Tokens> QueueTokens = new Queue<>();
        for (Tokens token : Tokens){ QueueTokens.enqueue(token); }
        // Reading the tasks.txt file
        // Applying Put and Buy method with algortims
        String[] taskLines = ReadFromFile.readFile(args[3]);
        assert taskLines != null;
        for (String taskLine : taskLines){
            String[] taskWords = taskLine.split("\t");
            if(taskWords[0].equals("PUT")){
                for ( int i = 1; i < taskWords.length; i++ ) {
                    String[] tasks = taskWords[i].split(",");
                    for( Stack<Items> partStack : PartStacks){
                        for(int j = 0 ; partStack.size()>j ; j++ ){
                            if(partStack.get(j).getName().equals(tasks[0])){
                                for(int k = 1 ; k<tasks.length ; k++){
                                    Items.add(new Items(tasks[k],tasks[0]));
                                    partStack.push(new Items(tasks[k],tasks[0])); }
                                break; } } } } }

            else{
                for(int i = 1 ; i < taskWords.length ; i++){
                    String[] tasks = taskWords[i].split(",");
                    int number = Integer.parseInt(tasks[1]);
                    int tempNumber = Integer.parseInt(tasks[1]);
                    String name = tasks[0];     Tokens token2;
                    for ( int j = 0; j < number; j++ ) {
                        for(int k = 1 ; k<Items.size()+1 ; k++){
                            if(Items.get(Items.size()-k).getName().equals(name)){
                                Items.remove(Items.get(Items.size()-k));
                                break; } } }
                    for (Tokens token : QueueTokens){
                        if(token.getName().equals(name)){
                            if (token.getNumber() >= tempNumber) {
                                token.setNumber(token.getNumber()-number);
                                token2 = (Tokens)token.clone();
                                QueueTokens.enqueue(token2);
                                token.setNumber(0);
                                break; }
                            else{ tempNumber -= token.getNumber(); token.setNumber(0); } } } } } }
//***************************************
// writing the output.txt file
        FileWriter writer = new FileWriter(args[4]);
//****************************************
// Reversing the token box for suiting output format.
        Stack<Tokens> b = new Stack<>();
        while(!QueueTokens.isEmpty()){ b.push(QueueTokens.Dequeue()); }
        while(!b.isEmpty()){ QueueTokens.enqueue(b.pop()); }
//*****************************************
// Giving the last queue of tokenbox
        Tokens.clear();
        for (Tokens token : QueueTokens ){ Tokens.add(token); }
        Tokens.sort(new Sorter());
        Queue<Tokens> QueueTokensFinal = new Queue<>();
        for (Tokens token : Tokens){ QueueTokensFinal.enqueue(token); }
//******************************************
//Calling methods for creating output.txt
        Collections.reverse(Items);
        PrintingBoard(Items,PartStacks,writer);
        PrintingTokenBox(QueueTokensFinal,writer);
        writer.close(); }}
