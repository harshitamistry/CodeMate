package com.beans;
// Generated 5-Nov-2016 11:07:12 PM by Hibernate Tools 5.2.0.Beta1

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Problemresult generated by hbm2java
 */
@Entity
@Table(name = "problemresult", catalog = "hibernateCodeMate")
public class Problemresult implements java.io.Serializable {

	private ProblemresultId id;
	private Problems problems;

	public Problemresult() {
	}

	public Problemresult(ProblemresultId id, Problems problems) {
		this.id = id;
		this.problems = problems;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "problemId", column = @Column(name = "ProblemID", nullable = false)),
			@AttributeOverride(name = "result", column = @Column(name = "Result", nullable = false, length = 65535)),
			@AttributeOverride(name = "visibility", column = @Column(name = "Visibility", nullable = false)) })
	public ProblemresultId getId() {
		return this.id;
	}

	public void setId(ProblemresultId id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ProblemID", nullable = false, insertable = false, updatable = false)
	public Problems getProblems() {
		return this.problems;
	}

	public void setProblems(Problems problems) {
		this.problems = problems;
	}

}
