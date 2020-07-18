package com.redpigmall.api.beans.propertyeditors;

import java.beans.PropertyEditorSupport;

import com.redpigmall.api.beans.StringUtils;

public class StringTrimmerEditor extends PropertyEditorSupport {
	private final String charsToDelete;
	private final boolean emptyAsNull;

	public StringTrimmerEditor(boolean emptyAsNull) {
		this.charsToDelete = null;
		this.emptyAsNull = emptyAsNull;
	}

	public StringTrimmerEditor(String charsToDelete, boolean emptyAsNull) {
		this.charsToDelete = charsToDelete;
		this.emptyAsNull = emptyAsNull;
	}

	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text.trim();
			if (this.charsToDelete != null) {
				value = StringUtils.deleteAny(value, this.charsToDelete);
			}
			if ((this.emptyAsNull) && ("".equals(value))) {
				setValue(null);
			} else {
				setValue(value);
			}
		}
	}

	public String getAsText() {
		Object value = getValue();
		return value != null ? value.toString() : "";
	}
}
