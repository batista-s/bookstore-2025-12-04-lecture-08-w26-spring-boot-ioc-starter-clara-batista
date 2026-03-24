package csd214.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Objects;

@Entity
@DiscriminatorValue("DIGITAL_MUSIC")
public class DigitalMusicEntity extends MusicCollectionEntity {
    @Column(name = "link")
    private String link;
    @Column(name = "download_count", nullable = false)
    private int downloadCount = 0;

    public DigitalMusicEntity() {
    }

    public DigitalMusicEntity(double price, String title, String artist, String genre, int year, String link, String name) {
        super(name, price, title, artist, genre, year);
        this.link = link;
        this.downloadCount = 0;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }

    @Override
    public String toString() {
        return "DigitalMusicEntity{" +
                "link='" + link + '\'' +
                ", downloadCount=" + downloadCount +
                '}' + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof DigitalMusicEntity that)) return false;
        if (!super.equals(o)) return false;
        return downloadCount == that.downloadCount && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), link, downloadCount);
    }
}