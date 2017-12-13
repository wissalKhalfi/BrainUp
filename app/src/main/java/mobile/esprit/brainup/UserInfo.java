package mobile.esprit.brainup;

/**
 * Created by macbookpro on 10/12/2016.
 */

public class UserInfo {

    public String NamePlayer;
    public int AgePlayer;
    public  String CountryPlayer;

    public UserInfo() {
    }

    public UserInfo(String Name, int age, String country) {
        NamePlayer = Name;
        AgePlayer = age;
        CountryPlayer = country;
    }

    public String DisplayNamePlayer() {
        return NamePlayer;
    }
    public int DisplayAgePlayer() {
        return AgePlayer;
    }
    public String DisplayCountryPlayer() {
        return CountryPlayer;
    }
    /*public String getNamePlayer() {
        return NamePlayer;
    }

    public int getAgePlayer() {
        return AgePlayer;
    }

    public String getCountryPlayer() {
        return CountryPlayer;
    }*/

    @Override
    public String toString() {
        return "UserInfo{" +
                "NamePlayer='" + NamePlayer + '\'' +
                ", AgePlayer=" + AgePlayer +
                ", CountryPlayer='" + CountryPlayer + '\'' +
                '}';
    }
}
