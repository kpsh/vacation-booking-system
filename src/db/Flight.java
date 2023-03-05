package db;


public class Flight {
    String fid, ffrom, fto, fdate, fclass, fseats;

    public Flight(String fid, String ffrom, String fto, String fdate, String fclass, String fseats) {
        this.fid = fid;
        this.ffrom = ffrom;
        this.fto = fto;
        this.fdate = fdate;
        this.fclass = fclass;
        this.fseats = fseats;
    }

    public String getFid() {
        return fid;
    }

    public void setFid(String fid) {
        this.fid = fid;
    }

    public String getFfrom() {
        return ffrom;
    }

    public void setFfrom(String ffrom) {
        this.ffrom = ffrom;
    }

    public String getFto() {
        return fto;
    }

    public void setFto(String fto) {
        this.fto = fto;
    }

    public String getFdate() {
        return fdate;
    }

    public void setFdate(String fdate) {
        this.fdate = fdate;
    }

    public String getFclass() {
        return fclass;
    }

    public void setFclass(String fclass) {
        this.fclass = fclass;
    }

    public String getFseats() {
        return fseats;
    }

    public void setFseats(String fseats) {
        this.fseats = fseats;
    }
    
    
}
