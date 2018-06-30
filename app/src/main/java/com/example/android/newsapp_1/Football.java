package com.example.android.newsapp_1;

/**
 * Created by djalél on 30/06/2018.
 */

/**
 * An {@link Football} object contains information related to a single news.
 */
public class Football {
    /** title of the football news */
    private String mTitle;
    /** date of the football news */
    private String mDate;
    /** url of the football news */
    private String mUrl;
    /** autor of the football news */
    private String mAuthor;
    /** section of the football news */
    private String mSection;
    /**
     * Constructs a new {@link Football} object.
     *
     * @param title is the title  of the  news
     * @param date is the date when the news happened
     * @param url is the website URL to find more details about the news
     * @param author is the website URL to find more details about the earthquake
     * @param section is the section og the news
     */
    public Football(String title, String date, String url, String author, String section) {
        mTitle = title;
        mDate = date;
        mUrl = url;
        mAuthor = author;
        mSection = section;
    }
    /**
     * Returns the title of the news.
     */
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }
    /**
     * Returns the date of the news.
     */
    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
    /**
     * Returns the url of the news.
     */
    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        mUrl = url;
    }
    /**
     * Returns the autor of the news.
     */
    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        mAuthor = author;
    }
    /**
     * Returns the section of the news.
     */
    public String getSection() {
        return mSection;
    }

    public void setSection(String section) {
        mSection = section;
    }

    @Override
    public String toString() {
        return  " title "    + getTitle();

    }
}