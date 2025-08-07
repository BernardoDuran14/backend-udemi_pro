package plaxi.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import plaxi.backend.entity.S3Object;

public interface S3ObjectRepository extends JpaRepository<S3Object, Long> {

//    @Query("SELECT s FROM S3Object s WHERE s.bucketName = ?1 AND s.fileName = ?2")
//    S3Object findByBucketNameAndFileName(String bucketName, String fileName);
}
