package com.misyn.smartintranet.entity;



import javax.persistence.*;

/**
 * Created by Heshi on 3/10/17.
 */
@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int typeID;
    @Enumerated(EnumType.STRING)
    private TypeName typeName;

    private int maxShortLeaves;
    private int maxAnnualLeaves;
    private int maxCasualLeaves;
    private int maxSickLeaves;
    
    private String description;

    public enum TypeName{
    	PERMANENT,PROBATION, TEMPORARY,CONTRACT,OTHER}


    

    

    public TypeName getTypeName() {
		return typeName;
	}

	public void setTypeName(TypeName typeName) {
		this.typeName = typeName;
	}

	@Override
    public boolean equals(Object obj) {
        return ((Type)obj).typeID == this.typeID;
    }

    public int getMaxShortLeaves() {
        return maxShortLeaves;
    }

    public void setMaxShortLeaves(int maxShortLeaves) {
        this.maxShortLeaves = maxShortLeaves;
    }

    public int getMaxAnnualLeaves() {
        return maxAnnualLeaves;
    }

    public void setMaxAnnualLeaves(int maxAnnualLeaves) {
        this.maxAnnualLeaves = maxAnnualLeaves;
    }

    public int getMaxCasualLeaves() {
        return maxCasualLeaves;
    }

    public void setMaxCasualLeaves(int maxCasualLeaves) {
        this.maxCasualLeaves = maxCasualLeaves;
    }

    public int getMaxSickLeaves() {
        return maxSickLeaves;
    }

    public void setMaxSickLeaves(int maxSickLeaves) {
        this.maxSickLeaves = maxSickLeaves;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
}
