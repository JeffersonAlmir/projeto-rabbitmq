package com.appProducer.api1.controller;

import com.appProducer.api1.entity.Produto;
import com.appProducer.api1.service.QueueSendService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private QueueSendService queueSend;

    public ProdutoController(QueueSendService queueSend) {
        this.queueSend = queueSend;
    }

    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
        queueSend.send(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
}
