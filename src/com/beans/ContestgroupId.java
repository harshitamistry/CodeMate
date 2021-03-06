package com.beans;
// Generated 5-Nov-2016 11:07:12 PM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ContestgroupId generated by hbm2java
 */
@Embeddable
public class ContestgroupId implements java.io.Serializable {

	private int contestId;
	private int groupId;

	public ContestgroupId() {
	}

	public ContestgroupId(int contestId, int groupId) {
		this.contestId = contestId;
		this.groupId = groupId;
	}

	@Column(name = "ContestID", nullable = false)
	public int getContestId() {
		return this.contestId;
	}

	public void setContestId(int contestId) {
		this.contestId = contestId;
	}

	@Column(name = "GroupID", nullable = false)
	public int getGroupId() {
		return this.groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ContestgroupId))
			return false;
		ContestgroupId castOther = (ContestgroupId) other;

		return (this.getContestId() == castOther.getContestId())
				&& (this.getGroupId() == castOther.getGroupId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getContestId();
		result = 37 * result + this.getGroupId();
		return result;
	}

}
