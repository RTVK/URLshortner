package com.tinyurl.model;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "tiny_url", "long_url"})
public class TinyUrl {

	@JsonProperty("tiny_url")
	private String tinyUrl;

	@NotNull
	@JsonProperty("long_url")
	private String longUrl;

	@NotNull
	public String getTinyUrl() {
		return tinyUrl;
	}

	public void setTinyUrl(String tinyUrl) {
		this.tinyUrl = tinyUrl;
	}

	@NotNull
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
		return new HashCodeBuilder().append(this.longUrl).toHashCode();
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
