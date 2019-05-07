package db.pojos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "BiomaterialList")
@XmlType(propOrder = {"biomaterials"})
public class BiomaterialList implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@XmlElement(name = "Biomaterial")
    @XmlElementWrapper(name = "Products") //element that surrounds more elements
	private List<Biomaterial> biomaterials;

	public BiomaterialList() {
		super();
		this.biomaterials = new ArrayList<>();	}

	public BiomaterialList(List<Biomaterial> lista) {
		biomaterials = lista;
	}

	public List<Biomaterial> getBiomaterials() {
		return biomaterials;
	}

	public void setBiomaterials(List<Biomaterial> biomaterials) {
		this.biomaterials = biomaterials;
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((biomaterials == null) ? 0 : biomaterials.hashCode());
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
		BiomaterialList other = (BiomaterialList) obj;
		if (biomaterials == null) {
			if (other.biomaterials != null)
				return false;
		} else if (!biomaterials.equals(other.biomaterials))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BiomaterialList [biomaterials=" + biomaterials + "]";
	}
	
	
	
}
