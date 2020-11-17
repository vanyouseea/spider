package hqr.spider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hqr.spider.domain.MhBook;

@Repository
public interface MhBookRepo extends JpaRepository<MhBook, Integer> {

}
