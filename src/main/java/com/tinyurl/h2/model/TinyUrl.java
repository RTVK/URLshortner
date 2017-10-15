package com.tinyurl.h2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "TinyUrl")
public class TinyUrl {

	@Id
	@Column(name = "tiny_url")
	private Long tinyUrl;

	@Column(name = "long_url")
	private String longUrl;

	public Long getTinyUrl() {
		return tinyUrl;
	}

	public void setTinyUrl(Long tinyUrl) {
		this.tinyUrl = tinyUrl;
	}

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.tinyUrl).append(this.longUrl).hashCode();
	}

	@Override
	public boolean equals(final Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof TinyUrl) == false) {
			return false;
		}
		TinyUrl rhs = ((TinyUrl) other);
		return new EqualsBuilder().append(this.longUrl, rhs.longUrl)
				.append(this.tinyUrl, rhs.tinyUrl).isEquals();
	}

}
