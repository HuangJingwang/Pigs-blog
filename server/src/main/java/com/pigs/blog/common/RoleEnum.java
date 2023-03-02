package com.pigs.blog.common;

public enum  RoleEnum {
    ROOT("root", "root"),
    VISITOR("visitor", "visitor"),
    BLACK("black", "black");
    private String role;
    private String des;

    RoleEnum(String role, String des) {
        this.role = role;
        this.des = des;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
