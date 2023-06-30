package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.service.autofill.UserData;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MyDbHandler extends SQLiteOpenHelper {
    String title = "MyDbHandler";
    public static int DATA_VERSION = 1;
    ArrayList<User> userList = new ArrayList<>();
    public static final String DATABASE_NAME = "UserDb.db";

    public String TABLE_USERS= "users";
    public String COLUMN_USERNAME = "name";
    public String COLUMN_DESCRIPTION = "description";
    public String COLUMN_ID = "id";
    public String COLUMN_FOLLOWED = "followed";


    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ACCOUNT_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + COLUMN_USERNAME + " TEXT, " + COLUMN_DESCRIPTION + " TEXT," + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_FOLLOWED + " INTEGER) ";
        Log.i(title, CREATE_ACCOUNT_TABLE);

        db.execSQL(CREATE_ACCOUNT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);

    }

    public void addUsers(User user) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, user.getName());
        values.put(COLUMN_DESCRIPTION, user.getDescription());
        values.put(COLUMN_ID, user.getId());
        values.put(COLUMN_FOLLOWED, user.getFollowed());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_USERS, null, values);
        db.close();



    }

    public ArrayList<User> getUser(){
          ArrayList<User>userList= new ArrayList<>();
          SQLiteDatabase db=getReadableDatabase();
          String query = "SELECT * FROM " + TABLE_USERS;
          Cursor cursor =db.rawQuery(query,null);

         if(cursor.moveToFirst())
         {

             Log.i(title,"If cursor is not null");
             while(cursor.moveToNext())
             {
                  String name =cursor.getString(1);
                  String desc =cursor.getString(2);
                  int id =cursor.getInt(3);
                  boolean followed = (cursor.getInt(4)!=0);
                  User user = new User(name,desc,id,followed);
                  Log.i(title,"on success add user"+user);
                 userList.add(user);
                 Log.i(title,"Checking user List"+userList);
             }
         }
          cursor.close();
          db.close();
          return userList;
    }
    /*public ArrayList<User> getUSERS() {
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TABLE_USERS, null);
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(cursor.getColumnIndex(COLUMN_USERNAME));
                String des = cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION));
                int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                boolean fo = cursor.getInt(cursor.getColumnIndex(COLUMN_FOLLOWED)) == 1;
                User user = new User(name, des, id, fo);
                userList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return userList;


    }*/


    public boolean updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Username", user.getName());
        values.put("Description",user.getDescription());
        values.put("Id",user.getId());
        values.put("followed",user.getFollowed());

        int rows =db.update("users",values,"Username=?",new String[]{String.valueOf(user.getName())});
        if(rows==1)
        {
            return false;
        }
        return true;



    }


    public void CreateUser() {

        int i = 0;
        Random random = new Random();
        for (i = 0; i <= 20; i++) {
            User user = new User("Name" + generateNO(), "Description" + generateNO(), generateNO(), random.nextBoolean());
            addUsers(user);
        }


    }


    private int generateNO(){
        Random random= new Random();
        int randNum =random.nextInt(99999999);
        return randNum;

    }


}
