package eStoreProduct.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class cartModel {
	@Id
	private int id;
	private int cid;
	private int pid;
	private int quantity;
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public cartModel(int cid, int pid,int quantity) {
		this.cid = cid;
		this.pid = pid;
		this.quantity=quantity;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

}