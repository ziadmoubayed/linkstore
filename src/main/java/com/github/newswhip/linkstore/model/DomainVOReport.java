package com.github.newswhip.linkstore.model;

import java.util.Objects;

public class DomainVOReport {

    private String domain;
    private Long stats;

    public DomainVOReport() {
    }

    public DomainVOReport(String domain, Long stats) {
        this.domain = domain;
        this.stats = stats;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Long getStats() {
        return stats;
    }

    public void setStats(Long stats) {
        this.stats = stats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DomainVOReport that = (DomainVOReport) o;
        return Objects.equals(domain, that.domain) &&
                Objects.equals(stats, that.stats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain, stats);
    }

    @Override
    public String toString() {
        return "DomainVOReport{" +
                "domain='" + domain + '\'' +
                ", stats=" + stats +
                '}';
    }
}
