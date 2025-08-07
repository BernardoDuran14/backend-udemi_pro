package plaxi.backend.dto;

import java.util.Objects;

public class NewFileDto {
    private final String filename;
    private final String bucket;
    private final String contentType;

    public NewFileDto(String filename, String bucket, String contentType) {
        this.filename = filename;
        this.bucket = bucket;
        this.contentType = contentType;
    }

    public String getFilename() {
        return filename;
    }

    public String getBucket() {
        return bucket;
    }

    public String getContentType() {
        return contentType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewFileDto that = (NewFileDto) o;
        return filename.equals(that.filename) && bucket.equals(that.bucket) && contentType.equals(that.contentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(filename, bucket, contentType);
    }

    @Override
    public String toString() {
        return "NewFileDto{" +
                "filename='" + filename + '\'' +
                ", bucket='" + bucket + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}