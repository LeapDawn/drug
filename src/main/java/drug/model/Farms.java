package drug.model;

import java.sql.Timestamp;

public class Farms {
    private String farmname;

    private String farmaddress;

    private String farmphone;

    private String farmlinkman;

    private String farmraiseway;

    private String farmraisescope;

    private String farmraisetype;

    private String farmbuilddate;

    private String farmremarks;

    private String farmprovince;

    private Timestamp addtime;

    private String adduser;

    public String getFarmname() {
        return farmname;
    }

    public void setFarmname(String farmname) {
        this.farmname = farmname == null ? null : farmname.trim();
    }

    public String getFarmaddress() {
        return farmaddress;
    }

    public void setFarmaddress(String farmaddress) {
        this.farmaddress = farmaddress == null ? null : farmaddress.trim();
    }

    public String getFarmphone() {
        return farmphone;
    }

    public void setFarmphone(String farmphone) {
        this.farmphone = farmphone == null ? null : farmphone.trim();
    }

    public String getFarmlinkman() {
        return farmlinkman;
    }

    public void setFarmlinkman(String farmlinkman) {
        this.farmlinkman = farmlinkman == null ? null : farmlinkman.trim();
    }

    public String getFarmraiseway() {
        return farmraiseway;
    }

    public void setFarmraiseway(String farmraiseway) {
        this.farmraiseway = farmraiseway == null ? null : farmraiseway.trim();
    }

    public String getFarmraisescope() {
        return farmraisescope;
    }

    public void setFarmraisescope(String farmraisescope) {
        this.farmraisescope = farmraisescope == null ? null : farmraisescope.trim();
    }

    public String getFarmraisetype() {
        return farmraisetype;
    }

    public void setFarmraisetype(String farmraisetype) {
        this.farmraisetype = farmraisetype == null ? null : farmraisetype.trim();
    }

    public String getFarmbuilddate() {
        return farmbuilddate;
    }

    public void setFarmbuilddate(String farmbuilddate) {
        this.farmbuilddate = farmbuilddate == null ? null : farmbuilddate.trim();
    }

    public String getFarmremarks() {
        return farmremarks;
    }

    public void setFarmremarks(String farmremarks) {
        this.farmremarks = farmremarks == null ? null : farmremarks.trim();
    }

    public String getFarmprovince() {
        return farmprovince;
    }

    public void setFarmprovince(String farmprovince) {
        this.farmprovince = farmprovince == null ? null : farmprovince.trim();
    }

    public Timestamp getAddtime() {
        return addtime;
    }

    public void setAddtime(Timestamp addtime) {
        this.addtime = addtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }

	@Override
	public String toString() {
		return "Farms [farmname=" + farmname + ", farmaddress=" + farmaddress
				+ ", farmphone=" + farmphone + ", farmlinkman=" + farmlinkman
				+ ", farmraiseway=" + farmraiseway + ", farmraisescope="
				+ farmraisescope + ", farmraisetype=" + farmraisetype
				+ ", farmbuilddate=" + farmbuilddate + ", farmremarks="
				+ farmremarks + ", farmprovince=" + farmprovince + ", addtime="
				+ addtime + ", adduser=" + adduser + "]";
	}
    
    
}