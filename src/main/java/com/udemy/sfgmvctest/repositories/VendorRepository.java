package com.udemy.sfgmvctest.repositories;

import com.udemy.sfgmvctest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
