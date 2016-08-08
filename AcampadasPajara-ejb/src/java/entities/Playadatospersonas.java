/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author W7
 */
@Entity
@Table(name = "PLAYADATOSPERSONAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Playadatospersonas.findAll", query = "SELECT p FROM Playadatospersonas p"),
    @NamedQuery(name = "Playadatospersonas.findByPdp", query = "SELECT p FROM Playadatospersonas p WHERE p.pdp = :pdp"),
    @NamedQuery(name = "Playadatospersonas.findByPlaya", query = "SELECT p FROM Playadatospersonas p WHERE p.playa = :playa"),
    @NamedQuery(name = "Playadatospersonas.findByNombrepersona", query = "SELECT p FROM Playadatospersonas p WHERE p.nombrepersona = :nombrepersona"),
    @NamedQuery(name = "Playadatospersonas.findByApellido", query = "SELECT p FROM Playadatospersonas p WHERE p.apellido = :apellido"),
    @NamedQuery(name = "Playadatospersonas.findByDni", query = "SELECT p FROM Playadatospersonas p WHERE p.dni = :dni"),
    @NamedQuery(name = "Playadatospersonas.findByMunicipio", query = "SELECT p FROM Playadatospersonas p WHERE p.municipio = :municipio"),
    @NamedQuery(name = "Playadatospersonas.findByEmail", query = "SELECT p FROM Playadatospersonas p WHERE p.email = :email"),
    @NamedQuery(name = "Playadatospersonas.findByTel\u00e9fono", query = "SELECT p FROM Playadatospersonas p WHERE p.tel\u00e9fono = :tel\u00e9fono"),
    @NamedQuery(name = "Playadatospersonas.findByFechaEntrada", query = "SELECT p FROM Playadatospersonas p WHERE p.fechaEntrada = :fechaEntrada"),
    @NamedQuery(name = "Playadatospersonas.findByFechaSalida", query = "SELECT p FROM Playadatospersonas p WHERE p.fechaSalida = :fechaSalida")})
public class Playadatospersonas implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PDP")
    private Integer pdp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "PLAYA")
    private String playa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "NOMBREPERSONA")
    private String nombrepersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "APELLIDO")
    private String apellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "DNI")
    private String dni;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "MUNICIPIO")
    private String municipio;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "EMAIL")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 144)
    @Column(name = "TEL\u00c9FONO")
    private String teléfono;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_ENTRADA")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrada;
    @Basic(optional = false)
    @NotNull
    @Column(name = "FECHA_SALIDA")
    @Temporal(TemporalType.DATE)
    private Date fechaSalida;

    public Playadatospersonas() {
    }

    public Playadatospersonas(Integer pdp) {
        this.pdp = pdp;
    }

    public Playadatospersonas(Integer pdp, String playa, String nombrepersona, String apellido, String dni, String municipio, String email, String teléfono, Date fechaEntrada, Date fechaSalida) {
        this.pdp = pdp;
        this.playa = playa;
        this.nombrepersona = nombrepersona;
        this.apellido = apellido;
        this.dni = dni;
        this.municipio = municipio;
        this.email = email;
        this.teléfono = teléfono;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public Integer getPdp() {
        return pdp;
    }

    public void setPdp(Integer pdp) {
        this.pdp = pdp;
    }

    public String getPlaya() {
        return playa;
    }

    public void setPlaya(String playa) {
        this.playa = playa;
    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pdp != null ? pdp.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Playadatospersonas)) {
            return false;
        }
        Playadatospersonas other = (Playadatospersonas) object;
        if ((this.pdp == null && other.pdp != null) || (this.pdp != null && !this.pdp.equals(other.pdp))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Playadatospersonas[ pdp=" + pdp + " ]";
    }
    
}
