INSERT INTO categoria (descricao, nome) VALUES
('Entreterimento para pets', 'Brinquedos'),
('Comida para pets', 'Alimentos');

INSERT INTO produto (descricao, imagem, marca, min_quantidade, nome, preco, quantidade, category_id) VALUES
('produto feito com ingredientes selecionados', 'teste.png', 'petz', 2, 'ração comilão', 23.00, 4, 2),
('brinquedo feito de borracha', 'teste.png', 'petisz', 2, 'osso de barracha', 23.00, 4, 1);

INSERT INTO usuario (nome) VALUES
('Vitor'),
('Manuela');

INSERT INTO item_pedido (quantidade, produto_id, usuario_id) VALUES
(1, 1, 2);