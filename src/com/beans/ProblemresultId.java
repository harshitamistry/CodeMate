package com.beans;
// Generated 5-Nov-2016 11:07:12 PM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProblemresultId generated by hbm2java
 */
@Embeddable
public class ProblemresultId implements java.io.Serializable {

	private int problemId;
	private String result;
	private boolean visibility;

	public ProblemresultId() {
	}

	public ProblemresultId(int problemId, String result, boolean visibility) {
		this.problemId = problemId;
		this.result = result;
		this.visibility = visibility;
	}

	@Column(name = "ProblemID", nullable = false)
	public int getProblemId() {
		return this.problemId;
	}

	public void setProblemId(int problemId) {
		this.problemId = problemId;
	}

	@Column(name = "Result", nullable = false, length = 65535)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Column(name = "Visibility", nullable = false)
	public boolean isVisibility() {
		return this.visibility;
	}

	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProblemresultId))
			return false;
		ProblemresultId castOther = (ProblemresultId) other;

		return (this.getProblemId() == castOther.getProblemId())
				&& ((this.getResult() == castOther.getResult())
						|| (this.getResult() != null
								&& castOther.getResult() != null
								&& this.getResult()
										.equals(castOther.getResult())))
				&& (this.isVisibility() == castOther.isVisibility());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProblemId();
		result = 37 * result
				+ (getResult() == null ? 0 : this.getResult().hashCode());
		result = 37 * result + (this.isVisibility() ? 1 : 0);
		return result;
	}

}
