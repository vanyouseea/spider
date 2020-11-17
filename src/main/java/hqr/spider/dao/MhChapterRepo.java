package hqr.spider.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hqr.spider.domain.MhChapter;

@Repository
public interface MhChapterRepo extends JpaRepository<MhChapter, Integer> {

}
