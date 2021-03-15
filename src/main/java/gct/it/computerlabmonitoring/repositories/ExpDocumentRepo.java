package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.ExpDocument;

import java.util.List;

@Repository
public interface ExpDocumentRepo extends CrudRepository<ExpDocument, Integer>{
    List<ExpDocument> findAll();
    
    @Query(nativeQuery = true,
    value = "select * from exp_document where id in (select doc_id from submission where student_reg_no= ?1);")
    List<ExpDocument> findDocsByRegNo(String regno);
}