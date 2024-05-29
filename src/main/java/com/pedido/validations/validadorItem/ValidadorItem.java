package com.pedido.validations.validadorItem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pedido.validations.validadorItem.validadorPost.ValidadorPostItem;

@Service
public class ValidadorItem {
    
    @Autowired
    private List<ValidadorPostItem> validarPost;


}
