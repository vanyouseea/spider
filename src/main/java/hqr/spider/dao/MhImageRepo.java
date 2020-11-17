package hqr.spider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hqr.spider.domain.MhImage;

@Repository
public interface MhImageRepo extends JpaRepository<MhImage, Long> {

}
