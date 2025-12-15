package com.app.consomer.api2.service;

import com.app.consomer.api2.entity.Produto;
import com.app.consomer.api2.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public Produto processar(Produto produto){
        if(produto.getPreco() == null || produto.getPreco()<=0){
            throw new IllegalArgumentException("Preço inválido");
        }
        Produto produtoSave = produtoRepository.save(produto);

        return produtoSave;
    }

    public List<Produto> listAll(){
        return produtoRepository.findAll();
    }
}
