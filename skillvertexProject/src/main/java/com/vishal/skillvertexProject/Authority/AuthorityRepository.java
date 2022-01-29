package com.vishal.skillvertexProject.Authority;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {

}
