package com.pedido.validations.validadorItem.validadorPost;

import com.pedido.dtos.ItemInputDTO;

public interface ValidadorPostItem {

        void validar(long idPedido, ItemInputDTO dto);
}
