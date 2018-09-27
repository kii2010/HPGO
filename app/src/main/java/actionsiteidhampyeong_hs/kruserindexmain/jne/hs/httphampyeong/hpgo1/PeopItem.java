package actionsiteidhampyeong_hs.kruserindexmain.jne.hs.httphampyeong.hpgo1;

public class PeopItem {
    private String mname, msubject, mtast, mgrade;

    public PeopItem(String name, String subject, String tast, String grade) {
        mname = name;
        msubject = subject;
        mtast = tast;
        mgrade = grade;
    }

    public String getPeopname() {
        return mname;
    }

    public String getPeopsubject() {
        return msubject;
    }

    public String getPeoptast() {
        return mtast;
    }
    public String getPeopgrade() {
        return mgrade;
    }
}
