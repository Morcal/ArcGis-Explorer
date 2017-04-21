package com.lyqdhgo.environment.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by QiDeHong on 2017/4/21.
 */

public class Task implements Parcelable {
    private String title;
    private String content;

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
    }

    protected Task(Parcel in) {
        title = in.readString();
        content = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeString(title);
        dest.writeString(content);
    }
}
