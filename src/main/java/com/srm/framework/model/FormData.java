
package com.srm.framework.model;

import java.util.List;

public class FormData {

    private String username;
    private String password;
    private String comments;
    private String fileName;
    private List<String> checkboxes;
    private String radio;
    private List<String> multiSelect;
    private String dropdown;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getComments() {
        return comments;
    }

    public String getFileName() {
        return fileName;
    }

    public List<String> getCheckboxes() {
        return checkboxes;
    }

    public String getRadio() {
        return radio;
    }

    public List<String> getMultiSelect() {
        return multiSelect;
    }

    public String getDropdown() {
        return dropdown;
    }
}
