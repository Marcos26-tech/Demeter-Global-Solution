package br.com.demeter.to;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioTO {

	private int idUsuario;
	private long cnpjUsuario;
	private String razaoSocial;
	private String emailUusario;
	private String senhaUsuario;
	private String tipoUsuario;
	private String regiaoUsuario;
	
	public UsuarioTO() {
	}

	public UsuarioTO(int idUsuario, long cnpjUsuario, String razaoSocial, String emailUusario, String senhaUsuario, String tipoUsuario) {
		this.idUsuario = idUsuario;
		this.cnpjUsuario = cnpjUsuario;
		this.razaoSocial = razaoSocial;
		this.emailUusario = emailUusario;
		this.senhaUsuario = senhaUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public UsuarioTO(String regiaoUsuario) {
		this.regiaoUsuario = regiaoUsuario;
	}

	public UsuarioTO(int idUsuario, String razaoSocial) {
		this.idUsuario = idUsuario;
		this.razaoSocial = razaoSocial;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public long getCnpjUsuario() {
		return cnpjUsuario;
	}

	public void setCnpjUsuario(long cnpjUsuario) {
		this.cnpjUsuario = cnpjUsuario;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getEmailUusario() {
		return emailUusario;
	}

	public void setEmailUusario(String emailUusario) {
		this.emailUusario = emailUusario;
	}

	public String getSenhaUsuario() {
		return senhaUsuario;
	}

	public void setSenhaUsuario(String senhaUsuario) {
		this.senhaUsuario = senhaUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getRegiaoUsuario() {
		return regiaoUsuario;
	}

	public void setRegiaoUsuario(String regiaoUsuario) {
		this.regiaoUsuario = regiaoUsuario;
	}

	@Override
	public String toString() {
		return "UsuarioBO{" +
				"idUsuario=" + idUsuario +
				", cnpjUsuario=" + cnpjUsuario +
				", razaoSocial='" + razaoSocial + '\'' +
				", emailUusario='" + emailUusario + '\'' +
				", tipoUsuario='" + tipoUsuario + '\'' +
				", regiaoUsuario='" + regiaoUsuario + '\'' +
				'}';
	}
}
