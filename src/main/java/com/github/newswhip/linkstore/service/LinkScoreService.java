package com.github.newswhip.linkstore.service;

import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;
import com.github.newswhip.linkstore.repo.impl.InMemoryLinkScoreRepo;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Simpleton Service wrapping necessary Business Logic associated with the REPL.
 * CRUD operations + reporting methods.
 * We can add as many reporting methods as we need, which will be consumed by the report generators
 * The LinkVORepository returns a stream of data to avoid memory explosions.
 */
public enum LinkScoreService {

    INSTANCE;

    private LinkVORepository linkVORepository;

    LinkScoreService() {
        this.linkVORepository = new InMemoryLinkScoreRepo();
    }

    /**
     * Adds a link with it's score to the data store.
     *
     * @param url
     * @param score
     */
    public void addLink(String url, Long score) {
        if (score < 0)
            throw new IllegalArgumentException("Invalid score. It should be a whole number, greater or equal to zero");
        this.linkVORepository.addLink(new LinkVO(url, score));
    }

    /**
     * Removes a link from the store.
     *
     * @param url
     */
    public void removeLink(String url) {
        this.linkVORepository.removeLink(new LinkVO(url));
    }

    /**
     * Clears the data store.
     * This function can be dangerous.
     * But we are including it because LinkScoreService is a singleton / global variable
     * and we need a way to reset it in the same jvm.
     */
    public void flushStore() {
        this.linkVORepository.deleteAll();
    }

    /**
     * Domain Stats Report groups the links by domain aggregating the sum of stats and the count of links.
     * This report is like: select domain, count(links), sum(stats) group by domain.
     *
     * @return
     */
    public Map<String, AbstractMap.SimpleEntry<Integer, Long>> getDomainStats() {
        return this.linkVORepository.getLinks().collect(Collectors.toMap(LinkVO::getDomain,
                linkVO -> new AbstractMap.SimpleEntry<>(1, linkVO.getScore()),
                (existing, conflict) -> existing = new AbstractMap.SimpleEntry<>(existing.getKey() + 1, existing.getValue() + conflict.getValue())));
    }
}
