package zenonideas.zshop_android;

public class C_User {
    private String lid;
    private String un;
    private String pw;
    private String fn;
    private String ln;
    private String rid;
    private String img1;
    private String st;

    public C_User() {
    }

    public C_User(String lid, String un, String pw, String fn, String ln, String rid, String img1, String st) {
        this.lid = lid;
        this.un = un;
        this.pw = pw;
        this.fn = fn;
        this.ln = ln;
        this.rid = rid;
        this.img1 = img1;
        this.st = st;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getUn() {
        return un;
    }

    public void setUn(String un) {
        this.un = un;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }
}
