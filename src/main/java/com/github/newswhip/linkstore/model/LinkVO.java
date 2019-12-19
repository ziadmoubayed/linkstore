package com.github.newswhip.linkstore.model;

import com.github.newswhip.linkstore.common.UrlUtils;

import java.util.Objects;

public class LinkVO {

    private String url;
    private String domain;
    private Long score;

    public LinkVO(String url) {
        this.url = url;
        this.domain = UrlUtils.getDomain(url);
    }

    public LinkVO(String url, Long score) {
        this.url = url;
        this.score = score;
        this.domain = UrlUtils.getDomain(url);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDomain() {
        return domain;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkVO linkVO = (LinkVO) o;
        return Objects.equals(url, linkVO.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }

    @Override
    public String toString() {
        return "LinkVO{" +
                "url='" + url + '\'' +
                ", domain='" + domain + '\'' +
                ", score=" + score +
                '}';
    }
}
