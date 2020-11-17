package hqr.spider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hqr.spider.domain.MhBookTagMap;

@Repository
public interface MhBookTagMapRepo extends JpaRepository<MhBookTagMap, Long> {
	
}
