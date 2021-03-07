package gct.it.computerlabmonitoring.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import gct.it.computerlabmonitoring.entities.ExpDocument;

import java.util.List;

@Repository
public interface ExpDocumentRepo extends CrudRepository<ExpDocument, Integer>{
    List<ExpDocument> findAll();
}
