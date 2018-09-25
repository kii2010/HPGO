package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

public class Notiltem {
    private String mTitle, mDate, mWriter;

    public Notiltem(String title, String date, String writer) {
        mTitle = title;
        mDate = date;
        mWriter = writer;
    }
    public String getNoticeTitle() {
        return mTitle;
    }

    public String getNoticeDate() {
        return mDate;
    }

    public String getNoticeWriter() {
        return mWriter;
    }
}
