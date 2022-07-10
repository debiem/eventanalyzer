package com.cs.build.server.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event {

	private String id;
	private BuildState state;
	private String type;
	private String host;
	@JsonProperty("timestamp")
	private long timeStamp;
	
	public Event() {
		
	}

	public Event(String id, BuildState state, String type, String host, long timeStamp) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.timeStamp = timeStamp;
	}

	public String getId() {
		return id;
	}

	public BuildState getState() {
		return state;
	}

	public String getType() {
		return type;
	}

	public String getHost() {
		return host;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + (int) (timeStamp ^ (timeStamp >>> 32));
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Event other = (Event) obj;
		if (host == null) {
			if (other.host != null)
				return false;
		} else if (!host.equals(other.host))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (state != other.state)
			return false;
		if (timeStamp != other.timeStamp)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

}
