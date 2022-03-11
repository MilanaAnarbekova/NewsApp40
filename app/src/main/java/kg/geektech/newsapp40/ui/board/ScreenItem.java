package kg.geektech.newsapp40.ui.board;

import com.airbnb.lottie.LottieAnimationView;

import java.io.Serializable;
import java.util.ArrayList;

public class ScreenItem  {

    LottieAnimationView lottie;

    private int image;
    private String title;
    private String textDes;

    public ScreenItem(int image, String title, String textDes) {
        this.image = image;
        this.title = title;
        this.textDes = textDes;
    }

    public ScreenItem(ArrayList<ScreenItem> screenList, BoardFragment boardFragment) {
        this.image = image;
        this.title = title;
        this.textDes = textDes;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTextDes() {
        return textDes;
    }

    public void setTextDes(String textDes) {
        this.textDes = textDes;
    }


}
