package drug.model;

public class StandardMicKey {
    private String medicalname;

    private String standardname;

    private String strainname;

    public String getMedicalname() {
        return medicalname;
    }

    public void setMedicalname(String medicalname) {
        this.medicalname = medicalname == null ? null : medicalname.trim();
    }

    public String getStandardname() {
        return standardname;
    }

    public void setStandardname(String standardname) {
        this.standardname = standardname == null ? null : standardname.trim();
    }

    public String getStrainname() {
        return strainname;
    }

    public void setStrainname(String strainname) {
        this.strainname = strainname == null ? null : strainname.trim();
    }

	@Override
	public String toString() {
		return "StandardMicKey [medicalname=" + medicalname + ", standardname="
				+ standardname + ", strainname=" + strainname + "]";
	}
    
    
}