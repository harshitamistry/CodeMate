package com.beans;
// Generated 5-Nov-2016 11:07:12 PM by Hibernate Tools 5.2.0.Beta1

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Country generated by hbm2java
 */
@Entity
@Table(name = "Country", catalog = "hibernateCodeMate")
@NamedQueries({
		@NamedQuery(name = "Country.allCountry", query = "from Country"),
		@NamedQuery(name = "Country.getCountry", query = "from Country where countryId=:countryId"),
		@NamedQuery(name = "Country.getFlagOfUser", query = "select c.countryFlag from Country c join c.schools s join s.users u where userId=:userId") })
public class Country implements java.io.Serializable {

	private Integer countryId;
	private String countryName;
	private byte[] countryFlag;
	private String countryName_1;
	private Set<School> schools = new HashSet<School>(0);

	public Country() {
	}

	public Country(String countryName, byte[] countryFlag) {
		this.countryName = countryName;
		this.countryFlag = countryFlag;
	}

	public Country(String countryName, byte[] countryFlag,
			String countryName_1, Set<School> schools) {
		this.countryName = countryName;
		this.countryFlag = countryFlag;
		this.countryName_1 = countryName_1;
		this.schools = schools;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CountryID", unique = true, nullable = false)
	public Integer getCountryId() {
		return this.countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Column(name = "CountryName", nullable = false)
	public String getCountryName() {
		return this.countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Column(name = "CountryFlag", nullable = false)
	public byte[] getCountryFlag() {
		return this.countryFlag;
	}

	public void setCountryFlag(byte[] countryFlag) {
		this.countryFlag = countryFlag;
	}

	@Column(name = "country_name")
	public String getCountryName_1() {
		return this.countryName_1;
	}

	public void setCountryName_1(String countryName_1) {
		this.countryName_1 = countryName_1;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "country")
	public Set<School> getSchools() {
		return this.schools;
	}

	public void setSchools(Set<School> schools) {
		this.schools = schools;
	}

}
