--tipo_estado
INSERT INTO public.tipo_estado (id_estado, estado)
VALUES 
(1, 'Feito'),
(2, 'Cancelado'),
(3, 'Em Processo');

-- Tipo de usuário
INSERT INTO public.tipo_usuario (id_tipo_usuario, tipo)
VALUES
(1, 'admin'),
(2, 'técnico de manutenção'),
(3, 'user');

-- Código postal
INSERT INTO public.cod_postal (id_cod_postal, localidade)
VALUES
(1, '4750-052');

-- Usuários
INSERT INTO public.usuario (nome, tel, nif, cod_postal, id_tipo_usuario, rua, n_porta)
VALUES
('Joao Antonio', '+351933456789', 123456789, '1', 1, 'Rua Pereiras', 10),
('Carlos Ferreira', '+351911223344', 555444333, '1', 2, 'Rua das Oficinas', 5),
('Maria Silva', '+351912345678', 987654321, '1', 3, 'Avenida Central', 20);

-- Tipos de espaço desportivo
INSERT INTO public.tipo_espaco_desportivo (id_tipo_espaco, tipo)
VALUES 
(1, 'Futebol'),
(2, 'Basquetebol'),
(3, 'Ténis');

-- Espaços desportivos
INSERT INTO public.espaco_desportivo (id_tipo_espaco, capacidade, lote, preco_hora, disponibilidade)
VALUES 
(1, 100, 'Lote A', 25.00, TRUE),
(2, 50, 'Lote B', 20.00, TRUE),
(3, 30, 'Lote C', 15.00, FALSE),
(1, 120, 'Lote D', 30.00, TRUE);

--tipos de pagamento
INSERT INTO public.tipo_pagamento (id_tipo_pag, nome)
VALUES 
(1, 'Cartão de Crédito'),
(2, 'MB Way');

-- Inserindo dados de Manutenção
INSERT INTO public.manutencao (id_usuario, id_espaco, dt_ini, dt_fim, descricao, custo, id_estado)
VALUES
(2, 1, '2024-03-01', '2024-03-01', 'Troca da relva sintética', 500.00, 1), 
(2, 2, '2024-03-05', '2024-03-05', 'Pintura das marcações da quadra', 200.00, 1),
(2, 4, '2024-03-10', '2024-03-10', 'Reparação das redes do campo', 150.00, 1);

-- Reservas
INSERT INTO public.reserva (id_usuario, id_espaco, dt, h_ini, h_fim, id_estado)
VALUES
(3, 1, '2024-03-15', '14:00', '16:00', 1),
(3, 3, '2024-03-18', '10:00', '12:00', 1),
(3, 2, '2024-03-20', '18:00', '20:00', 1);

-- Pagamentos
INSERT INTO public.pagamento (id_reserva, dt_pagamento, id_estado, id_tipo_pag, id_usuario)
VALUES
(1, '2024-03-15', 2, 1, 3), 
(2, '2024-03-18', 2, 2, 3),
(3, '2024-03-20', 2, 1, 3); 

-- Notificações
INSERT INTO public.notificacao (id_usuario, mensagem, data_notificacao, hora_notificacao)
VALUES
(3, 'Reserva confirmada para o espaço Futebol em 15/03/2024', '2024-03-10', '10:30:00'),
(3, 'Reserva confirmada para o espaço Ténis em 18/03/2024', '2024-03-12', '11:15:00'),
(3, 'Reserva confirmada para o espaço Basquetebol em 20/03/2024', '2024-03-15', '09:45:00'),
(2, 'Manutenção programada para o espaço Futebol em 01/03/2024', '2024-02-28', '14:00:00'),
(2, 'Manutenção programada para o espaço Basquetebol em 05/03/2024', '2024-03-02', '15:30:00'),
(2, 'Manutenção programada para o espaço Futebol em 10/03/2024', '2024-03-07', '16:45:00');





