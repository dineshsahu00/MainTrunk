package edrix.main.commons;

import java.io.Serializable;
import java.util.Date;

public class Scores implements Serializable{

	/**
	 * Author Dinesh
	 */
	private static final long serialVersionUID = -6063797986532526440L;
	private Date recordDate;
	private float literature;
	private float maths;
	private float technotronics;
	private float bioscience;
	private float computes;
	private float athletics;
	private float artistic;
	private float behaviour;
	private float leadership;
	private float management;
	private float ethics;
	
	
	
	public Scores(Date recordDate, float literature, float maths, float technotronics, float bioscience,
			float computes, float athletics, float artistic, float behaviour, float leadership, float management,
			float ethics) {
		super();
		this.recordDate = recordDate;
		this.literature = literature;
		this.maths = maths;
		this.technotronics = technotronics;
		this.bioscience = bioscience;
		this.computes = computes;
		this.athletics = athletics;
		this.artistic = artistic;
		this.behaviour = behaviour;
		this.leadership = leadership;
		this.management = management;
		this.ethics = ethics;
	}
	
	public Date getRecordDate() {
		return recordDate;
	}
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	public float getLiterature() {
		return literature;
	}
	public void setLiterature(float literature) {
		this.literature = literature;
	}
	public float getMaths() {
		return maths;
	}
	public void setMaths(float maths) {
		this.maths = maths;
	}
	public float getTechnotronics() {
		return technotronics;
	}
	public void setTechnotronics(float technotronics) {
		this.technotronics = technotronics;
	}
	public float getBioscience() {
		return bioscience;
	}
	public void setBioscience(float bioscience) {
		this.bioscience = bioscience;
	}
	public float getComputes() {
		return computes;
	}
	public void setComputes(float computes) {
		this.computes = computes;
	}
	public float getAthletics() {
		return athletics;
	}
	public void setAthletics(float athletics) {
		this.athletics = athletics;
	}
	public float getArtistic() {
		return artistic;
	}
	public void setArtistic(float artistic) {
		this.artistic = artistic;
	}
	public float getBehaviour() {
		return behaviour;
	}
	public void setBehaviour(float behaviour) {
		this.behaviour = behaviour;
	}
	public float getLeadership() {
		return leadership;
	}
	public void setLeadership(float leadership) {
		this.leadership = leadership;
	}
	public float getManagement() {
		return management;
	}
	public void setManagement(float management) {
		this.management = management;
	}
	public float getEthics() {
		return ethics;
	}
	public void setEthics(float ethics) {
		this.ethics = ethics;
	}
	
}
