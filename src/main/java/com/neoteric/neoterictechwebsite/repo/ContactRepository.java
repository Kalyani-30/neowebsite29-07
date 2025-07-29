package com.neoteric.neoterictechwebsite.repo;

import com.neoteric.neoterictechwebsite.entity.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity,Long> {

}
