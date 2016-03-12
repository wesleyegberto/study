package br.unifieo.sd.servidornomes.data;

/**
 * Representa um servidor de serviço.
 */
public class ServerService {
	private String ip;
	private short port;
	private short syncPort;
	private short power;
	private int qtyClients;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public short getPort() {
		return port;
	}

	public void setPort(short port) {
		this.port = port;
	}

	public short getSyncPort() {
		return syncPort;
	}

	public void setSyncPort(short syncPort) {
		this.syncPort = syncPort;
	}

	public short getPower() {
		return power;
	}

	public void setPower(short power) {
		this.power = power;
	}

	public int getQtyClients() {
		return qtyClients;
	}

	public void setQtyClients(int qtyClients) {
		this.qtyClients = qtyClients;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + port;
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
		ServerService other = (ServerService) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (port != other.port)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return ip + ":" + port;
	}

}
