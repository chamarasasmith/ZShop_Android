package zenonideas.zshop_android;

public class C_Brand {
    private String bid;
    private String bname;
    private String img1;
    private String st;

    public C_Brand() {
    }

    public C_Brand(String bid, String bname, String img1, String st) {
        this.bid = bid;
        this.bname = bname;
        this.img1 = img1;
        this.st = st;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
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
