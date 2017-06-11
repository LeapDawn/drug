package drug.model;

import java.util.Date;

public class GenView {
    private Integer id;

    private String strainno;

    private String genname;

    private String genalias;

    private String genremarks;

    private String sampleno;

    private Date sampledate;

    private Integer animalno;

    private String animalname;

    private String sampleProvince;

    private String genotyping1;

    private String genotyping2;

    private String genotyping3;

    private String sampleSource;
    
    private String farmName;
    
    public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public String getSampleSource() {
		return sampleSource;
	}

	public void setSampleSource(String sampleSource) {
		this.sampleSource = sampleSource;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrainno() {
        return strainno;
    }

    public void setStrainno(String strainno) {
        this.strainno = strainno == null ? null : strainno.trim();
    }

    public String getGenname() {
        return genname;
    }

    public void setGenname(String genname) {
        this.genname = genname == null ? null : genname.trim();
    }

    public String getGenalias() {
        return genalias;
    }

    public void setGenalias(String genalias) {
        this.genalias = genalias == null ? null : genalias.trim();
    }

    public String getGenremarks() {
        return genremarks;
    }

    public void setGenremarks(String genremarks) {
        this.genremarks = genremarks == null ? null : genremarks.trim();
    }

    public String getSampleno() {
        return sampleno;
    }

    public void setSampleno(String sampleno) {
        this.sampleno = sampleno == null ? null : sampleno.trim();
    }

    public Date getSampledate() {
        return sampledate;
    }

    public void setSampledate(Date sampledate) {
        this.sampledate = sampledate;
    }

    public Integer getAnimalno() {
        return animalno;
    }

    public void setAnimalno(Integer animalno) {
        this.animalno = animalno;
    }

    public String getAnimalname() {
        return animalname;
    }

    public void setAnimalname(String animalname) {
        this.animalname = animalname == null ? null : animalname.trim();
    }

    public String getSampleProvince() {
        return sampleProvince;
    }

    public void setSampleProvince(String farmprovince) {
        this.sampleProvince = farmprovince == null ? null : farmprovince.trim();
    }

    public String getGenotyping1() {
        return genotyping1;
    }

    public void setGenotyping1(String genotyping1) {
        this.genotyping1 = genotyping1 == null ? null : genotyping1.trim();
    }

    public String getGenotyping2() {
        return genotyping2;
    }

    public void setGenotyping2(String genotyping2) {
        this.genotyping2 = genotyping2 == null ? null : genotyping2.trim();
    }

    public String getGenotyping3() {
        return genotyping3;
    }

    public void setGenotyping3(String genotyping3) {
        this.genotyping3 = genotyping3 == null ? null : genotyping3.trim();
    }
}