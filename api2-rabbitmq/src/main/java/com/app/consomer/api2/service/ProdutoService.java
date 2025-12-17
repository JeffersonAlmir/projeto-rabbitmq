package com.app.consomer.api2.service;

import com.app.consomer.api2.entity.Produto;
import com.app.consomer.api2.repository.ProdutoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final RedisTemplate<String, Object> redisTemplate;

    public ProdutoService(ProdutoRepository produtoRepository, RedisTemplate<String, Object> redisTemplate) {
        this.produtoRepository = produtoRepository;
        this.redisTemplate = redisTemplate;
    }

    public Produto processar(Produto produto){
        if(produto.getPreco() == null || produto.getPreco()<=0){
            throw new IllegalArgumentException("Preço inválido");
        }
        Produto produtoSave = produtoRepository.save(produto);

        return produtoSave;
    }

    public List<Produto> listAll(){
        List<Produto> cache = (List<Produto>) redisTemplate.opsForValue().get("produto");

        if(cache!=null){
            log.info("Produto listado com o REDIS com sucesso");
            return cache;
        }
        log.info("Produto listado com o POSTGRES com sucesso");
        redisTemplate.opsForValue().set("produto", produtoRepository.findAll());
        return produtoRepository.findAll();
    }
}
