package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

public class NotiItem {
    private String mTitle, mDate, mWriter, mInfo;

    public NotiItem(String title, String date, String writer, String info) {
        mTitle = title;
        mDate = date;
        mWriter = writer;
        mInfo = info;
    }

    public String getNoticeTitle() {
        return mTitle;
    }

    public String getNoticeInfo() {
        return mInfo;
    }


    public String getNoticeDate() {
        return mDate;
    }

    public String getNoticeWriter() {
        return mWriter;
    }

}

