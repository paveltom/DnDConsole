package BusinessLayer.BoardPackage;

public class StatPair {
    private String statName;
    public String Name(){return this.statName;}
    private Object statValue;
    public String Value(){return this.statValue.toString();}

    public StatPair(String statName, Object statValue){
        this.statName = statName;
        this.statValue = statValue;
    }
}
