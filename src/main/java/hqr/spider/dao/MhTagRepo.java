package hqr.spider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import hqr.spider.domain.MhTag;

@Repository
public interface MhTagRepo extends JpaRepository<MhTag, Integer> {
	
	@Query(value="select count(1) from mh_tag where tag_url=:tagUrl and tag_name=:tagName", nativeQuery = true)
	int checkTagExist(String tagUrl, String tagName);
	
	@Query(value="select * from mh_tag where tag_name=:tagName", nativeQuery = true)
	MhTag getEntityByTagName(String tagName);
	
}
