package drug.model;

public class DetailAnimal {
    private Integer animalno;

    private String animalname;

    private String animalremarks;

    private String others1;

    private String others2;

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

    public String getAnimalremarks() {
        return animalremarks;
    }

    public void setAnimalremarks(String animalremarks) {
        this.animalremarks = animalremarks == null ? null : animalremarks.trim();
    }

    public String getOthers1() {
        return others1;
    }

    public void setOthers1(String others1) {
        this.others1 = others1 == null ? null : others1.trim();
    }

    public String getOthers2() {
        return others2;
    }

    public void setOthers2(String others2) {
        this.others2 = others2 == null ? null : others2.trim();
    }

	@Override
	public String toString() {
		return "DetailAnimal [animalno=" + animalno + ", animalname="
				+ animalname + ", animalremarks=" + animalremarks
				+ ", others1=" + others1 + ", others2=" + others2 + "]";
	}
}