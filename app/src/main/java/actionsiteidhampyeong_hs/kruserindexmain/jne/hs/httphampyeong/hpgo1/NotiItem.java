package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

public class NotiItem {
    private String mTitle, mDate, mWriter;

    public NotiItem(String title, String date, String writer) {
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
