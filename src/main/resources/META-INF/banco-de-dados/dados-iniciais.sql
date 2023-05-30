insert into produto (id, data_criacao, data_ultima_atualizacao, nome, descricao, preco) values (nextval('produto_id_seq'), CURRENT_DATE - INTERVAL '1 day', current_timestamp, 'Kindle', 'Conheça o novo Kindle', 799.0);
insert into produto (id, data_criacao, data_ultima_atualizacao, nome, descricao, preco) values (nextval('produto_id_seq'), CURRENT_DATE - INTERVAL '1 day', current_timestamp, 'Camera GoPro Hero 7', 'Desempenho 2x melhor', 1400.0);

insert into cliente (id, nome, sexo) values (nextval('cliente_id_seq'), 'Fernando Medeiros', 'MASCULINO');
insert into cliente (id, nome, sexo) values (nextval('cliente_id_seq'), 'Marcos Mariano', 'MASCULINO');

insert into pedido(id, data_conclusao, data_criacao, data_ultima_atualizacao, bairro, cep, cidade, complemento, estado, logradouro, numero, status, total, cliente_id)	VALUES (nextval('pedido_id_seq'), current_timestamp, current_timestamp, current_timestamp, 'Jardim América 2', '72922351', 'Aguas Lindas de Goias', 'complemento', 'Goias', 'Rua Catalão, Q20', '3a2', 'AGUARDANDO', 100.0, 1);
		 
insert into item_pedido (pedido_id, produto_id, preco_produto, quantidade) values ( 1, 1, 5.0, 2);

insert into categoria (id, nome) values (nextval('categoria_id_seq'), 'Eletrônicos');