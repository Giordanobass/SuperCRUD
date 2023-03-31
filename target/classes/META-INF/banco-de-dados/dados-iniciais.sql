insert into produto (id, nome, descricao, preco, data_criacao) values (nextval('produto_id_seq'), 'Kindle', 'Conheça o novo Kindle, agora com iluminação embutida ajustável, que permite que você leia em ambientes abertos ou fechados, a qualquer hora do dia.', 799.0, CURRENT_DATE - INTERVAL '1 day');
insert into produto (id, nome, descricao, preco, data_criacao) values (nextval('produto_id_seq'), 'Camera GoPro Hero 7', 'Desempenho 2x melhor', 1400.0, CURRENT_DATE - INTERVAL '1 day');

insert into cliente (id, nome, sexo) values (nextval('cliente_id_seq'), 'Fernando Medeiros', 'MASCULINO');
insert into cliente (id, nome, sexo) values (nextval('cliente_id_seq'), 'Marcos Mariano', 'MASCULINO');

insert into pedido(id, data_conclusao, data_criacao, data_ultima_atualizacao, bairro, cep, cidade, complemento, estado, logradouro, numero, status, total, cliente_id)	VALUES (nextval('pedido_id_seq'), current_timestamp, current_timestamp, current_timestamp, 'Jardim América 2', '72922351', 'Aguas Lindas de Goias', 'complemento', 'Goias', 'Rua Catalão, Q20', '3a2', 'AGUARDANDO', 100.0, 1);
		 
insert into item_pedido (id, pedido_id, produto_id, preco_produto, quantidade) values (nextval('item_pedido_id_seq'), 1, 1, 5.0, 2);

insert into categoria (id, nome) values (nextval('categoria_id_seq'), 'Eletrônicos');