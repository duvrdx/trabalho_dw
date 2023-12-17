INSERT INTO epic_type (id, description)
VALUES ('cc975528-d749-46cb-83b9-945383698973', 'CRUD'); 

INSERT INTO user_story_type (id, description, epic_type_id)
VALUES ('75e30e80-b226-415a-8e83-7c4b6751ce3a', 'Criar', 'cc975528-d749-46cb-83b9-945383698973');

INSERT INTO user_story_type (id, description, epic_type_id)
VALUES ('44c20343-90bf-4cd3-876e-efca22ae1e57', 'Deletar', 'cc975528-d749-46cb-83b9-945383698973'); 

INSERT INTO user_story_type (id, description, epic_type_id)
VALUES ('766d5a6f-dd95-4899-b80c-d1baf9e19d27', 'Resgatar', 'cc975528-d749-46cb-83b9-945383698973'); 

INSERT INTO user_story_type (id, description, epic_type_id)
VALUES ('f103893d-5ce5-4ad8-9fe1-f321925b2015', 'Atualizar', 'cc975528-d749-46cb-83b9-945383698973');  



INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('a6eeea77-7135-4568-8837-1bb613970c92', 'Criar rota de criação de', '75e30e80-b226-415a-8e83-7c4b6751ce3a');
INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('74241c2c-f486-4e4d-8faf-a262a571ad6b', 'Criar view de criação de', '75e30e80-b226-415a-8e83-7c4b6751ce3a');

INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('06f050d8-561a-4b95-a645-608064f1d813', 'Criar rota para deletar','44c20343-90bf-4cd3-876e-efca22ae1e57'); 
INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('3d9cc235-66ff-4385-9bb9-a68879f5be16', 'Criar view para deletar', '44c20343-90bf-4cd3-876e-efca22ae1e57');

INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('4abce2e0-55c8-461e-9bb0-cac5bd28e61f', 'Criar rota para resgatar', '766d5a6f-dd95-4899-b80c-d1baf9e19d27');
INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('f75664b3-1a6e-4665-a1d8-11a3bdc6cb0f', 'Criar view para resgatar', '766d5a6f-dd95-4899-b80c-d1baf9e19d27');  

INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('a6658c33-2b3e-4d16-a157-cbadfdacc92a', 'Criar rota de atualização','f103893d-5ce5-4ad8-9fe1-f321925b2015'); 
INSERT INTO task_type (id, description, user_story_type_id)
VALUES ('d5bdd11a-3abc-4b55-905f-54b09b7b6435', 'Criar view para atualizar', 'f103893d-5ce5-4ad8-9fe1-f321925b2015'); 
