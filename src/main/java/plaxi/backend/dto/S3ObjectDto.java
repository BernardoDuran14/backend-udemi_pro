package plaxi.backend.dto;

public class S3ObjectDto {

    private Long s3ObjectId;
    private String contentType;
    private String bucket;
    private String filename;
    private String url;
    private Boolean status;

    // Constructor por defecto
    public S3ObjectDto() {
    }

    // Constructor con todos los campos
    public S3ObjectDto(Long s3ObjectId, String contentType, String bucket, String filename, String url, Boolean status) {
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
