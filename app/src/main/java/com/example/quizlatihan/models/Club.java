package com.example.quizlatihan.models;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

public class Club implements Parcelable {
    private String clubDescription;
    //    private String playerName;
    private Boolean clubStatus;
    private int clubImage;

    public Club(String clubDescription, Boolean clubStatus, Integer clubImage){
        this.clubDescription = clubDescription;
//        this.playerName = playerName;
        this.clubStatus = clubStatus;
        this.clubImage = clubImage;
    }

    protected Club(Parcel in) {
        clubDescription = in.readString();
//        playerName = in.readString();
        clubImage = in.readInt();
        byte tmpClubStatus = in.readByte();
        clubStatus = tmpClubStatus == 0 ? null : tmpClubStatus == 1;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(clubDescription);
//        dest.writeString(playerName);
        dest.writeInt(clubImage);
        dest.writeByte((byte) (clubStatus == null ? 0 : clubStatus ? 1 : 2));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Club> CREATOR = new Creator<Club>() {
        @Override
        public Club createFromParcel(Parcel in) {
            return new Club(in);
        }

        @Override
        public Club[] newArray(int size) {
            return new Club[size];
        }
    };

    public String getClubDescription() {
        return clubDescription;
    }

    public void setClubDescription(String clubDescription) {
        this.clubDescription = clubDescription;
    }

//    public String getPlayerName() {
//        return playerName;
//    }
//
//    public void setPlayerName(String playerName) {
//        this.playerName = playerName;
//    }

    public int getClubImage() {
        return clubImage;
    }

    public void setClubImage(int clubImage) {
        this.clubImage = clubImage;
    }

    public Boolean getClubStatus() {
        return clubStatus;
    }

    public void setClubStatus(Boolean clubStatus) {
        this.clubStatus = clubStatus;
    }

    @BindingAdapter({"android:src"})
    public static void setImageDrawable(ImageView view, Drawable drawable) {
        view.setImageDrawable(drawable);
    }

}
