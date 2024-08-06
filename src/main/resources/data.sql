-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto.html#howto.data-initialization.using-basic-sql-scripts

-- user roles
INSERT INTO roles (id, role)
VALUES (1, 'ADMIN');
INSERT INTO roles (id, role)
VALUES (2, 'MODERATOR');
INSERT INTO roles (id, role)
VALUES (3, 'USER');

-- some test users
INSERT INTO users (id, age, email, full_name, password, username)
VALUES (1, 28, 'admin@admin.com', 'Admin Adminov',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 'admin');

INSERT INTO users (id, age, email, full_name, password, username)
VALUES (2, 29, 'moderator@moderator.com', 'Moderator Moderatorov',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b',
        'moderator');

INSERT INTO users (id, age, email, full_name,  password, username)
VALUES (3, 30, 'user@user.com', 'User Userov',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 'user');

INSERT INTO users (id, age, email, full_name,  password, username)
VALUES (4, 33, 'ivan@ivan.com', 'Ivan Ivanov',
        '26dd1ba9ae974a70136ea4463046371516661b0601ca0fb83a905876ca549b3473dd524d578e241b', 'ivan');
-- user roles
-- admin
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (1, 1);
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (1, 2);
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (1, 3);
-- moderator
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (2, 2);
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (2, 3);
-- user
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (3, 3);
-- user 2
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (4, 3);
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (4, 2);
INSERT INTO users_roles (`user_ent_id`, `roles_id`)
VALUES (4, 1);

-- categories
# POLITICAL, WAR, ECONOMIC, CULTURAL
INSERT INTO categories (id, description, name)
VALUES ('1', 'Articles for politics.', 'POLITICAL');
INSERT INTO categories (id, description, name)
VALUES ('2', 'Articles for wars.', 'WAR');
INSERT INTO categories (id, description, name)
VALUES ('3', 'Articles for economic.', 'ECONOMIC');
INSERT INTO categories (id, description, name)
VALUES ('4', 'Articles for culture.', 'CULTURAL');


-- articles
INSERT INTO articles ( title, content, period, created, author_id)
VALUES
    ('Rome Empire',
     'Octavian, Antony, and Lepidus formed the Second Triumvirate of Rome but, as with the first, these men were also equally ambitious.
Lepidus was effectively neutralized when Antony and Octavian agreed that he should have Hispania and Africa to rule over and thereby kept
him from any power play in Rome. It was agreed that Octavian would rule Roman lands in the west and Antony in the east
Antony''s involvement with the Egyptian queen Cleopatra VII, however, upset the balance Octavian had hoped to maintain and the two went
to war. Antony and Cleopatra''s combined forces were defeated at the Battle of Actium in 31 BCE and both later took their own lives.
Octavian emerged as the sole power in Rome. In 27 BCE he was granted extraordinary powers by the Senate and took the name of Augustus,
the first Emperor of Rome.
Historians are in agreement that this is the point at which the history of Rome ends and the history of the Roman Empire begins.',
     'ROME_EMPIRE',
     2024-03-09, 1);
INSERT INTO articles ( title, content, period, created, author_id)
VALUES(
          'Thracian Art',
       'The art produced by the people of Thrace,
as indicated by the many precious objects found in Thracian tombs dating from the Bronze Age onwards, was,
like the culture itself, a mix of indigenous ideas and foreign influences. Although it can be difficult to distinguish
local and imported high-value objects, typical features of Thracian Art are the use of brightly coloured wall paintings
to decorate tombs, the widespread use of metal vessels, especially for the burial of the deceased''s remains,
and intricately manufactured jewellery pieces in precious metals. Finally, there was a particular appreciation
for Greek black-figure pottery, with many of the
finest examples of that genre surviving in Thracian tombs.',
       'Ancient Greece',
    2024-04-12,3
      );


INSERT INTO articles_categories VALUES (1,1);
INSERT INTO articles_categories VALUES (1,2);
INSERT INTO articles_categories VALUES (2,1);
INSERT INTO articles_categories VALUES (2,2);
INSERT INTO articles_categories VALUES (2,3);
INSERT INTO articles_categories VALUES (3,1);
INSERT INTO articles_categories VALUES (3,2);
INSERT INTO articles_categories VALUES (4,1);
INSERT INTO articles_categories VALUES (4,2);
INSERT INTO articles_categories VALUES (4,3);
INSERT INTO articles_categories VALUES (4,4);
INSERT INTO articles_categories VALUES (5,1);
INSERT INTO articles_categories VALUES (5,2);



INSERT INTO pictures(title, url, author_id, article_id)
VALUES ('Kumata', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg', 1, 1);

INSERT INTO pictures(title, url, author_id, article_id)
VALUES ('Kumata', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630581072/d47iy8kxv6qni8euhojk.jpg', 1, 1);

INSERT INTO pictures(title, url, author_id, article_id)
VALUES ('Velo Erul', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630581418/tqhjrinmsb69ev7upg0q.jpg', 1, 2);

INSERT INTO pictures(title, url, author_id, article_id)
VALUES ('Velo Erul 2', 'http://res.cloudinary.com/ch-cloud/image/upload/v1630582448/oowojgn4lagybkvv20jb.jpg', 1, 2);


