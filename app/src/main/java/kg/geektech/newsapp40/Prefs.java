package kg.geektech.newsapp40;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {

    private SharedPreferences preferences;

    public Prefs (Context context){
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);

    }
    public void saveBoardState(){
        preferences.edit().putBoolean("boardState", true).apply();
    }

    public boolean isBoardShow(){
        return preferences.getBoolean("boardState",false);
    }
    public  void saveNameState(String name){
        preferences.edit().putString("name",name).apply();
    }

    public String getNameSate(){
        return preferences.getString("name"," ");
    }

    public void DeleteAll(){
        preferences.edit().clear().apply();
    }

    public void saveImage(Uri uri){
        preferences.edit().putString("photo" , uri.toString()).apply();
    }
    public  String getImage(){
       return preferences.getString("photo" , "https://i.pinimg.com/736x/b0/e3/d2/b0e3d277a215cf3a33303f4f8f26009f.jpg");
    }
}
