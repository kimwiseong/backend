USE wehigher;

CREATE TABLE `users`(
    `id` integer auto_increment,
    `social_id` varchar(100) not null,
    `email` varchar(30) not null,
    `is_graduate` boolean,
    `is_login` boolean,
    `is_valid` boolean,
    `name` varchar(20),
    `password` varchar(30),
    `provider` enum('KAKAO', 'NAVER') not null,
    `refresh_token` varchar(300),
    `role` enum('USER', 'ADMIN') not null,
    CONSTRAINT USERS_PK PRIMARY KEY (`id`),
    CONSTRAINT USERS_CK UNIQUE (`social_id`, `provider`)
);

CREATE TABLE `base_time_entity`(
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE `award`(
    `id` integer auto_increment,
    `date` varchar(20) not null,
    `institution` varchar(30) not null,
    `name` varchar(20) not null,
    `target` varchar(20) not null,
    `tier` varchar(20) not null,
    PRIMARY KEY (`id`)
);

CREATE TABLE `career`(
    `id` integer auto_increment,
    `grade` tinyint not null,
    `parent_hope` varchar(20) not null,
    `student_hope` varchar(20) not null,
    `reason` varchar(300),
    `specialty_or_interest` varchar(256),
    PRIMARY KEY (`id`)
);

CREATE TABLE `creative`(
    `id` integer auto_increment,
    `activity_time` integer,
    `area` varchar(20),
    `grade` tinyint not null,
    `specialty` varchar(300),
    PRIMARY KEY (`id`)
);

CREATE TABLE `educational`(
    `id` integer auto_increment,
    `course` varchar(20),
    `detail_and_specialty` varchar(300),
    `grade` tinyint,
    `ranking` tinyint,
    `semester` varchar(10),
    `subject` varchar(20),
    PRIMARY KEY (`id`)
);

# CREATE TABLE `interview`(
#     `id` integer auto_increment
# );

CREATE TABLE `opinion`(
    `id` integer auto_increment,
    `grade` tinyint not null,
    `content` varchar(300),
    PRIMARY KEY (`id`)
);


CREATE TABLE `reading`(
    `id` integer auto_increment,
    `grade` tinyint not null,
    `semester` varchar(10),
    `subject` varchar(20),
    `title` varchar(30)
);

CREATE TABLE `school_record`(
    `school_record_id` integer auto_increment,
    `user_id` integer not null,
    `career_id` integer not null,
    `educational_id` integer not null,
    `opinion_id` integer not null,
    `reading_id` integer not null,
    `creative_id` integer not null,
    `award_id` integer not null,
    PRIMARY KEY (`school_record_id`),
    FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
    FOREIGN KEY (`career_id`) REFERENCES `career` (`id`),
    FOREIGN KEY (`educational_id`) REFERENCES `educational` (`id`),
    FOREIGN KEY (`opinion_id`) REFERENCES `opinion` (`id`),
    FOREIGN KEY (`reading_id`) REFERENCES `reading` (`id`),
    FOREIGN KEY (`creative_id`) REFERENCES `creative` (`id`),
    FOREIGN KEY (`award_id`) REFERENCES `award` (`id`)
);

CREATE TABLE `qna`(
    `id` integer auto_increment,
    `answer` varchar(200),
    `question` varchar(200),
    PRIMARY KEY (`id`)
);
