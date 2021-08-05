package sg.edu.rp.c346.id20039529.singaporesisland;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Island implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int yearReleased;
    private int stars;

    public Island(int id, String title, String singers, int yearReleased, int stars) {
        this.id = id;
        this.title = title;
        this.singers = singers;
        this.yearReleased = yearReleased;
        this.stars = stars;

    }

    public int getId() {
        return id;
    }

    public Island setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Island setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSingers() {
        return singers;
    }

    public Island setSingers(String singers) {
        this.singers = singers;
        return this;
    }

    public int getYearReleased() {
        return yearReleased;
    }

    public Island setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public int getStars() {
        return stars;
    }

    public Island setStars(int stars) {
        this.stars = stars;
        return this;
    }

    @NonNull
    @Override
    public String toString() {
        String starsString = "";
        for(int i = 0; i < stars; i++){
            starsString += "*";
        }
        return title + "\n" + singers + " - " + yearReleased + "\n" + starsString;

    }
}
