package mobile.esprit.brainup.IQtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by macbookpro on 18/11/2016.
 */

public class QuizHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
            // Database Name
    private static final String DATABASE_NAME = "BrainUpIQ";
            // tasks table name
    private static final String TABLE_QUEST = "quest";
            // tasks Table Columns names
    private static final String KEY_ID = "qid";
    private static final String KEY_QUES = "question";
    private static final String KEY_ANSWER = "answer"; // correct option
    private static final String KEY_OPTA = "opta"; // option a
    private static final String KEY_OPTB = "optb"; // option b
    private static final String KEY_OPTC = "optc"; // option c
    private SQLiteDatabase dbase;

    public QuizHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        dbase = db;
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
                + " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
                + KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
        db.execSQL(sql);
        addQuestion();
    }

    private void addQuestion() {
        Question q1 = new Question("What is Shakespeare famous for ?", "His plays", "His operas", "His paintings", "His plays");
        this.addQuestion(q1);
        Question q2 = new Question("What is Barack Obama's middle name ?", "Oussama", "Mubarak", "Hussein", "Hussein");
        this.addQuestion(q2);
        Question q3 = new Question("Which of these names isn't the name of a US city ?", "Glasgow", "New York", "Texas", "Glasgow");
        this.addQuestion(q3);
        Question q4 = new Question("Portuguese is spoken in ______", "Argentina", "Brazil", "Ecuador", "Brazil");
        this.addQuestion(q4);
        Question q5 = new Question("What is the capital of Peru?", "San Juan", "Bogota", "Lima", "Lima");
        this.addQuestion(q5);
        Question q6 = new Question("In which city did the 2008 Olympic Games take place ?", "Beijing", "London", "Rio de Janeiro", "Beijing");
        this.addQuestion(q6);
        Question q7 = new Question("Entomology is the science that studies", "Insects", "Behavior of human beings", "The formation of rocks", "Insects");
        this.addQuestion(q7);
        Question q8 = new Question("Eritrea, which became the 182nd member of the UN in 1993, is in the continent of", "Africa", "Europe", "Australia", "Africa");
        this.addQuestion(q8);
        Question q9 = new Question("AZBYDWGT?? Which 2 letters come next?", "JG", "KP", "FU", "KP");
        this.addQuestion(q9);
        Question q10 = new Question("Which number is the odd one out? \n 84129, 32418, 47632, 36119, 67626, 72927", "47632", "32418", "36119", "47632");
        this.addQuestion(q10);
        Question q11 = new Question("Which word is closest in meaning to the word 'FLIPPANT' ", "irreverent", "candid", "feeble", "irreverent");
        this.addQuestion(q11);
        Question q12 = new Question(" Which word below is out of sequence?\n" +
                "manage, aplomb, fedora, manual, jumble, junior, author ", "fedora", "author", "jumble", "fedora");
        this.addQuestion(q12);
        Question q13 = new Question("What number is, logically, missing from the sequence below? 348269, 284315, *****, 8438, 4811, 842, 86", "7307", "34826", "34782", "34826");
        this.addQuestion(q13);
        Question q14 = new Question("Tunisia is in ______", "Africa", "Europe", "Asia", "Africa");
        this.addQuestion(q14);
        Question q15 = new Question("In which decade was the American Institute of Electrical Engineers (AIEE) founded?", "1850s", "1880s", "1930s", "1880s");
        this.addQuestion(q15);
        Question q16 = new Question("What is part of a database that holds only one type of information?", "File", "Record", "Field", "Field");
        this.addQuestion(q16);
        Question q17 = new Question("'OS' computer abbreviation usually means ?", "Open software", "Operating system", "Optical sensor", "Operating system");
        this.addQuestion(q17);
        Question q18 = new Question("Which scientist discovered the radioactive element radium?", "Marie Curie", "Nelson Mandella", "Albert Einstein", "Marie Curie");
        this.addQuestion(q18);
        Question q19 = new Question("In which decade was the telephone invented?", "2000s", "1970s", "1870s", "1870s");
        this.addQuestion(q19);
        Question q20 = new Question("What Galileo invented?", "Thermometer", "Barometer", "Microscope", "Thermometer");
        this.addQuestion(q20);
        Question q21 = new Question("Which word means the same as ANCHORITE ", "recluse", "trammel", "feeble", "recluse");
        this.addQuestion(q21);
        Question q22 = new Question("What is the value of x?\n" +
                " 64 – 12 × 2 + 6 ÷ 3 = x ", "67", "42", "20", "42");
        this.addQuestion(q22);
        Question q23 = new Question("What is the name given to a group of ROOKS?\n" , "murmuration", "park", "building", "building");
        this.addQuestion(q23);
        Question q24 = new Question(" Which word is opposite in meaning to the word: ?\n" +
                " SIGNIFICANT", "Stupid", "Modest", "Petty", "Modest");
        this.addQuestion(q24);
        Question q25 = new Question("Which two numbers come next in this sequence?\n" +
                " 38, 24, 62, 12, 74, ? ", "28,102", "20,108", "100, 210", "28,102");
        this.addQuestion(q25);
        Question q26 = new Question("Which word means the same as NEOPHYTE?\n" , "scallywag ", "gimrack", "novice", "novice");
        this.addQuestion(q26);
        Question q27 = new Question("What is the name given to a group of HERRINGS?\n" , "glean", "sute", "sedge", "glean");
        this.addQuestion(q27);
        Question q28 = new Question("Which two of these words are closest in meaning?\n" +
                "GLUT, SUPPLICATION, AID, CACHE, GUIDANCE, PLEA", "GUIDANCE, PLEA ", "CACHE, GUIDANCE", "Supplication,plea", "Supplication,plea");
        this.addQuestion(q28);
        Question q29 = new Question("What is a GOOGOL?\n" , "a mathematical term", "a folk dance", "not a gypsy", "a mathematical term");
        this.addQuestion(q29);
        Question q30 = new Question("586321 is to 268\n" +
                "as 94783219647 is to ?" , "42468", "46284", "42648", "46284");
        this.addQuestion(q30);
        Question q31 = new Question("Which word means the same as the word: INDISCRETION" , "Folly", "Crudity", "Sloth", "Folly");
        this.addQuestion(q31);
        Question q32 = new Question("What do the following words have in common?\n" +
                " PRECIOUS, CIRCLE, TONE, AUTOMATIC" , "They can all be prefixed with anti", "They can all be prefixed with semi", "Nothing in common", "They can all be prefixed with semi");
        this.addQuestion(q32);
        Question q33 = new Question("Which is the odd one out?\n" +
                "SALIFEROUS, EVACUATION, REGULATION, EXHAUSTION, INOCULATED, DUODECIMAL" , "INOCULATED", "REGULATION", "Evacuation", "Evacuation");
        this.addQuestion(q33);
        Question q34 = new Question("I strode to Dorset,ate milk and rice in Limerick \n and bought Edna a beer in Aberdeen.\n Whom did I meet in Antrim?" , "Mirtan", "Martin", "Mitran", "Martin");
        this.addQuestion(q34);
        Question q35 = new Question("What is the name given to a group of HORSES?" , "Haras", "Husk", "Mute", "Haras");
        this.addQuestion(q35);
        Question q36 = new Question("What is an ORRERY?" , "a Florentine iris", "A clockwork model", "a dungeon", "A clockwork model");
        this.addQuestion(q36);
        Question q37 = new Question("Which two words mean the same?" , "LAMPOON, DEFAME", "MEDIATE, DEFAME", "ADVOCATE, LAMPOON", "LAMPOON, DEFAME");
        this.addQuestion(q37);
        Question q38 = new Question("What do these words have in common?\n" +
                "ABUNDANCE, ALLEVIATE, UNTRUTHS, PROCAINE, CHAMBER" , "They all contain Biblical characters", "Nothing", "They all contain trilical characters", "They all contain Biblical characters");
        this.addQuestion(q38);
        Question q39 = new Question("What number continues this sequence? \n " +
                "987, 251, 369, 872, 513, ?" , "721", "628", "698", "698");
        this.addQuestion(q39);
        Question q40 = new Question("What comes next in this sequence?  \n" +
                " 7, 8, 9, 10, 12, 14, 16, 20, 21, 28, ?" , "72", "27", "34", "27");
        this.addQuestion(q40);

    }

    public void addQuestion(Question quest) {
        // SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_QUES, quest.getQUESTION());
        values.put(KEY_ANSWER, quest.getAnswer());
        values.put(KEY_OPTA, quest.getOptA());
        values.put(KEY_OPTB, quest.getOptB());
        values.put(KEY_OPTC, quest.getOptC());
        // Inserting Row
        dbase.insert(TABLE_QUEST, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
        // Create tables again
        onCreate(db);

    }
    public List<Question> getAllQuestions() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public List<Question> getAllQuestionsLevel2() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST+" WHERE "+KEY_ID  +" BETWEEN 11 AND 20 ";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public List<Question> getAllQuestionsLevel3() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST+" WHERE "+KEY_ID  +" BETWEEN 21 AND 30 ";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }

    public List<Question> getAllQuestionsLevel4() {
        List<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_QUEST+" WHERE "+KEY_ID  +" BETWEEN 31 AND 40 ";
        dbase = this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setAnswer(cursor.getString(2));
                quest.setOptA(cursor.getString(3));
                quest.setOptB(cursor.getString(4));
                quest.setOptC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
}
