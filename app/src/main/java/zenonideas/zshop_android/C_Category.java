package zenonideas.zshop_android;

public class C_Category {
    private String cid;
    private String cname;
    private String img1;
    private String st;

    public C_Category() {
    }

    public C_Category(String cid, String cname, String img1, String st) {
        this.cid = cid;
        this.cname = cname;
        this.img1 = img1;
        this.st = st;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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
