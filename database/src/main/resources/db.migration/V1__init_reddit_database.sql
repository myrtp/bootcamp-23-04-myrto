CREATE SCHEMA IF NOT EXISTS bootcamp2304myrto;

CREATE TABLE IF NOT EXISTS bootcamp2304myrto.user (
          id bigserial primary key,
          username text not null,
          email text not null,
          password text not null,
          dob timestamp not null,
          profileImage text
);
CREATE TABLE IF NOT EXISTS bootcamp2304myrto.subreddit (
           id bigserial primary key,
           description text not null,
           title text not null,
           created_at timestamp not null

);

CREATE TABLE IF NOT EXISTS bootcamp2304myrto.post (
      id bigserial primary key,
      subreddit_id bigint not null,
      user_id bigint not null,
      title text not null,
      text text not null,
      image text not null,
      created_at timestamp not null,
      updated_at  timestamp,
      foreign key (user_id) references bootcamp2304myrto.user(id),
      foreign key (subreddit_id) references bootcamp2304myrto.subreddit(id)

);


CREATE TABLE IF NOT EXISTS bootcamp2304myrto.comment (
         id bigserial primary key,
         post_id bigint not null,
         user_id bigint not null,
         text text not null,
         image text,
         comm_reply text,
         created_at timestamp not null,
         updated_at  timestamp,
         foreign key (user_id) references bootcamp2304myrto.user(id),
         foreign key (post_id) references bootcamp2304myrto.post(id)

);

CREATE TABLE IF NOT EXISTS bootcamp2304myrto.interest (
              id bigserial primary key,
              text text not null

);

CREATE TABLE IF NOT EXISTS bootcamp2304myrto.user_interest (
               id bigserial primary key,
               user_id bigint not null,
               interest_id bigint not null,
               foreign key (user_id) references bootcamp2304myrto.user(id),
               foreign key (interest_id) references bootcamp2304myrto.interest(id)
);


CREATE TABLE IF NOT EXISTS bootcamp2304myrto.subred_interest (
                 id bigserial primary key,
                 subreddit_id bigint not null,
                 interest_id bigint not null,
                 foreign key (subreddit_id) references bootcamp2304myrto.subreddit(id),
                 foreign key (interest_id) references bootcamp2304myrto.interest(id)
);

CREATE TABLE IF NOT EXISTS bootcamp2304myrto.user_subred (
         id bigserial primary key,
         subreddit_id bigint not null,
         user_id bigint not null,
         role text not null,
         foreign key (subreddit_id) references bootcamp2304myrto.subreddit(id),
         foreign key (user_id) references bootcamp2304myrto.user(id)
);

ALTER TABLE bootcamp2304myrto.comment
    ADD COLUMN  subreddit_id bigint,
        add  foreign key (subreddit_id) references bootcamp2304myrto.subreddit(id);

