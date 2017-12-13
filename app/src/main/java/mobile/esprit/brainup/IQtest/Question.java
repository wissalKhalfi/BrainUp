package mobile.esprit.brainup.IQtest;

/**
 * Created by macbookpro on 18/11/2016.
 */

public class Question {
    private int ID;
    private String QUESTION;
    private String optA;
    private String optB;
    private String optC;
    private String answer;

    public Question() {
        ID = 0;
        QUESTION = "";
        optA = "";
        optB = "";
        optC = "";
        answer = "";
    }

    public Question(String qUESTION, String oPTA, String oPTB, String oPTC, String aNSWER) {
        this.QUESTION = qUESTION;
        this.optA = oPTA;
        this.optB = oPTB;
        this.optC = oPTC;
        this.answer = aNSWER;
    }


    public int getID() {
        return ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public String getOptA() {
        return optA;
    }

    public String getOptB() {
        return optB;
    }

    public String getOptC() {
        return optC;
    }

    public String getAnswer() {
        return answer;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public void setOptA(String optA) {
        this.optA = optA;
    }

    public void setOptB(String optB) {
        this.optB = optB;
    }

    public void setOptC(String optC) {
        this.optC = optC;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
