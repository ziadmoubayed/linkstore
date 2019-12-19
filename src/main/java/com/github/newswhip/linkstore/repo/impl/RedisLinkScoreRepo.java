package com.github.newswhip.linkstore.repo.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.newswhip.linkstore.common.Mapper;
import com.github.newswhip.linkstore.model.LinkVO;
import com.github.newswhip.linkstore.repo.LinkVORepository;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.stream.Stream;

public class RedisLinkScoreRepo implements LinkVORepository {

    private final static String REDIS_SET = "sets:links";
    private final ObjectMapper mapper = Mapper.INSTANCE.getObjectMapper();

    @Override
    public void addLinkWithScore(LinkVO linkVO) {
        try (Jedis jedis = new Jedis()) {
            jedis.sadd(REDIS_SET, mapper.writeValueAsString(linkVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeLink(LinkVO linkVO) {
        try (Jedis jedis = new Jedis()) {
            jedis.srem(REDIS_SET, mapper.writeValueAsString(linkVO));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Stream<LinkVO> getLinks() {
        try (Jedis jedis = new Jedis()) {
            return jedis.smembers(REDIS_SET).stream()
                    .map(ss -> {
                        try {
                            return mapper.readValue(ss, LinkVO.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return null;
                    });
        }
    }

    @Override
    public void deleteAll() {
        try(Jedis jedis = new Jedis()) {
            jedis.flushDB();
        }
    }
}
