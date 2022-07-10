package com.cs.build.server.model;

public class AnalyzedEvents {

	private final String id;
	private final long duration;
	private final String type;
	private final String host;
	private boolean alert;

	public AnalyzedEvents(String id, long duration, String type, String host, boolean alert) {
		super();
		this.id = id;
		this.duration = duration;
		this.type = type;
		this.host = host;
		this.alert = alert;
	}

	public boolean isAlert() {
		return alert;
	}

	public void setAlert(boolean alert) {
		this.alert = alert;
	}

	public String getId() {
		return id;
	}

	public long getDuration() {
		return duration;
	}

	public String getType() {
		return type;
	}

	public String getHost() {
		return host;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alert ? 1231 : 1237);
		result = prime * result + (int) (duration ^ (duration >>> 32));
		result = prime * result + ((host == null) ? 0 : host.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		AnalyzedEvents other = (AnalyzedEvents) obj;
		if (alert != other.alert)
			return false;
		if (duration != other.duration)
			return false;
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EventsExceedinSLA [id=" + id + ", duration=" + duration + ", type=" + type + ", host=" + host
				+ ", alert=" + alert + "]";
	}

	
}
