-- Seeds para USUARIOS
INSERT INTO usuarios (nome, email, area_atuacao, data_cadastro)
VALUES
    ('Maria Silva', 'maria.silva@email.com', 'Administrativo', '2025-10-20'),
    ('Joao Santos', 'joao.santos@email.com', 'Vendedora', '2025-11-01'),
    ('Ana Oliveira', 'ana.oliveira@email.com', 'Buscando 1º Emprego', '2025-11-05');

-- Seeds para TRILHAS
INSERT INTO trilhas (nome, descricao, nivel, carga_horaria, foco_principal)
VALUES
    ('Analista de Dados para Impacto Social',
     'Aprenda a usar dados para tomar decisões que reduzam a desigualdade. (ODS 10)',
     'INICIANTE', 40, 'Dados e ODS 10'),
    ('Desenvolvedor de IA Ética e Inclusiva',
     'Crie IAs que promovam a justiça e evitem vieses, focando em inovação responsável. (ODS 9 e 10)',
     'AVANCADO', 120, 'IA e ODS 9'),
    ('Especialista em Cibersegurança para Trabalho Remoto',
     'Garanta ambientes de trabalho digital seguros e decentes. (ODS 8)',
     'INTERMEDIARIO', 60, 'Segurança e ODS 8'),
    ('Alfabetização Digital para o Futuro do Trabalho',
     'Trilha base para quem precisa de requalificação total para entrar no mercado digital. (ODS 4)',
     'INICIANTE', 20, 'Inclusão Digital e ODS 4');

--  Seeds para COMPETENCIAS
INSERT INTO competencias (nome, categoria, descricao)
VALUES
    ('Análise de Dados', 'Técnica', 'Capacidade de analisar conjuntos de dados'),
    ('IA e Machine Learning', 'Técnica', 'Fundamentos de IA'),
    ('Segurança da Informação', 'Técnica', 'Proteger dados e sistemas'),
    ('Comunicação Interpessoal', 'Comportamental', 'Habilidade de se comunicar com clareza'),
    ('Pensamento Crítico', 'Comportamental', 'Analisar problemas de forma lógica'),
    ('Alfabetização Digital', 'Técnica', 'Uso de ferramentas digitais básicas');

-- Relação TRILHA_COMPETENCIA (N:N)
-- Trilha 1 (Analista de Dados) ganha comps 1 (Dados) e 5 (Pensamento Crítico)
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 1);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (1, 5);

-- Trilha 2 (IA Ética) ganha comps 2 (IA) e 4 (Comunicação)
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (2, 2);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (2, 4);

-- Trilha 3 (Cibersegurança) ganha comps 3 (Segurança) e 5 (Pensamento Crítico)
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (3, 3);
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (3, 5);

-- Trilha 4 (Alfabetização) ganha comp 6 (Alfabetização Digital)
INSERT INTO trilha_competencia (trilha_id, competencia_id) VALUES (4, 6);

-- NOVO: Seeds para MATRICULAS (Ex: Maria se matriculou na Trilha 1)
INSERT INTO matriculas (usuario_id, trilha_id, data_inscricao, status)
VALUES
    (1, 1, '2025-11-10', 'CURSANDO'),
    (2, 4, '2025-11-12', 'CURSANDO');