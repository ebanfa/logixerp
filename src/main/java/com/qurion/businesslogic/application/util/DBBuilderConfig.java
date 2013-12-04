/**
 * 
 */
package com.qurion.businesslogic.application.util;

/**
 * @author Edward Banfa
 *
 */
public class DBBuilderConfig {
	
	private Boolean overWrite;
	private String uiConfigDir;

	/**
	 * @param overWrite
	 */
	public DBBuilderConfig(Boolean overWrite) {
		this.overWrite = overWrite;
	}

	/**
	 * @return the overWrite
	 */
	public Boolean getOverWrite() {
		return overWrite;
	}

	/**
	 * @param overWrite the overWrite to set
	 */
	public void setOverWrite(Boolean overWrite) {
		this.overWrite = overWrite;
	}

	/**
	 * @return the uiConfigDir
	 */
	public String getUiConfigDir() {
		return uiConfigDir;
	}

	/**
	 * @param uiConfigDir the uiConfigDir to set
	 */
	public void setUiConfigDir(String uiConfigDir) {
		this.uiConfigDir = uiConfigDir;
	}

}
