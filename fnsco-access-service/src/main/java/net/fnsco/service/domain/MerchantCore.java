package net.fnsco.service.domain;

import java.util.Date;

import net.fnsco.core.base.DTO;
/**
 * @desc 商户基本信息表
 * @author tangliang
 * @date 2017年6月21日 下午1:46:30
 */
public class MerchantCore extends DTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5841374213021310345L;

	private Integer id;

    private String innercode;

    private String name;

    private String merarea;

    private String signdate;

    private String merenname;

    private String cooporgncode;

    private String registarea;

    private String startbustime;

    private String endbustime;

    private String realbusscope;

    private String merlegalperson;

    private String legalpersontel;

    private String legalvalidpapertype;

    private String papernum;

    private String papervalidtime;

    private String businesslicensenum;

    private String businesslicensevalidtime;

    private String taxregistcode;

    private String merregistaddress;

    private String accounttype;

    private String subbankname;

    private String accountno;

    private String accountname;

    private String openbankprince;

    private String openbank;

    private String openbankcity;

    private String accountholderid;

    private String openbanknum;

    private String closedcircle;

    private String abbreviation;

    private Integer status;

    private String mercflag;

    private Integer source;

    private String installarea;

    private String merinstallarea;

    private String updateuser;

    private Date lastupdatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInnercode() {
        return innercode;
    }

    public void setInnercode(String innercode) {
        this.innercode = innercode == null ? null : innercode.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMerarea() {
        return merarea;
    }

    public void setMerarea(String merarea) {
        this.merarea = merarea == null ? null : merarea.trim();
    }

    public String getSigndate() {
        return signdate;
    }

    public void setSigndate(String signdate) {
        this.signdate = signdate == null ? null : signdate.trim();
    }

    public String getMerenname() {
        return merenname;
    }

    public void setMerenname(String merenname) {
        this.merenname = merenname == null ? null : merenname.trim();
    }

    public String getCooporgncode() {
        return cooporgncode;
    }

    public void setCooporgncode(String cooporgncode) {
        this.cooporgncode = cooporgncode == null ? null : cooporgncode.trim();
    }

    public String getRegistarea() {
        return registarea;
    }

    public void setRegistarea(String registarea) {
        this.registarea = registarea == null ? null : registarea.trim();
    }

    public String getStartbustime() {
        return startbustime;
    }

    public void setStartbustime(String startbustime) {
        this.startbustime = startbustime == null ? null : startbustime.trim();
    }

    public String getEndbustime() {
        return endbustime;
    }

    public void setEndbustime(String endbustime) {
        this.endbustime = endbustime == null ? null : endbustime.trim();
    }

    public String getRealbusscope() {
        return realbusscope;
    }

    public void setRealbusscope(String realbusscope) {
        this.realbusscope = realbusscope == null ? null : realbusscope.trim();
    }

    public String getMerlegalperson() {
        return merlegalperson;
    }

    public void setMerlegalperson(String merlegalperson) {
        this.merlegalperson = merlegalperson == null ? null : merlegalperson.trim();
    }

    public String getLegalpersontel() {
        return legalpersontel;
    }

    public void setLegalpersontel(String legalpersontel) {
        this.legalpersontel = legalpersontel == null ? null : legalpersontel.trim();
    }

    public String getLegalvalidpapertype() {
        return legalvalidpapertype;
    }

    public void setLegalvalidpapertype(String legalvalidpapertype) {
        this.legalvalidpapertype = legalvalidpapertype == null ? null : legalvalidpapertype.trim();
    }

    public String getPapernum() {
        return papernum;
    }

    public void setPapernum(String papernum) {
        this.papernum = papernum == null ? null : papernum.trim();
    }

    public String getPapervalidtime() {
        return papervalidtime;
    }

    public void setPapervalidtime(String papervalidtime) {
        this.papervalidtime = papervalidtime == null ? null : papervalidtime.trim();
    }

    public String getBusinesslicensenum() {
        return businesslicensenum;
    }

    public void setBusinesslicensenum(String businesslicensenum) {
        this.businesslicensenum = businesslicensenum == null ? null : businesslicensenum.trim();
    }

    public String getBusinesslicensevalidtime() {
        return businesslicensevalidtime;
    }

    public void setBusinesslicensevalidtime(String businesslicensevalidtime) {
        this.businesslicensevalidtime = businesslicensevalidtime == null ? null : businesslicensevalidtime.trim();
    }

    public String getTaxregistcode() {
        return taxregistcode;
    }

    public void setTaxregistcode(String taxregistcode) {
        this.taxregistcode = taxregistcode == null ? null : taxregistcode.trim();
    }

    public String getMerregistaddress() {
        return merregistaddress;
    }

    public void setMerregistaddress(String merregistaddress) {
        this.merregistaddress = merregistaddress == null ? null : merregistaddress.trim();
    }

    public String getAccounttype() {
        return accounttype;
    }

    public void setAccounttype(String accounttype) {
        this.accounttype = accounttype == null ? null : accounttype.trim();
    }

    public String getSubbankname() {
        return subbankname;
    }

    public void setSubbankname(String subbankname) {
        this.subbankname = subbankname == null ? null : subbankname.trim();
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno == null ? null : accountno.trim();
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname == null ? null : accountname.trim();
    }

    public String getOpenbankprince() {
        return openbankprince;
    }

    public void setOpenbankprince(String openbankprince) {
        this.openbankprince = openbankprince == null ? null : openbankprince.trim();
    }

    public String getOpenbank() {
        return openbank;
    }

    public void setOpenbank(String openbank) {
        this.openbank = openbank == null ? null : openbank.trim();
    }

    public String getOpenbankcity() {
        return openbankcity;
    }

    public void setOpenbankcity(String openbankcity) {
        this.openbankcity = openbankcity == null ? null : openbankcity.trim();
    }

    public String getAccountholderid() {
        return accountholderid;
    }

    public void setAccountholderid(String accountholderid) {
        this.accountholderid = accountholderid == null ? null : accountholderid.trim();
    }

    public String getOpenbanknum() {
        return openbanknum;
    }

    public void setOpenbanknum(String openbanknum) {
        this.openbanknum = openbanknum == null ? null : openbanknum.trim();
    }

    public String getClosedcircle() {
        return closedcircle;
    }

    public void setClosedcircle(String closedcircle) {
        this.closedcircle = closedcircle == null ? null : closedcircle.trim();
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation == null ? null : abbreviation.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMercflag() {
        return mercflag;
    }

    public void setMercflag(String mercflag) {
        this.mercflag = mercflag == null ? null : mercflag.trim();
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getInstallarea() {
        return installarea;
    }

    public void setInstallarea(String installarea) {
        this.installarea = installarea == null ? null : installarea.trim();
    }

    public String getMerinstallarea() {
        return merinstallarea;
    }

    public void setMerinstallarea(String merinstallarea) {
        this.merinstallarea = merinstallarea == null ? null : merinstallarea.trim();
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getLastupdatetime() {
        return lastupdatetime;
    }

    public void setLastupdatetime(Date lastupdatetime) {
        this.lastupdatetime = lastupdatetime;
    }
}