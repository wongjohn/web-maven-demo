package com.aly.domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 人事招聘。
 */
@XmlRootElement
public class Recruits {
	/**唯一主键*/
	private Integer recruitId;
	/**岗位名称*/
	private String recruitPosition;
	/**工作地点*/
	private String workPlace;
	/**工作性质*/
	private String workProperty;
	/**招聘人数*/
	private String recruitNum;
    /**岗位责任*/
    private String responsibilities;
    /**任职资格*/
    private String requirements;
    /**其他*/
    private String others;

    public Integer getRecruitId() {
		return recruitId;
	}

    public void setRecruitId(Integer recruitId) {
        this.recruitId = recruitId;
	}

    public String getRecruitPosition() {
        return recruitPosition;
	}

    public void setRecruitPosition(String recruitPosition) {
        this.recruitPosition = recruitPosition;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getWorkProperty() {
		return workProperty;
	}

	public void setWorkProperty(String workProperty) {
		this.workProperty = workProperty;
	}

	public String getRecruitNum() {
		return recruitNum;
	}

	public void setRecruitNum(String recruitNum) {
		this.recruitNum = recruitNum;
	}

    public String getResponsibilities() {
        return responsibilities;
    }

    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }
}
