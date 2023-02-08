CREATE TABLE public.users_courses(
    users_course_id uuid NOT NULL,
    user_id uuid NOT NULL,
    course_id uuid NOT NULL,
    CONSTRAINT users_courses_pkey PRIMARY KEY (users_course_id)
)