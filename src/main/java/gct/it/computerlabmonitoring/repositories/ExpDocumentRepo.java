package gct.it.computerlabmonitoring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gct.it.computerlabmonitoring.entities.ExpDocument;

@Repository @Transactional
public interface ExpDocumentRepo extends CrudRepository<ExpDocument, Integer>{
    List<ExpDocument> findAll();
    
    @Query(nativeQuery = true,
    value = "select * from exp_document where id in (select doc_id from submission where student_reg_no= ?1);")
    List<ExpDocument> findDocsByRegNo(String regno);

    @Modifying(clearAutomatically = true)
    @Query(value = "update exp_document set marks = ?1 where id = ?2", nativeQuery = true)
    int updateMark(Integer marks, Integer docId);
}