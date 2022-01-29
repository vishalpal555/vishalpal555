package com.vishal.skillvertexProject.Authority;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import com.vishal.skillvertexProject.User.User;
import org.springframework.security.core.GrantedAuthority;


@Table(name = "authorities")
@Entity
public class Authority implements GrantedAuthority{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

    @Column(name = "ROLE_DESCRIPTION")
    private String roleDescription;

    @Override
    public String getAuthority() {
        // TODO Auto-generated method stub
        return this.authority;
    }

    public String getId(){
        return this.id;
    }

    public String getUsername(){
        return this.username;
    }

    public String getRoleDescription(){
        return this.roleDescription;
    }

    public void setRoleDescription(String roleDescription){
        this.roleDescription = roleDescription;
    }

    public void setRoleCode(String authority){
        this.authority = authority;
    }

    public void setUsername(String username){
        this.username = username;
    }


    
}
