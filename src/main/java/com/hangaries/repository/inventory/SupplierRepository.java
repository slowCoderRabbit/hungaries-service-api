package com.hangaries.repository.inventory;

import com.hangaries.model.inventory.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "select * from SUPPLIER_MASTER where supplier_status=:status order by id", nativeQuery = true)
    List<Supplier> getAllActiveSuppliers(@Param("status") String status);

    @Modifying
    @Transactional
    @Query(value = "update SUPPLIER_MASTER set supplier_status=:status, updated_by=:updatedBy,updated_date=:updatedDate where id=:id", nativeQuery = true)
    int saveSupplierStatus(@Param("id") long id, @Param("status") String status, @Param("updatedBy") String updatedBy, @Param("updatedDate") Date updatedDate);

    @Query(value = "select * from SUPPLIER_MASTER where id=:id", nativeQuery = true)
    Supplier getSupplierById(@Param("id") long id);
}