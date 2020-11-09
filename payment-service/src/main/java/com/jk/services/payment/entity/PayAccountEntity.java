package com.jk.services.payment.entity;

import com.jk.framework.base.entity.BaseEntity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>支付账号</p>
 * <p/>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author afi on on 2017/5/11.
 * @version 1.0
 * @since 1.0
 */
public class PayAccountEntity extends BaseEntity{
	private static final long serialVersionUID = -6926698681905825045L;

	private Integer id;

    private String name;

    private String productCode;

    private String partner;

    private String partnerKey;
    
    private String password;
    
    private String account;
    
    private String accountName;

    private String code;

    private String codeKey;

    private String remark;

    private String caFileName;

    private String caPassword;
    
    private BigDecimal balance;

    public String getCaFileName() {
        return caFileName;
    }

    public void setCaFileName(String caFileName) {
        this.caFileName = caFileName;
    }

    public String getCaPassword() {
        return caPassword;
    }

    public void setCaPassword(String caPassword) {
        this.caPassword = caPassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		 this.accountName = accountName == null ? null : accountName.trim();
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner == null ? null : partner.trim();
    }
    public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPartnerKey() {
        return partnerKey;
    }

    public void setPartnerKey(String partnerKey) {
        this.partnerKey = partnerKey == null ? null : partnerKey.trim();
    }
    public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    public String getCodeKey() {
        return codeKey;
    }

    public void setCodeKey(String codeKey) {
        this.codeKey = codeKey == null ? null : codeKey.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}