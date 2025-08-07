package plaxi.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "s3_object")
public class S3Object {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s3_object_id")
    private Long s3ObjectId;

    @Column(name = "content_type")
    private String contentType = "application/octet-stream";

    @Column(name = "bucket")
    private String bucket;

    @Column(name = "filename")
    private String filename;

    @Column(name = "url", length = 1024)
    private String url;

    @Column(name = "status")
    private Boolean status = true;

    // Constructor por defecto
    public S3Object() {
    }

    // Constructor sin s3ObjectId
    public S3Object(String contentType, String bucket, String filename, String url, Boolean status) {
        this.contentType = contentType;
        this.bucket = bucket;
        this.filename = filename;
        this.url = url;
        this.status = status;
    }

    // Constructor completo
    public S3Object(Long s3ObjectId, String contentType, String bucket, String filename, String url, Boolean status) {
        this.s3ObjectId = s3ObjectId;
        this.contentType = contentType;
        this.bucket = bucket;
        this.filename = filename;
        this.url = url;
        this.status = status;
    }

    // Getters y Setters
    public Long getS3ObjectId() {
        return s3ObjectId;
    }

    public void setS3ObjectId(Long s3ObjectId) {
        this.s3ObjectId = s3ObjectId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

