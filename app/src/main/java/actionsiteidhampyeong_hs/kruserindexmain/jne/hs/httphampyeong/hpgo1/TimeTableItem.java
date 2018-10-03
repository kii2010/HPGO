package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

public class TimeTableItem {
    private String mNum, mSub;

    public TimeTableItem(String num, String sub) {
        mNum = num;
        mSub = sub;
    }

    public String getTimetableNum() {
        return mNum;
    }

    public String getTimetableSub() {
        return mSub;
    }
}
